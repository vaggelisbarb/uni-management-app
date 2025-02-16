package com.example.demo;


import com.example.demo.repository.DepartmentRepository;
import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import com.example.demo.model.entities.Department;
import com.example.demo.model.entities.Professor;
import com.example.demo.model.entities.Student;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Χρήση embedded DB
@Transactional
public class StudentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setup() throws IOException {
        testEntityManager.clear();  // Clears persistence context
        setupDatabase();

        // Debugging: Print all departments and students to verify correct setup
        List<Department> departments = testEntityManager.getEntityManager().createQuery("SELECT d FROM Department d", Department.class).getResultList();
        System.out.println("Departments in DB: " + departments.size());

        List<Student> students = testEntityManager.getEntityManager().createQuery("SELECT s FROM Student s", Student.class).getResultList();
        System.out.println("Students in DB: " + students.size());
    }


    @Test
    public void findByIdTest() throws IOException {
        // Fetching a Student object from the database based on the ID
        Student student = studentRepository.findAll().get(0);

        // Assertions
        assertNotNull(student);
        assertEquals("John", student.getPersonalInfo().getFirstName());
        assertEquals("New York", student.getAddress().getCity());
        assertEquals("S123456789", student.getEnrollmentInfo().getStudentIdCardNumber());
    }

    @Test
    public void findByNationalityTest() {
        // Fetching the Student object from the database based on the nationality
        List<Student> foundStudents = studentRepository.findByNationality("American");

        // Assertions for American nationality
        assertNotNull(foundStudents);
        assertEquals(1, foundStudents.size());  // Based on the sample data, 2 students are American
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
    }

    @Test
    public void findByGpaGreaterThanTest() {
        // Fetching the Student object from the database based on the GPA
        List<Student> foundStudents = studentRepository.findByGpaGreaterThan(3.7);

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(2, foundStudents.size());  // Based on the sample data, 2 students have GPA > 3.7
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals("Michael", foundStudents.get(1).getPersonalInfo().getFirstName());
    }

    @Test
    public void findByDepartmentTest() {
        testEntityManager.flush();
        // Fetching the Student object from the database based on the department
        Department csDepartment = testEntityManager.find(Department.class, 1);  // Computer Science Department
        Department eeDepartment = testEntityManager.find(Department.class, 2);  // Electrical Engineering Department

        // Checking for students in the Computer Science Department
        List<Student> csStudents = studentRepository.findStudentsByDepartmentId(csDepartment.getId());
        assertNotNull(csStudents);
        assertEquals(4, csStudents.size());
        assertEquals("John", csStudents.get(0).getPersonalInfo().getFirstName());

        // Checking for students in the Electrical Engineering Department
        List<Student> eeStudents = studentRepository.findStudentsByDepartmentId(eeDepartment.getId());
        assertNotNull(eeStudents);
        assertEquals(0, eeStudents.size());
    }

    @Test
    public void findStudentsByGpaAndCreditsTest() {
        // Fetching the Student object from the database based on the GPA and credits
        List<Student> foundStudents = studentRepository.findStudentsByGpaAndCredits(3.8, 90);

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(2, foundStudents.size());  // Based on the sample data, only one student meets this criteria
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals(90, foundStudents.get(0).getCreditsCompleted());
    }

    @Test
    public void countStudentsByDepartmentTest() {
        // Fetching the count of Students from the database based on the department
        long csCount = studentRepository.countStudentsByDepartment(testEntityManager.find(Department.class, 1L));
        assertEquals(4, csCount);  // Based on the sample data, 1 student is in the Computer Science department

        long eeCount = studentRepository.countStudentsByDepartment(testEntityManager.find(Department.class, 2L));
        assertEquals(0, eeCount);  // Based on the sample data, 1 student is in the Electrical Engineering department

        long meCount = studentRepository.countStudentsByDepartment(testEntityManager.find(Department.class, 3L));
        assertEquals(0, meCount);  // Based on the sample data, 1 student is in the Mechanical Engineering department
    }

    public void setupDatabase() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load list of departments from JSON
        List<Department> departments = objectMapper.readValue(
                new File("src/main/resources/data/departments.json"),
                new TypeReference<List<Department>>() {}
        );

        if (!departments.isEmpty()) {
            departments.get(0).setDegreePrograms(Arrays.asList("BSc in Computer Science", "MSc in AI", "PhD in Cybersecurity"));
            departments.get(1).setDegreePrograms(Arrays.asList("BSc in Electrical Engineering", "MSc in Embedded Systems", "PhD in Power Systems"));
            departments.get(2).setDegreePrograms(Arrays.asList("BSc in Mechanical Engineering", "MSc in Robotics", "PhD in Fluid Mechanics"));

            departments.get(0).setResearchAreas(Arrays.asList("Artificial Intelligence", "Cybersecurity", "Software Engineering"));
            departments.get(1).setResearchAreas(Arrays.asList("Renewable Energy", "Microelectronics", "Wireless Communications"));
            departments.get(2).setResearchAreas(Arrays.asList("Robotics", "Aerospace Engineering", "Automotive Design"));
        }

        // Save or update departments
        for (Department department : departments) {
            if (department.getId() == null || testEntityManager.find(Department.class, department.getId()) == null) {
                testEntityManager.persist(department); // New entity -> Persist
            } else {
                testEntityManager.merge(department); // Existing entity -> Merge
            }
        }

        testEntityManager.flush(); // Flush once at the end

        // Load students from JSON
        List<Student> students = objectMapper.readValue(
                new File("src/main/resources/data/students.json"),
                new TypeReference<List<Student>>() {}
        );

        for (Student student : students) {
            student.setDepartment(departments.get(0));

            if (student.getId() == null || testEntityManager.find(Student.class, student.getId()) == null) {
                testEntityManager.persist(student); // Persist if new
            } else {
                testEntityManager.merge(student); // Merge if exists
            }
        }

        testEntityManager.flush(); // Final flush
    }


}
