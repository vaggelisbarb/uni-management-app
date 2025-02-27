package com.example.demo.controller;

import com.example.demo.dto.student.StudentDTO;
import com.example.demo.service.StudentService;
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

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @Operation(summary = "Create a Student", description = "Creates a new student and returns the created student")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Student was successfully created"),
            @ApiResponse(responseCode = "400", description = "Student Creation failed")
    })
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws Exception{
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        if (createdStudent == null)
            throw new Exception("Student already exists in the database for student " + studentDTO);

        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Update existing student", description = "Update an existing student and returns the updated entity")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student was successfully updated"),
            @ApiResponse(responseCode = "401", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
   @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) throws NoSuchElementException {
        StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
        if (updatedStudent == null)
            throw new NoSuchElementException("Student not found in the database for student " + studentDTO);

        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @Operation(summary = "Delete a student", description = "Deletes a Student by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Student was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) throws NoSuchElementException {
        if (!studentService.existsById(id))
            throw new NoSuchElementException("Student not found in the database for student " + id);

        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get a student by ID", description = "Retrieves a student by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) throws NoSuchElementException {
        StudentDTO student = studentService.getStudentById(id);
        if (student == null)
            throw new NoSuchElementException("Student not found in the database for student " + id);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Operation(summary = "Get all students", description = "Retrieves all students")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Students found")
    })
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws Exception{
        List<StudentDTO> students = studentService.getAllStudents();
        if (students == null)
            throw new Exception("No students found in the database");

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Get the total number of students", description = "Retrieves the total number of students")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Total number of students found")
    })
    @GetMapping("/totalStudents")
    public ResponseEntity<Long> getTotalStudents() throws Exception{
        long totalStudents = studentService.getTotalStudents();
        if (totalStudents == 0)
            throw new Exception("No students found in the database");

        return new ResponseEntity<>(totalStudents, HttpStatus.OK);
    }






}
