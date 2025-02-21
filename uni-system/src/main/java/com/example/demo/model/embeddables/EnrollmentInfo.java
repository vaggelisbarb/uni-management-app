package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentInfo {

    @NotBlank(message = "Enrollment date is required")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Enrollment date must be in the format dd-MM-yyyy")
    private String enrollmentDate;    // Date the student was enrolled in the institution

    @NotBlank(message = "Student ID card number is required")
    @Size(min = 8, max = 20, message = "Student ID card number must be between 8 and 20 characters")
    private String studentIdCardNumber; // Unique student ID card number

    @NotBlank(message = "Current status is required")
    @Pattern(regexp = "^(Active|Inactive|Graduated|Dropped Out)$", message = "Current status must be one of the following: Active, Inactive, Graduated, Dropped Out")
    private String currentStatus;   // Current status (e.g., "Active", "Graduated", "Dropped Out")

    @Pattern(regexp = "^\\d{4}$", message = "Graduation year must be a valid 4-digit year")
    private String graduationYear;  // Expected or actual year of graduation

}
