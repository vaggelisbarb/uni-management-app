package com.example.demo.dto;


import com.example.demo.dto.department.DepartmentDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a course")
public class CourseDTO {

    @Schema(description = "Unique identifier for the course", example = "101")
    private Long id;

    @Schema(description = "Name of the course", example = "Introduction to Computer Science")
    private String name;

    @Schema(description = "Brief description of the course", example = "This course introduces fundamental concepts of programming and computer science.")
    private String description;

    @Schema(description = "Number of credits assigned to the course", example = "3")
    private int credits;

    @Schema(description = "Category of the course", example = "Core")
    private String category;

    @Schema(description = "Level of the course (Undergraduate/Postgraduate)", example = "Undergraduate")
    private String level;

    @Schema(description = "Professor teaching the course")
    private ProfessorDTO professorDTO;

    @Schema(description = "Department offering the course")
    private DepartmentDTO departmentDTO;

    @Schema(description = "Schedule details of the course")
    private ScheduleDTO scheduleDTO;

    @Schema(description = "Indicates whether the course is elective", example = "false")
    private boolean elective;

    @Schema(description = "Grading policy of the course", example = "Midterms 40%, Final Exam 50%, Assignments 10%")
    private String gradingPolicy;

    @Schema(description = "Syllabus outline of the course", example = "Week 1: Introduction\nWeek 2: Data Structures\nWeek 3: Algorithms...")
    private String syllabus;

}
