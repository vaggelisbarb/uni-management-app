package com.example.demo.dto;


import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.dto.department.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a professor")
public class ProfessorDTO {

    @Schema(description = "Unique identifier for the professor", example = "42")
    private Long id; // Adding unique ID for updates and references

    @Schema(description = "Full name of the professor", example = "Dr. John Smith")
    private String name;

    @Schema(description = "Contact details of the professor", implementation = ContactInfoDTO.class)
    private ContactInfoDTO contactInfo;

    @Schema(description = "Department the professor belongs to", implementation = DepartmentDTO.class)
    private DepartmentDTO department;

    @JsonIgnore  // Prevents infinite recursion
    @ArraySchema(schema = @Schema(implementation = CourseDTO.class))
    @Schema(description = "List of current courses taught by the professor")
    private List<CourseDTO> currentCourses;

    @Schema(description = "Professor's field of expertise", example = "Artificial Intelligence")
    private String fieldOfExpertise; // Useful for filtering/searching professors


}
