package com.example.demo.dto.student;

import com.example.demo.dto.common.AddressDTO;
import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.dto.common.PersonalInfoDTO;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import com.example.demo.model.entities.Course;
import com.example.demo.model.entities.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    @JsonProperty("personalInfo")
    private PersonalInfoDTO personalInfoDTO; // Only personal name or publicly available details

    @JsonProperty("contactInfo")
    private ContactInfoDTO contactInfoDTO; // Only contact details or publicly available details
    private String nationality; // Only necessary nationality info

    @JsonProperty("enrollmentInfo")
    private EnrollmentInfoDTO enrollmentInfoDTO; // Non-sensitive enrollment data
    private Department department; // Basic department info

    @JsonProperty("address")
    private AddressDTO addressDTO; // Only necessary address info
    private List<Course> enrolledCourses; // Relevant courses for a public view
    private boolean isScholarshipHolder; // Only if relevant for the context
}
