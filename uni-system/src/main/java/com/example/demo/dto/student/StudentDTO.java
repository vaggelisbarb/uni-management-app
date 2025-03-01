package com.example.demo.dto.student;

import com.example.demo.dto.common.AddressDTO;
import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.dto.common.PersonalInfoDTO;
import com.example.demo.dto.department.DepartmentDTO;
import com.example.demo.model.entities.Course;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Schema(description = "Personal information of the student")
    private PersonalInfoDTO personalInfoDTO; // Only personal name or publicly available details

    @JsonProperty("contactInfo")
    @Schema(description = "Contact details of the student")
    private ContactInfoDTO contactInfoDTO; // Only contact details or publicly available details

    @Schema(description = "Nationality of the student", example = "Greek")
    private String nationality; // Only necessary nationality info

    @JsonProperty("enrollmentInfo")
    @Schema(description = "Enrollment details of the student")
    private EnrollmentInfoDTO enrollmentInfoDTO; // Non-sensitive enrollment data

    @Schema(description = "Department the student belongs to")
    private DepartmentDTO departmentDTO; // Basic departmentDTO info

    @JsonProperty("address")
    @Schema(description = "Address information of the student")
    private AddressDTO addressDTO; // Only necessary address info

    @Schema(description = "List of courses the student is currently enrolled in")
    private List<Course> enrolledCourses; // Relevant courses for a public view

    @Schema(description = "Indicates if the student has a scholarship", example = "true")
    private boolean scholarshipHolder; // Only if relevant for the context
}
