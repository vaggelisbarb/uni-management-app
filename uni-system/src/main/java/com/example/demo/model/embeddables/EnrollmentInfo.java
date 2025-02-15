package com.example.demo.model.embeddables;

import java.util.Date;

public class EnrollmentInfo {

    private String enrollmentDate;    // Date the student was enrolled in the institution
    private String studentIdCardNumber; // Unique student ID card number
    private String currentStatus;   // Current status (e.g., "Active", "Graduated", "Dropped Out")
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
