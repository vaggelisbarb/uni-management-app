package com.example.demo.controller;


import com.example.demo.dto.department.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Controller for managing departments.
 */
@RestController
@RequestMapping("/api/departments")
@Tag(name = "Departments", description = "APIs for managing departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Constructs the DepartmentController with the provided DepartmentService.
     *
     * @param departmentService the department service
     */
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    /**
     * Creates a new department.
     *
     * @param departmentDTO the department data transfer object
     * @return the created department
     */
    @Operation(summary = "Create a Department", description = "Creates a new department and returns the created department")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Department created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) throws Exception {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        if (createdDepartment == null)
            throw new Exception("Could not create department");

        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }
    /**
     * Retrieves a department by its ID.
     *
     * @param id the department ID
     * @return the department if found, otherwise 404 NOT FOUND
     */
    @Operation(summary = "Find department by ID", description = "Retrieves a department based on its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department found"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) throws NoSuchElementException{
        Optional<DepartmentDTO> department  = departmentService.findById(id);
        return department
                .map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseThrow(() -> new NoSuchElementException("Department not found with id : " + id));
    }

    /**
     * Updates an existing department.
     *
     * @param departmentDTO the department data transfer object
     * @return the updated department
     */
    @Operation(summary = "Update an existing department", description = "Updates an existing department and returns the updated entity")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) throws NoSuchElementException{
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentDTO);

        if (updatedDepartment == null) {
            throw new NoSuchElementException("Could not update department with id : " + departmentDTO.getId());
        }
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    /**
     * Deletes a department by ID.
     *
     * @param id the department ID
     * @return 204 NO CONTENT if deleted, otherwise 404 NOT FOUND
     */
    @Operation(summary = "Delete a department", description = "Deletes a Department by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) throws NoSuchElementException{
        if (!departmentService.existsById(id))
            throw new NoSuchElementException("Department not found with id " + id);

        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all departments.
     *
     * @return the list of all departments
     */
    @Operation(summary = "Get all departments", description = "Retrieves a list of all departments")
    @ApiResponse(responseCode = "200", description = "List of departments retrieved successfully")
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() throws Exception {
        List<DepartmentDTO> departments = departmentService.findAll();
        if (departments == null)
            throw new Exception("No departments found");

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    /**
     * Retrieves a department by name.
     *
     * @param name the department name
     * @return the department if found, otherwise 404 NOT FOUND
     */
    @Operation(summary = "Find department by name", description = "Retrieves a department by its name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department found"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable String name) throws NoSuchElementException{
        Optional<DepartmentDTO> department = departmentService.findByName(name);
        return department
                .map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseThrow(() -> new NoSuchElementException ("Could not find department with name " + name));
    }

    /**
     * Retrieves departments founded after a specific year.
     *
     * @param year the foundation year threshold
     * @return the list of departments
     */
    @Operation(summary = "Find departments founded after a specific year", description = "Retrieves departments founded after a given year")
    @ApiResponse(responseCode = "200", description = "List of departments retrieved successfully")
    @GetMapping("/founded-after/{year}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsFoundedAfter(@PathVariable int year) throws NoSuchElementException{
        List<DepartmentDTO> departments = departmentService.findByFoundedYearAfter(year);
        if (departments == null)
            throw new NoSuchElementException("No departments found with foundation year after " + year);

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    /**
     * Retrieves the department with the highest budget.
     *
     * @return the department with the highest budget if found, otherwise 404 NOT FOUND
     */
    @Operation(summary = "Find departments by budget range", description = "Retrieves departments within a specified budget range")
    @ApiResponse(responseCode = "200", description = "List of departments retrieved successfully")
    @GetMapping("/budget-range")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByBudgetRange(@RequestParam double minBudget, @RequestParam double maxBudget) throws NoSuchElementException{
        List<DepartmentDTO> departments = departmentService.findByBudgetBetween(minBudget, maxBudget);
        if (departments == null)
            throw new NoSuchElementException("No departments found with budget range between " + minBudget + " and " + maxBudget);

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get departments with student count greater than a specified number
    @Operation(summary = "Find departments with student count greater than a specified number", description = "Retrieves departments where student count exceeds a given number")
    @ApiResponse(responseCode = "200", description = "List of departments retrieved successfully")
    @GetMapping("/student-count/{count}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByStudentCountGreaterThan(@PathVariable int count) throws NoSuchElementException{
        List<DepartmentDTO> departments = departmentService.findByStudentCountGreaterThan(count);
        if (departments == null)
            throw new NoSuchElementException("No departments found with student count greater than " + count);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get department with the highest budget
    @Operation(summary = "Get department with the highest budget", description = "Retrieves the department with the highest budget")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No department found")
    })
    @GetMapping("/highest-budget")
    public ResponseEntity<DepartmentDTO> getDepartmentWithHighestBudget() throws NoSuchElementException{
        Optional<DepartmentDTO> department = departmentService.findDepartmentWithHighestBudget();
        return department.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseThrow(() -> new NoSuchElementException("No department found with highest budget"));
    }
}