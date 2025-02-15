package com.example.demo.service;

import com.example.demo.model.entities.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService() { }

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add a new student to the database
    @Transactional
    public Student addStudent(Student student) {
        // Add any validation logic here, if necessary
        return studentRepository.save(student);
    }

    // Retrieve all students from the database
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    // Retrieve a student by their ID
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new RuntimeException("Student not found with id: " + id)); // Can create a custom exception
    }

    // Update a student's information in the database
    @Transactional
    public Student updateStudent(Student student) {
        // Optionally check if the student exists before updating
        return studentRepository.save(student);
    }

    // Delete a student from the database
    @Transactional
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id); // Can create a custom exception
        }
    }

    // Get the total number of students in the database
    public long getTotalStudents() {
        return studentRepository.count();
    }

    // Additional methods using custom queries

    // Find students by nationality
    public List<Student> findStudentsByNationality(String nationality) {
        return studentRepository.findByNationality(nationality);
    }

    // Find students with GPA greater than the given value
    public List<Student> findStudentsByGpaGreaterThan(double gpa) {
        return studentRepository.findByGpaGreaterThan(gpa);
    }

    // Find students who are scholarship holders
    public List<Student> findScholarshipHolders() {
        return studentRepository.findByIsScholarshipHolderTrue();
    }

    // Find students with GPA and credits completed conditions
    public List<Student> findStudentsByGpaAndCredits(double gpa, int credits) {
        return studentRepository.findStudentsByGpaAndCredits(gpa, credits);
    }

    // Find students by enrolled course
    public List<Student> findStudentsByCourseId(Long courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }


    // Find students by achievement
    public List<Student> findStudentsByAchievement(String achievement) {
        return studentRepository.findStudentsByAchievement(achievement);
    }


}
