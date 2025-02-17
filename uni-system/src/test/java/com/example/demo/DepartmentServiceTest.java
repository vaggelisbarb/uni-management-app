package com.example.demo;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.model.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.DepartmentMapper;
import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private List<DepartmentDTO> departmentDTOList;

    @BeforeEach
    public void setUp() throws IOException {
        // Fetch departments from JSON
        departmentDTOList = fetchDepartmentsFromJson();

    }

    @Test
    void testCreateDepartment() {
        for (DepartmentDTO departmentDTO : departmentDTOList) {
            Department department = DepartmentMapper.toEntity(departmentDTO);

            when(departmentRepository.save(any(Department.class))).thenReturn(department);

            // Call the method under test
            DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);

            // Verify that the save method is called once for each department
            assertNotNull(savedDepartment);
            assertEquals(departmentDTO.getName(), savedDepartment.getName());
        }

        // Ensure that save is called only once for each department after the loop
        verify(departmentRepository, times(departmentDTOList.size())).save(any(Department.class));
    }

    @Test
    void testFindAll() {
        when(departmentRepository.findAll()).thenReturn(departmentDTOList.stream().map(DepartmentMapper::toEntity).collect(toList()));

        // Call the method under test
        List<DepartmentDTO> fetchedDepartments = departmentService.findAll();

        // Verify that the findAll method is called once
        assertNotNull(fetchedDepartments);
        for (DepartmentDTO departmentDTO : fetchedDepartments) {
            System.out.println(JsonUtil.toJson(departmentDTO));

        }
        assertEquals(departmentDTOList.size(), fetchedDepartments.size());
    }

    @Test
    void testDeleteDepartment() {
        // Create a department entity from the DTO
        Department department = DepartmentMapper.toEntity(departmentDTOList.get(0));

        // Manually set the ID for the department (simulating Hibernate's auto-generation)
        department.setId(1L);

        // Mock the repository to return true when existsById is called with ID 1
        when(departmentRepository.existsById(1L)).thenReturn(true);

        // Call the method under test
        departmentService.deleteDepartment(1L);

        // Verify that the delete method is called once with the department's id
        verify(departmentRepository, times(1)).deleteById(1L);
        assertEquals(0, departmentRepository.findAll().size());
    }

    @Test
    void testUpdateDepartmentExists() {
        // Prepare department entity
        Department department = new Department();
        department.setId(1L);
        department.setName("Computer Science");

        // Prepare departmentDTO with the updated name
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Updated Computer Science");

        // Mock the repository to return the department when findById is called
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // Mock the save method to return the updated department
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // Call the update method
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentDTO);

        // Verify the updated department's name is correct
        assertNotNull(updatedDepartment);
        assertEquals("Computer Science", updatedDepartment.getName());

        // Verify repository calls
        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void testUpdateDepartmentNotFound() {
        // Create a departmentDTO with ID 99L for the test
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(99L);
        departmentDTO.setName("Non-existent Department");

        // Mock the repository to return an empty Optional for the department ID 99L
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());

        // Call the update method and assert that an exception is thrown
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> departmentService.updateDepartment(departmentDTO));

        // Assert that the exception message is as expected
        assertEquals("Department not found with id: " + departmentDTO.getId(), thrown.getMessage());

        // Verify that findById was called once
        verify(departmentRepository, times(1)).findById(99L);
        // Ensure save was not called since the department wasn't found
        verify(departmentRepository, never()).save(any(Department.class));
    }



    private static List<DepartmentDTO> fetchDepartmentsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the list of departments from JSON file and map them to DepartmentDTOs
        List<DepartmentDTO> departmentDTOList = objectMapper.readValue(
                new File("src/main/resources/data/departments.json"),
                new TypeReference<List<DepartmentDTO>>() {}
        );
        departmentDTOList.get(0).setId(1L);
        departmentDTOList.get(1).setId(2L);
        departmentDTOList.get(2).setId(3L);
        // You can further modify or populate additional fields here if needed
        return departmentDTOList;
    }




}


