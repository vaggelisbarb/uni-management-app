package com.example.demo.util;

import com.example.demo.dto.common.AddressDTO;
import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.dto.common.PersonalInfoDTO;
import com.example.demo.dto.student.EnrollmentInfoDTO;
import com.example.demo.dto.student.StudentDTO;
import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import com.example.demo.model.entities.Course;
import com.example.demo.model.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    // Method to convert Student entity to StudentDTO
    public static StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }

        // Map properties of Student to StudentDTO
        return new StudentDTO(
                student.getId(),
                mapPersonalInfoToDTO(student.getPersonalInfo()),
                mapContactInfoToDTO(student.getContactInfo()),
                student.getNationality(),
                mapEnrollmentInfoToDTO(student.getEnrollmentInfo()),
                student.getDepartment(),  // Assuming Department is a basic entity without sensitive info
                mapAddressToDTO(student.getAddress()),
                mapCourses(student.getEnrolledCourses()),
                student.isScholarshipHolder()
        );
    }

    // Method to convert StudentDTO to Student entity
    public static Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        }

        // Map properties of StudentDTO to Student entity
        return new Student(
                studentDTO.getId(),
                mapPersonalInfoToEntity(studentDTO.getPersonalInfoDTO()),
                mapContactInfoToEntity(studentDTO.getContactInfoDTO()),  // Not including ContactInfo and Address in DTO, setting them as null
                mapAddressToEntity(studentDTO.getAddressDTO()),  // Not including Address in DTO, setting them as null
                studentDTO.getNationality(),
                mapEnrollmentInfoToEntity(studentDTO.getEnrollmentInfoDTO()),
                studentDTO.getDepartment(),
                mapCoursesToEntities(studentDTO.getEnrolledCourses()),
                0.0,  // GPA not included in DTO, setting to default value
                0,  // Credits not included in DTO, setting to default value
                null,  // Advisor not included in DTO
                null,  // Extracurricular activities not included in DTO
                studentDTO.isScholarshipHolder(),
                null  // Achievements not included in DTO
        );
    }

    // Helper methods to map DTOs to Entities and vice versa

    private static PersonalInfoDTO mapPersonalInfoToDTO(PersonalInfo personalInfo) {
        return personalInfo != null ? new PersonalInfoDTO(personalInfo.getFirstName(), personalInfo.getLastName()) : null;
    }

    private static PersonalInfo mapPersonalInfoToEntity(PersonalInfoDTO personalInfoDTO) {
        return personalInfoDTO != null ? new PersonalInfo(personalInfoDTO.getFirstName(), personalInfoDTO.getLastName(), null, null) : null;
    }

    private static ContactInfoDTO mapContactInfoToDTO(ContactInfo contactInfo) {
        return contactInfo != null ? new ContactInfoDTO(contactInfo.getEmail()) : null;
    }

    private static ContactInfo mapContactInfoToEntity(ContactInfoDTO contactInfoDTO) {
        return contactInfoDTO != null ? new ContactInfo(contactInfoDTO.getEmail(), null) : null;
    }

    private static AddressDTO mapAddressToDTO(Address address) {
        return address != null ? new AddressDTO(address.getCity(), address.getCountry()) : null;
    }

    private static Address mapAddressToEntity(AddressDTO addressDTO) {
        return addressDTO != null ? new Address(null, addressDTO.getCity(), null, null, addressDTO.getCountry()) : null;
    }

    private static EnrollmentInfo mapEnrollmentInfoToEntity(EnrollmentInfoDTO enrollmentInfoDTO) {
        return enrollmentInfoDTO != null ? new EnrollmentInfo(enrollmentInfoDTO.getEnrollmentDate(), null, enrollmentInfoDTO.getCurrentStatus(), null) : null;
    }

    private static EnrollmentInfoDTO mapEnrollmentInfoToDTO(EnrollmentInfo enrollmentInfo) {
        return enrollmentInfo != null ? new EnrollmentInfoDTO(enrollmentInfo.getEnrollmentDate(), enrollmentInfo.getCurrentStatus()) : null;
    }

    private static List<Course> mapCourses(List<Course> courses) {
        return courses != null ? new ArrayList<>(courses) : null;
    }

    private static List<Course> mapCoursesToEntities(List<Course> courses) {
        return courses != null ? new ArrayList<>(courses) : null;
    }

}
