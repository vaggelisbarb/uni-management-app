package com.example.demo.repository;


import com.example.demo.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * Find a department by name (derived query method)
     *
     * @param name the name of the department
     * @return {@link Optional} the Department object if found
     * @see Optional
     * @see Department
     */
    Optional<Department> findByName(String name);

    /**
     * Find departments with a budget greater than a given amount
     *
     * @param minBudget the minimum amount of budget
     * @return {@link List} of Department objects with the specified budget greater than or equal to the specified amount
     * @see List
     * @see Department
     */
    List<Department> findByBudgetGreaterThan(double minBudget);

    /**
     * Find departments that have a budget within a specific range
     *
     * @param minBudget the minimum budget
     * @param maxBudget the maximum budget
     * @return {@link List} of Department objects with the specified budget within the given specified range
     * @see List
     * @see Department
     */
    List<Department> findByBudgetBetween(double minBudget, double maxBudget);

    /**
     * Find departments founded after a specific year
     *
     * @param year the year to search
     * @return {@link List} of Department objects which founded after the specified year
     * @see List
     * @see Department
     */
    List<Department> findByFoundedYearAfter(int year);

    // Find all departments that have more than a certain number of students
    List<Department> findByStudentCountGreaterThan(int studentCount);

    // Find all departments that have a certain professor as faculty member
    @Query("SELECT d FROM Department d JOIN d.faculty f WHERE f.id = :professorId")
    List<Department> findByProfessor(@Param("professorId") Long professorId);

    // Find departments that have a specific professor as Head of Department
    @Query("SELECT d FROM Department d WHERE d.headOfDepartment.id = :professorId")
    Optional<Department> findByHeadOfDepartment(@Param("professorId") Long professorId);

    // Find all departments offering a specific degree program
    @Query("SELECT d FROM Department d JOIN d.degreePrograms dp WHERE dp = :program")
    List<Department> findByDegreeProgram(@Param("program") String program);

    // Find all departments located in a specific city
    @Query("SELECT d FROM Department d WHERE d.address.city = :city")
    List<Department> findByCity(@Param("city") String city);

    // Find the department with the highest budget
    @Query("SELECT d FROM Department d ORDER BY d.budget DESC LIMIT 1")
    Optional<Department> findTopByOrderByBudgetDesc();

}
