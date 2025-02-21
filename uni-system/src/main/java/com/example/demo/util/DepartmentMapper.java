package com.example.demo.util;

import com.example.demo.dto.department.DepartmentDTO;
import com.example.demo.model.entities.Course;
import com.example.demo.model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getBuildingLocation(),
                department.getResearchAreas(),
                department.getStudentCount(),
                department.getWebsite(),
                department.getFoundedYear(),
                coursesToStringList(department.getCoursesOffered()),
                department.getDegreePrograms()
        );
    }

    public static Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        department.setBuildingLocation(departmentDTO.getBuildingLocation());
        department.setResearchAreas(departmentDTO.getResearchAreas());
        department.setStudentCount(departmentDTO.getStudentCount());
        department.setWebsite(departmentDTO.getWebsite());
        department.setFoundedYear(departmentDTO.getFoundedYear());
        department.setDegreePrograms(departmentDTO.getDegreePrograms() != null ? departmentDTO.getDegreePrograms() : new ArrayList<>());

        // Convert course names to Course entities
        department.setCoursesOffered(coursesFromStringList(departmentDTO.getCourses() != null ? departmentDTO.getCourses() : new ArrayList<>()));

        return department;
    }

    private static List<String> coursesToStringList(List<Course> courses) {
        if (courses == null) {
            return new ArrayList<>(); // Return an empty list if courses is null
        }
        return courses.stream()
                .map(Course::getName) // Assuming Course has a getName() method
                .collect(Collectors.toList());
    }

    private static List<Course> coursesFromStringList(List<String> courseNames) {
        return courseNames.stream()
                .map(name -> {
                    Course course = new Course();
                    course.setName(name); // Ensure Course entity has a setName() method
                    return course;
                })
                .collect(Collectors.toList());
    }
}
