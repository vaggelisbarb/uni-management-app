package com.example.demo.util;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.ProfessorDTO;
import com.example.demo.model.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        if (course == null)
            return null;

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getCredits(),
                course.getCategory(),
                course.getLevel(),
                ProfessorMapper.toDTO(course.getProfessor()),
                DepartmentMapper.toDTO(course.getDepartment()),
                CommonMapper.scheduleInfoToDTO(course.getScheduleInfo()),
                course.isElective(),
                course.getGradingPolicy(),
                course.getSyllabus());
    }

    public static List<CourseDTO> listToDTOs(List<Course> courses) {
        List<CourseDTO> dtoList = new ArrayList<>();

        for (Course course : courses)
            dtoList.add(toDTO(course));

        return dtoList;
    }

    public static Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null)
            return null;

        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setCredits(courseDTO.getCredits());
        course.setCategory(courseDTO.getCategory());
        course.setLevel(courseDTO.getLevel());
        course.setProfessor(ProfessorMapper.toEntity(courseDTO.getProfessorDTO()));
        course.setDepartment(DepartmentMapper.toEntity(courseDTO.getDepartmentDTO()));
        course.setScheduleInfo(CommonMapper.scheduleInfoToEntity(courseDTO.getScheduleDTO()));
        course.setElective(courseDTO.isElective());
        course.setGradingPolicy(courseDTO.getGradingPolicy());
        course.setSyllabus(courseDTO.getSyllabus());

        return course;
    }

    public static List<Course> listToEntities(List<CourseDTO> courseDTOs) {
        List<Course> entities = new ArrayList<>();

        for (CourseDTO courseDTO : courseDTOs)
            entities.add(toEntity(courseDTO));

        return entities;
    }



}
