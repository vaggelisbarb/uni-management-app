package com.example.demo.util;

import com.example.demo.dto.CourseDTO;
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
                CourseMapper.listToDTOs(department.getCoursesOffered()),
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
        department.setCoursesOffered(CourseMapper.listToEntities(departmentDTO.getCourses()));
        return department;
    }


}
