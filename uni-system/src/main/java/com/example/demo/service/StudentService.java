package com.example.demo.service;

import com.example.demo.dto.student.StudentDTO;
import com.example.demo.model.entities.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService() { }

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add a new student to the database
    // Add a new student to the database
    @Transactional
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    // Retrieve all students from the database
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a student by their ID
    public StudentDTO getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(StudentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Update a student's information in the database
    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    // Delete a student from the database
    @Transactional
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    // Get the total number of students in the database
    public long getTotalStudents() {
        return studentRepository.count();
    }

    // Additional methods using custom queries

    // Find students by nationality
    public List<StudentDTO> findStudentsByNationality(String nationality) {
        return studentRepository.findByNationality(nationality).stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find students with GPA greater than the given value
    public List<StudentDTO> findStudentsByGpaGreaterThan(double gpa) {
        return studentRepository.findByGpaGreaterThan(gpa).stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find students who are scholarship holders
    public List<StudentDTO> findScholarshipHolders() {
        return studentRepository.findByIsScholarshipHolderTrue().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find students with GPA and credits completed conditions
    public List<StudentDTO> findStudentsByGpaAndCredits(double gpa, int credits) {
        return studentRepository.findStudentsByGpaAndCredits(gpa, credits).stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find students by enrolled course
    public List<StudentDTO> findStudentsByCourseId(Long courseId) {
        return studentRepository.findStudentsByCourseId(courseId).stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }



    // Find students by achievement
    public List<StudentDTO> findStudentsByAchievement(String achievement) {
        return studentRepository.findStudentsByAchievement(achievement).stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
