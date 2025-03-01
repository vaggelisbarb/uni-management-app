package com.example.demo.util;

import com.example.demo.dto.ProfessorDTO;
import com.example.demo.model.entities.Professor;

public class ProfessorMapper {

    public static ProfessorDTO toDTO(Professor professor) {

        if (professor == null)
            return null;

        return new ProfessorDTO(
                professor.getId(),
                professor.getName(),
                CommonMapper.contactInfoToDTO(professor.getContactInfo()),
                DepartmentMapper.toDTO(professor.getDepartment()),
                CourseMapper.listToDTOs(professor.getCurrentCourses()),
                professor.getFieldOfExpertise());
    }

    public static Professor toEntity(ProfessorDTO professorDTO) {
        if (professorDTO == null)
            return null;

        Professor professor = new Professor();
        professor.setId(professorDTO.getId());
        professor.setName(professorDTO.getName());
        professor.setContactInfo(CommonMapper.contactInfoToEntity(professorDTO.getContactInfo()));
        professor.setDepartment(DepartmentMapper.toEntity(professorDTO.getDepartment()));
        professor.setCurrentCourses(CourseMapper.listToEntities(professorDTO.getCurrentCourses()));
        professor.setFieldOfExpertise(professorDTO.getFieldOfExpertise());

        return professor;
    }

}
