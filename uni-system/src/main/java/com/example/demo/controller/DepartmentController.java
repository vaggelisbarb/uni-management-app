package com.example.demo.controller;


import com.example.demo.dto.department.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create a new department
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }
    // Find department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> department  = departmentService.findById(id);
        return department.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentDTO);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    // Get all departments
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Find department by name
    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable String name) {
        Optional<DepartmentDTO> department = departmentService.findByName(name);
        return department.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get departments founded after a specific year
    @GetMapping("/founded-after/{year}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsFoundedAfter(@PathVariable int year) {
        List<DepartmentDTO> departments = departmentService.findByFoundedYearAfter(year);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get departments by budget range
    @GetMapping("/budget-range")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByBudgetRange(@RequestParam double minBudget, @RequestParam double maxBudget) {
        List<DepartmentDTO> departments = departmentService.findByBudgetBetween(minBudget, maxBudget);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get departments with student count greater than a specified number
    @GetMapping("/student-count/{count}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByStudentCountGreaterThan(@PathVariable int count) {
        List<DepartmentDTO> departments = departmentService.findByStudentCountGreaterThan(count);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get department with the highest budget
    @GetMapping("/highest-budget")
    public ResponseEntity<DepartmentDTO> getDepartmentWithHighestBudget() {
        Optional<DepartmentDTO> department = departmentService.findDepartmentWithHighestBudget();
        return department.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a department by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (!departmentService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}