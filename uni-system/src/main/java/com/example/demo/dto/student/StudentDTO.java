package com.example.demo.dto.student;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.common.AddressDTO;
import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.dto.common.PersonalInfoDTO;
import com.example.demo.dto.department.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Student")
public class StudentDTO {

    @Schema(description = "Unique identifier for the student", example = "1")
    private Long id;

    @JsonProperty("personalInfo")
    @Schema(description = "Personal information of the student", implementation = PersonalInfoDTO.class)
    private PersonalInfoDTO personalInfoDTO; // Only personal name or publicly available details

    @JsonProperty("contactInfo")
    @Schema(description = "Contact details of the student", implementation = ContactInfoDTO.class)
    private ContactInfoDTO contactInfoDTO; // Only contact details or publicly available details

    @Schema(description = "Nationality of the student", example = "Greek")
    private String nationality; // Only necessary nationality info

    @JsonProperty("enrollmentInfo")
    @Schema(description = "Enrollment details of the student", implementation = EnrollmentInfoDTO.class)
    private EnrollmentInfoDTO enrollmentInfoDTO; // Non-sensitive enrollment data

    @Schema(description = "Department the student belongs to", implementation = DepartmentDTO.class)
    private DepartmentDTO departmentDTO; // Basic departmentDTO info

    @JsonProperty("address")
    @Schema(description = "Address information of the student", implementation = AddressDTO.class)
    private AddressDTO addressDTO; // Only necessary address info

    @JsonIgnore  // Prevents infinite recursion
    @ArraySchema(schema = @Schema(implementation = CourseDTO.class))
    @Schema(description = "List of courses the student is currently enrolled in")
    private List<CourseDTO> enrolledCourses; // Relevant courses for a public view

    @Schema(description = "Indicates if the student has a scholarship", example = "true")
    private boolean scholarshipHolder; // Only if relevant for the context
}
