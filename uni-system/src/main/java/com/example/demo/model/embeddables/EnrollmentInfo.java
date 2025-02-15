package com.example.demo.model.embeddables;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

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

    public EnrollmentInfo() {
    }

    public EnrollmentInfo(String enrollmentDate, String studentIdCardNumber, String currentStatus, String graduationYear) {
        this.enrollmentDate = enrollmentDate;
        this.studentIdCardNumber = studentIdCardNumber;
        this.currentStatus = currentStatus;
        this.graduationYear = graduationYear;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentIdCardNumber() {
        return studentIdCardNumber;
    }

    public void setStudentIdCardNumber(String studentIdCardNumber) {
        this.studentIdCardNumber = studentIdCardNumber;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public String toString() {
        return "EnrollmentInfo{" +
                "enrollmentDate=" + enrollmentDate +
                ", studentIdCardNumber='" + studentIdCardNumber + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                '}';
    }
}
