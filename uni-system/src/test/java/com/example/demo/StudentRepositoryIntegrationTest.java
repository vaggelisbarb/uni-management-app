package com.example.demo;


import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import com.example.demo.model.entities.Department;
import com.example.demo.model.entities.Professor;
import com.example.demo.model.entities.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Χρήση embedded DB
public class StudentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findByIdTest() {
        // Storing Department object first into the database in order to get id
        Department department = buildDepartmentObj();
        testEntityManager.persistAndFlush(department);

        // Storing Student object into the database with the related department
        Student student = buildStudentObj();
        student.setDepartment(department);
        testEntityManager.persistAndFlush(student);

         // Fetching the Student object from the database based on the id
        Student foundStudent = studentRepository.findById(student.getId()).orElse(null);

        // Assertions
        assertNotNull(foundStudent);
        assertEquals("John", foundStudent.getPersonalInfo().getFirstName());
        assertEquals("City", foundStudent.getAddress().getCity());
        assertEquals("ID2402205", foundStudent.getEnrollmentInfo().getStudentIdCardNumber());
    }

    @Test
    public void findByNationalityTest() {
        setupDatabase();

        // Fetching the Student object from the database based on the nationality
        List<Student> foundStudents = studentRepository.findByNationality("GREECE");

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(1, foundStudents.size());
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals("City", foundStudents.get(0).getAddress().getCity());
        assertEquals("ID2402205", foundStudents.get(0).getEnrollmentInfo().getStudentIdCardNumber());
        assertEquals("GREECE", foundStudents.get(0).getNationality());
    }

    @Test
    public void findByGpaGreaterThanTest() {
        setupDatabase();

        // Fetching the Student object from the database based on the GPA
        List<Student> foundStudents = studentRepository.findByGpaGreaterThan(3.0);

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(1, foundStudents.size());
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals("City", foundStudents.get(0).getAddress().getCity());
        assertEquals("ID2402205", foundStudents.get(0).getEnrollmentInfo().getStudentIdCardNumber());
        assertEquals(6.4, foundStudents.get(0).getGpa());

        foundStudents = studentRepository.findByGpaGreaterThan(6.3);
        assertEquals(1, foundStudents.size());
        assertEquals(6.4, foundStudents.get(0).getGpa());

        foundStudents = studentRepository.findByGpaGreaterThan(6.4);
        assertEquals(0, foundStudents.size());

        foundStudents = studentRepository.findByGpaGreaterThan(6.5);
        assertEquals(0, foundStudents.size());
    }

    @Test
    public void findByDepartmentTest() {
        setupDatabase();

        // Fetching the Student object from the database based on the department
        Department department = testEntityManager.find(Department.class, 1L);
        List<Student> foundStudents = studentRepository.findByDepartment(department);

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(1, foundStudents.size());
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals("City", foundStudents.get(0).getAddress().getCity());
        assertEquals("ID2402205", foundStudents.get(0).getEnrollmentInfo().getStudentIdCardNumber());
        assertEquals("GREECE", foundStudents.get(0).getNationality());
    }


    @Test
    public void findStudentsByGpaAndCreditsTest() {
        setupDatabase();

        // Fetching the Student object from the database based on the GPA and credits
        List<Student> foundStudents = studentRepository.findStudentsByGpaAndCredits(6.4, 120);

        // Assertions
        assertNotNull(foundStudents);
        assertEquals(1, foundStudents.size());
        assertEquals("John", foundStudents.get(0).getPersonalInfo().getFirstName());
        assertEquals("City", foundStudents.get(0).getAddress().getCity());
        assertEquals("ID2402205", foundStudents.get(0).getEnrollmentInfo().getStudentIdCardNumber());
        assertEquals(6.4, foundStudents.get(0).getGpa());
        assertEquals(120, foundStudents.get(0).getCreditsCompleted());

        foundStudents = studentRepository.findStudentsByGpaAndCredits(6.5, 120);
        assertEquals(0, foundStudents.size());

        foundStudents = studentRepository.findStudentsByGpaAndCredits(6.4, 100);
        assertEquals(1, foundStudents.size());

        foundStudents = studentRepository.findStudentsByGpaAndCredits(6.4, 119);
        assertEquals(1, foundStudents.size());
    }

    @Test
    public void countStudentsByDepartmentTest() {
        setupDatabase();

        // Fetching the count of Students from the database based on the department
        long count = studentRepository.countStudentsByDepartment(testEntityManager.find(Department.class, 1L));

        // Assertions
        assertEquals(1, count);

        count = studentRepository.countStudentsByDepartment(testEntityManager.find(Department.class, 2L));
        assertEquals(0, count);
    }

    public void setupDatabase() {
        // Storing Department object first into the database in order to get id
        Department department = buildDepartmentObj();
        testEntityManager.persistAndFlush(department);

        // Storing Student object into the database with the related department
        Student student = buildStudentObj();
        student.setDepartment(department);
        testEntityManager.persistAndFlush(student);
    }
    // Generate Student instance
    private static Student buildStudentObj() {
        PersonalInfo personalInfo = new PersonalInfo("John", "Doe", "12-10-1996", "Male");
        ContactInfo contactInfo = new ContactInfo("vaggelisbarbalias@outlook.com", "6900000000");
        Address address = new Address("1234 Main Str", "Country", "54321", "State", "City");
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo("10-10-2013", "ID2402205", "Active", "2018");

        Student student = new Student(null, personalInfo, contactInfo, address, "GREECE", enrollmentInfo, null, null, 6.4, 120, null, null, true, null );
        return student;
    }

    // Generate Department instance
    private static Department buildDepartmentObj() {
        Department department = new Department();

        department.setName("Department of Computer Science");
        department.setDescription("Focuses on AI, Cybersecurity, and Software Engineering.");
        department.setBuildingLocation("Building A, Room 305");

        Address address = new Address("123 University St", "Countryland", "12345", "State", "City");
        department.setAddress(address);

        ContactInfo contactInfo = new ContactInfo("cs-dept@university.edu", "+1 234 567 8900");
        department.setContactInfo(contactInfo);

        Professor headOfDepartment = null;
        department.setHeadOfDepartment(headOfDepartment);

        List<String> degreePrograms = Arrays.asList("BSc in Computer Science", "MSc in AI", "PhD in Cybersecurity");
        department.setDegreePrograms(degreePrograms);

        List<String> researchAreas = Arrays.asList("Artificial Intelligence", "Cybersecurity", "Software Engineering");
        department.setResearchAreas(researchAreas);

        department.setStudentCount(500);
        department.setWebsite("https://cs.university.edu");
        department.setFoundedYear(1985);
        department.setBudget(5_000_000.00);

        return department;
    }

}
