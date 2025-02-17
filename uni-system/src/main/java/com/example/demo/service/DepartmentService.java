package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.model.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.util.DepartmentMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Create a new department
    public DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDTO(savedDepartment);
    }

    // Delete a department by id
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    // Update department
    public DepartmentDTO updateDepartment(@Valid DepartmentDTO departmentDTO) {
        Optional<Department> departmentOpt = departmentRepository.findById(departmentDTO.getId());
        if (departmentOpt.isPresent()) {
            Department department = DepartmentMapper.toEntity(departmentDTO);
            department.setId(departmentDTO.getId()); // Ensure ID is retained
            Department updatedDepartment = departmentRepository.save(department);
            return DepartmentMapper.toDTO(updatedDepartment);
        } else {
            throw new RuntimeException("Department not found with id: " + departmentDTO.getId());
        }
    }

    // Find department by name
    public Optional<DepartmentDTO> findByName(String name) {
        return departmentRepository.findByName(name)
                .map(DepartmentMapper::toDTO);
    }

    // Find all departments
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find departments by budget range
    public List<DepartmentDTO> findByBudgetBetween(double minBudget, double maxBudget) {
        return departmentRepository.findByBudgetBetween(minBudget, maxBudget)
                .stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find departments founded after a specific year
    public List<DepartmentDTO> findByFoundedYearAfter(int year) {
        return departmentRepository.findByFoundedYearAfter(year)
                .stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find departments with student count greater than a specified number
    public List<DepartmentDTO> findByStudentCountGreaterThan(int studentCount) {
        return departmentRepository.findByStudentCountGreaterThan(studentCount)
                .stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find department with the highest budget
    public Optional<DepartmentDTO> findDepartmentWithHighestBudget() {
        return departmentRepository.findTopByOrderByBudgetDesc()
                .map(DepartmentMapper::toDTO);
    }

    // Check if a department exists by id
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }
}
