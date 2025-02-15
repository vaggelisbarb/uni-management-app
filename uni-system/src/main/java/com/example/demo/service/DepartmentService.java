package com.example.demo.service;

import com.example.demo.model.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService() { }

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Create a new department
    public Department createDepartment(@Valid Department department) {
        // Department object will be validated before being processed
        return departmentRepository.save(department);
    }

    // Delete a department by id
    public void deleteDepartment(Long id) {
        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if (departmentOpt.isPresent()) {
            departmentRepository.delete(departmentOpt.get());
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }

    public void updateDepartment(@Valid Department department) {
        Optional<Department> departmentOpt = departmentRepository.findById(department.getId());
        if (departmentOpt.isPresent()) {
            Department existingDepartment = departmentOpt.get();
            existingDepartment.setName(department.getName());
            existingDepartment.setDescription(department.getDescription());
            existingDepartment.setFaculty(department.getFaculty());
            existingDepartment.setHeadOfDepartment(department.getHeadOfDepartment());
            existingDepartment.setAddress(department.getAddress());
            existingDepartment.setBuildingLocation(department.getBuildingLocation());
            existingDepartment.setContactInfo(department.getContactInfo());
            existingDepartment.setDegreePrograms(department.getDegreePrograms());
            existingDepartment.setCoursesOffered(department.getCoursesOffered());
            existingDepartment.setResearchAreas(department.getResearchAreas());
            existingDepartment.setStudentCount(department.getStudentCount());
            existingDepartment.setWebsite(department.getWebsite());
            existingDepartment.setFoundedYear(department.getFoundedYear());
            existingDepartment.setBudget(department.getBudget());
            existingDepartment.setAffiliatedOrganizations(department.getAffiliatedOrganizations());
            departmentRepository.save(department);
        } else {
            throw new RuntimeException("Department not found with id: " + department.getId());
        }
    }

    // Delete all departments
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

    // Find department by name
    public Optional<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    // Find departments by research area
    public List<Department> findByResearchArea(String researchArea) {
        return departmentRepository.findByResearchAreasContaining(researchArea);
    }

    // Find departments by budget range
    public List<Department> findByBudgetBetween(double minBudget, double maxBudget) {
        return departmentRepository.findByBudgetBetween(minBudget, maxBudget);
    }

    // Find departments founded after a specific year
    public List<Department> findByFoundedYearAfter(int year) {
        return departmentRepository.findByFoundedYearAfter(year);
    }

    // Find departments with student count greater than a specified number
    public List<Department> findByStudentCountGreaterThan(int studentCount) {
        return departmentRepository.findByStudentCountGreaterThan(studentCount);
    }

    // Find departments by professor
    public List<Department> findByProfessor(Long professorId) {
        return departmentRepository.findByProfessor(professorId);
    }

    // Find department by head of department (professor)
    public Optional<Department> findByHeadOfDepartment(Long professorId) {
        return departmentRepository.findByHeadOfDepartment(professorId);
    }

    // Find departments offering a specific degree program
    public List<Department> findByDegreeProgram(String program) {
        return departmentRepository.findByDegreeProgram(program);
    }

    // Find departments located in a specific city
    public List<Department> findByCity(String city) {
        return departmentRepository.findByCity(city);
    }

    // Find department with the highest budget
    public Optional<Department> findDepartmentWithHighestBudget() {
        return departmentRepository.findTopByOrderByBudgetDesc();
    }

    // Find departments specialized in AI
    public List<Department> findDepartmentsSpecializedInAI() {
        return departmentRepository.findDepartmentsSpecializedInAI();
    }

    // Check if a department exists by id
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }


}
