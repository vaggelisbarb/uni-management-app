package com.example.demo;


import com.example.demo.model.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Χρήση embedded DB
@Transactional
public class DepartmentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() throws IOException {
        testEntityManager.flush();

        // Fetch departments from JSON and save them to the database
        List<Department> departments = fetchDepartmentsFromJson();
        // Save or update departments
        for (Department department : departments) {
            if (department.getId() == null || testEntityManager.find(Department.class, department.getId()) == null) {
                testEntityManager.persist(department); // New entity -> Persist
            } else {
                testEntityManager.merge(department); // Existing entity -> Merge
            }
        }

        // Debugging: Print all departments and students to verify correct setup
        List<Department> storedDepartments = testEntityManager.getEntityManager().createQuery("SELECT d FROM Department d", Department.class).getResultList();
        System.out.println("\u001B[34mDepartments in DB: " + departments.size()+"\u001B[0m");

    }

    @Test
    public void findByNameTest() {
        Optional<Department> department = departmentRepository.findByName("Department of Computer Science");
        assertTrue(department.isPresent());
        assertEquals(1985, department.get().getFoundedYear());
        assertTrue(department.get().getResearchAreas().contains("Artificial Intelligence"));
    }

    @Test
    public void findByName_NotFoundTest() {
        Optional<Department> department = departmentRepository.findByName("Non-Existent Department");
        assertFalse(department.isPresent());
    }

    @Test
    public void findByBudgetGreaterThanTest() {
        List<Department> departments = departmentRepository.findByBudgetGreaterThan(4400000.00);
        assertEquals(3, departments.size());
        assertEquals("Department of Computer Science", departments.get(0).getName());
        assertEquals("Department of Electrical Engineering", departments.get(1).getName());
        assertEquals("Department of Mechanical Engineering", departments.get(2).getName());
    }

    @Test
    public void findByBudgetGreaterThan_NoResultsTest() {
        List<Department> departments = departmentRepository.findByBudgetGreaterThan(6000000.00);
        assertTrue(departments.isEmpty());
    }

    @Test
    public void findByBudgetBetweenTest() {
        List<Department> departments = departmentRepository.findByBudgetBetween(4000000.00, 4900000.00);
        assertEquals(2, departments.size());
        assertEquals("Department of Electrical Engineering", departments.get(0).getName());
        assertEquals("Department of Mechanical Engineering", departments.get(1).getName());
    }

    @Test
    public void findByBudgetBetween_NoResultsTest() {
        List<Department> departments = departmentRepository.findByBudgetBetween(1000000.00, 2000000.00);
        assertTrue(departments.isEmpty());
    }

    @Test
    public void findByFoundedYearAfterTest() {
        List<Department> departments = departmentRepository.findByFoundedYearAfter(1981);
        assertEquals(1, departments.size());
        assertEquals("Department of Computer Science", departments.get(0).getName());
    }

    @Test
    public void findByFoundedYearAfter_NoResultsTest() {
        List<Department> departments = departmentRepository.findByFoundedYearAfter(2025);
        assertTrue(departments.isEmpty());
    }

    @Test
    public void findByStudentCountGreaterThanTest() {
        List<Department> departments = departmentRepository.findByStudentCountGreaterThan(550);
        assertEquals(1, departments.size());
        assertTrue(departments.stream().anyMatch(d -> d.getName().equals("Department of Electrical Engineering")));
    }

    @Test
    public void findByStudentCountGreaterThan_NoResultsTest() {
        List<Department> departments = departmentRepository.findByStudentCountGreaterThan(700);
        assertTrue(departments.isEmpty());
    }

    @Test
    public void findByCityTest() {
        List<Department> departments = departmentRepository.findByCity("Tech City");
        assertEquals(1, departments.size());
        assertEquals("Department of Electrical Engineering", departments.get(0).getName());
    }

    @Test
    public void findByCity_NoResultsTest() {
        List<Department> departments = departmentRepository.findByCity("Unknown City");
        assertTrue(departments.isEmpty());
    }
    @Test
    public void findTopByOrderByBudgetDescTest() {
        Optional<Department> department = departmentRepository.findTopByOrderByBudgetDesc();
        assertTrue(department.isPresent());
        assertEquals("Department of Computer Science", department.get().getName());
    }

    @Test
    public void findByHeadOfDepartmentTest() {
        Optional<Department> department = departmentRepository.findByHeadOfDepartment(1L);
        assertFalse(department.isPresent());
    }

    @Test
    public void findByBudgetBetween_ExactBoundariesTest() {
        List<Department> departments = departmentRepository.findByBudgetBetween(4000000.00, 5000000.00);
        assertEquals(3, departments.size());
        assertTrue(departments.stream().anyMatch(d -> d.getName().equals("Department of Computer Science")));
        assertTrue(departments.stream().anyMatch(d -> d.getName().equals("Department of Electrical Engineering")));
        assertTrue(departments.stream().anyMatch(d -> d.getName().equals("Department of Mechanical Engineering")));
    }

    @Test
    public void findByBudgetGreaterThan_AllDepartmentsTest() {
        List<Department> departments = departmentRepository.findByBudgetGreaterThan(1000000.00);
        assertEquals(3, departments.size()); // Since all departments should be included
    }

    @Test
    public void findByFoundedYearAfter_BoundaryYearTest() {
        List<Department> departments = departmentRepository.findByFoundedYearAfter(1980);
        assertEquals(1, departments.size()); // Departments founded after 1980
    }

    @Test
    public void findByFoundedYearAfter_ExactYearTest() {
        List<Department> departments = departmentRepository.findByFoundedYearAfter(1985);
        assertTrue(departments.isEmpty()); // No departmentDTO is founded after 1985 in test data
    }

    @Test
    public void emptyDatabaseTest() {
        testEntityManager.getEntityManager().createQuery("DELETE FROM Department").executeUpdate(); // Clear table
        testEntityManager.flush(); // Ensure changes are applied

        List<Department> allDepartments = departmentRepository.findAll();
        assertTrue(allDepartments.isEmpty(), "Database is not empty after deletion!");

        assertFalse(departmentRepository.findByName("Department of Computer Science").isPresent());
        assertTrue(departmentRepository.findByBudgetGreaterThan(4000000.00).isEmpty());
        assertTrue(departmentRepository.findByFoundedYearAfter(1980).isEmpty());
    }

    private static List<Department> fetchDepartmentsFromJson() throws IOException {
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

        return departments;
    }

}
