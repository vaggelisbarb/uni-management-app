package com.example.demo;

import com.example.demo.dto.department.DepartmentDTO;
import com.example.demo.model.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.DepartmentMapper;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            when(departmentRepository.save(any(Department.class))).thenReturn(DepartmentMapper.toEntity(departmentDTO));
            DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);
            assertNotNull(savedDepartment);
            assertEquals(departmentDTO.getName(), savedDepartment.getName());
        }
        verify(departmentRepository, times(departmentDTOList.size())).save(any(Department.class));
    }

    @Test
    void testFindByID() {
        Department department = DepartmentMapper.toEntity(departmentDTOList.get(0));
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<DepartmentDTO> fetchedDepartment = departmentService.findById(1L);
        assertNotNull(fetchedDepartment);
        assertEquals(department.getName(), fetchedDepartment.get().getName());
    }

    @Test
    void testFindAll() {
        when(departmentRepository.findAll()).thenReturn(departmentDTOList.stream().map(DepartmentMapper::toEntity).collect(toList()));
        List<DepartmentDTO> fetchedDepartments = departmentService.findAll();
        assertNotNull(fetchedDepartments);
        assertEquals(departmentDTOList.size(), fetchedDepartments.size());
    }

    @Test
    void testFindAllEmpty() {
        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());
        List<DepartmentDTO> fetchedDepartments = departmentService.findAll();
        assertNotNull(fetchedDepartments);
        assertTrue(fetchedDepartments.isEmpty());
    }

    @Test
    void testDeleteDepartment() {
        Department department = DepartmentMapper.toEntity(departmentDTOList.get(0));
        department.setId(1L);
        when(departmentRepository.existsById(1L)).thenReturn(true);
        departmentService.deleteDepartment(1L);
        verify(departmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNonExistentDepartment() {
        when(departmentRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> departmentService.deleteDepartment(99L));
        verify(departmentRepository, never()).deleteById(99L);
    }


    @Test
    void testUpdateDepartmentExists() {
        DepartmentDTO departmentDTO = departmentDTOList.get(0);
        Department department = DepartmentMapper.toEntity(departmentDTO);
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentDTO);
        assertNotNull(updatedDepartment);
        assertEquals(departmentDTO.getName(), updatedDepartment.getName());
    }

    @Test
    void testUpdateDepartmentNotFound() {
        DepartmentDTO departmentDTO = departmentDTOList.get(0);
        departmentDTO.setId(99L);
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> departmentService.updateDepartment(departmentDTO));
    }

    @Test
    void testFindByBudgetBetween_NoMatch() {
        when(departmentRepository.findByBudgetBetween(10000000.00, 20000000.00)).thenReturn(Collections.emptyList());
        List<DepartmentDTO> result = departmentService.findByBudgetBetween(10000000.00, 20000000.00);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByBudgetBetween_MultipleMatches() {
        List<Department> matchingDepartments = departmentDTOList.stream()
                .map(DepartmentMapper::toEntity)
                .filter(dept -> dept.getBudget() >= 4000000 && dept.getBudget() <= 5000000)
                .collect(toList());
        when(departmentRepository.findByBudgetBetween(4000000, 5000000)).thenReturn(matchingDepartments);
        List<DepartmentDTO> result = departmentService.findByBudgetBetween(4000000, 5000000);
        assertEquals(matchingDepartments.size(), result.size());
    }

    @Test
    void testFindByFoundedYearAfter() {
        when(departmentRepository.findByFoundedYearAfter(1980))
                .thenReturn((List<Department>) departmentDTOList.stream().map(DepartmentMapper::toEntity).filter(d -> d.getFoundedYear() > 1980).collect(Collectors.toList()));

        List<DepartmentDTO> result = departmentService.findByFoundedYearAfter(1980);
        assertNotNull(result);
        assertTrue(result.stream().allMatch(d -> d.getFoundedYear() > 1980));
        assertEquals(1, result.size());
    }

    @Test
    void testFindByStudentCountGreaterThan() {
        when(departmentRepository.findByStudentCountGreaterThan(500))
                .thenReturn((List<Department>) departmentDTOList.stream().map(DepartmentMapper::toEntity).filter(d -> d.getStudentCount() > 500).collect(Collectors.toList()));

        List<DepartmentDTO> result = departmentService.findByStudentCountGreaterThan(500);
        assertNotNull(result);
        assertTrue(result.stream().allMatch(d -> d.getStudentCount() > 500));
        assertEquals(2, result.size());
    }



    @Test
    void testExistsById() {
        when(departmentRepository.existsById(1L)).thenReturn(true);
        when(departmentRepository.existsById(99L)).thenReturn(false);

        assertTrue(departmentService.existsById(1L));
        assertFalse(departmentService.existsById(99L));
    }



    private static List<DepartmentDTO> fetchDepartmentsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the list of departments from JSON file and map them to DepartmentDTOs
        List<DepartmentDTO> departmentDTOList = objectMapper.readValue(
                new File("src/main/resources/data/departments.json"),
                new TypeReference<List<DepartmentDTO>>() {}
        );
        for (int i = 0; i < departmentDTOList.size(); i++) {
            departmentDTOList.get(i).setId((long) (i + 1));
        }
        // You can further modify or populate additional fields here if needed
        return departmentDTOList;
    }




}


