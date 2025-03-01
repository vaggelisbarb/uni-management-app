package com.example.demo;


import com.example.demo.dto.student.StudentDTO;
import com.example.demo.model.entities.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.StudentMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private List<StudentDTO> studentDTOList;
    private List<Student> studentList;

    @BeforeEach
    public void setUp() throws IOException {
        studentDTOList = fetchStudentsFromJson();
        studentList = studentDTOList.stream().map(StudentMapper::toEntity).collect(Collectors.toList());
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll())
                .thenReturn(studentDTOList.stream()
                        .map(StudentMapper::toEntity)
                        .collect(toList()));
        List<StudentDTO> actualStudents = studentService.getAllStudents();

        for (StudentDTO student : actualStudents)
            System.out.println("Actual students: " + JsonUtil.toJson(actualStudents));

        for (StudentDTO student : studentDTOList)
            System.out.println("Expected students: " + JsonUtil.toJson(studentDTOList));

        assertEquals(studentDTOList, actualStudents);
        assertEquals(4, actualStudents.size());
        assertIterableEquals(studentDTOList, actualStudents, "The returned students should match the expected list.");
    }

    @Test
    void testGetStudentById() {
        StudentDTO expectedStudent = studentDTOList.get(0);
        Student studentEntity = StudentMapper.toEntity(expectedStudent);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntity));

        StudentDTO actualStudent = studentService.getStudentById(1L);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testAddStudent() {
        StudentDTO newStudent = studentDTOList.get(0);
        Student studentEntity = StudentMapper.toEntity(newStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(studentEntity);

        StudentDTO savedStudent = studentService.addStudent(newStudent);
        assertEquals(newStudent, savedStudent);
    }

    @Test
    void testUpdateStudent() {
        StudentDTO updatedStudent = studentDTOList.get(0);
        Student studentEntity = StudentMapper.toEntity(updatedStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(studentEntity);

        StudentDTO result = studentService.updateStudent(updatedStudent);
        assertEquals(updatedStudent, result);
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);

        assertDoesNotThrow(() -> studentService.deleteStudent(1L));
    }

    @Test
    void testGetTotalStudents() {
        when(studentRepository.count()).thenReturn(4L);

        long totalStudents = studentService.getTotalStudents();
        assertEquals(4, totalStudents);
    }

    @Test
    void testFindStudentsByNationality() {
        String nationality = "Greek";
        List<Student> filteredStudents = studentList.stream()
                .filter(student -> nationality.equals(student.getNationality()))
                .collect(Collectors.toList());

        when(studentRepository.findByNationality(nationality)).thenReturn(filteredStudents);
        List<StudentDTO> actualStudents = studentService.findStudentsByNationality(nationality);

        assertEquals(filteredStudents.size(), actualStudents.size());
        assertTrue(actualStudents.stream().allMatch(student -> nationality.equals(student.getNationality())), "All students should have the specified nationality.");
    }

    @Test
    void testFindStudentsByGpaGreaterThan() {
        double gpa = 3.5;
        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getGpa() > gpa)
                .collect(Collectors.toList());

        when(studentRepository.findByGpaGreaterThan(gpa)).thenReturn(filteredStudents);
        List<StudentDTO> actualStudents = studentService.findStudentsByGpaGreaterThan(gpa);

        assertEquals(filteredStudents.size(), actualStudents.size());
    }


    @Test
    void testFindStudentsByGpaAndCredits() {
        double gpa = 3.0;
        int credits = 90;
        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getGpa() >= gpa && student.getCreditsCompleted() >= credits)
                .collect(Collectors.toList());

        when(studentRepository.findStudentsByGpaAndCredits(gpa, credits)).thenReturn(filteredStudents);
        List<StudentDTO> actualStudents = studentService.findStudentsByGpaAndCredits(gpa, credits);

        assertEquals(filteredStudents.size(), actualStudents.size());
    }



    private List<StudentDTO> fetchStudentsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignore unknown properties

        // Load the list of departments from JSON file and map them to DepartmentDTOs
        List<StudentDTO> studentDTOList = objectMapper.readValue(
                new File("src/main/resources/data/students.json"),
                new TypeReference<List<StudentDTO>>() {}
        );

        for (int i = 0; i < studentDTOList.size(); i++)
            studentDTOList.get(i).setId((long) (i + 1));

        return studentDTOList;
    }

}
