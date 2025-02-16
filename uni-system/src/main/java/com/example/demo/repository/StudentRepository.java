package com.example.demo.repository;

import com.example.demo.model.entities.Department;
import com.example.demo.model.entities.Professor;
import com.example.demo.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// Repository for Student entity operations (CRUD operations)
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);
    List<Student> findByNationality(String nationality); // Find students by nationality
    List<Student> findByGpaGreaterThan(double gpa); // Find students by GPA greater than a given value
    List<Student> findByAdvisor(Professor advisor); // Find students with a specific advisor
    List<Student> findByIsScholarshipHolderTrue(); // Find students who are scholarship holders

    // Custom queries
    @Query("SELECT s FROM Student s WHERE s.department.id = :departmentId")
    List<Student> findStudentsByDepartmentId(@Param("departmentId") Long departmentId); // Find students by department

    @Query("SELECT s FROM Student s WHERE s.gpa >= :gpa AND s.creditsCompleted >= :credits")
    List<Student> findStudentsByGpaAndCredits(@Param("gpa") double gpa, @Param("credits") int credits); // Find students with GPA and credits completed conditions

    @Query("SELECT s FROM Student s JOIN s.enrolledCourses c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId); // Find students enrolled in a specific course based on it's ID

    @Query("SELECT COUNT(s) FROM Student s WHERE s.department = :department")
    long countStudentsByDepartment(@Param("department") Department department); // Count students by department

    @Query("SELECT s FROM Student s JOIN s.achievements a WHERE a = :achievement")
    List<Student> findStudentsByAchievement(@Param("achievement") String achievement); // Find students who have achieved a specific award

}
