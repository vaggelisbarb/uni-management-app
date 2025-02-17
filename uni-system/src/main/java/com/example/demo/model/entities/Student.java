package com.example.demo.model.entities;

import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "student")
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates an all-args constructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the student (e.g., "S12345")

    @Embedded
    @Valid
    private PersonalInfo personalInfo; // Personal information for the student

    @Embedded
    @Valid
    private ContactInfo contactInfo; // // Embedded contact info

    @Embedded
    @Valid
    private Address address; // Embedded address info

    private String nationality; // Nationality of the student

    @Embedded
    @Valid
    private EnrollmentInfo enrollmentInfo; // Enrollment info for the student

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; // Department the student belongs to

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> enrolledCourses; // List of courses the student is currently enrolled in
    private double gpa; // Grade Point Average (GPA) of the student
    private int creditsCompleted; // Total number of credits completed

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Professor advisor; // Name of the academic advisor assigned to the student

    @ElementCollection
    @CollectionTable(name = "student_extracurriculars",
                joinColumns = @JoinColumn(name = "student_id"))
    private List<String> extracurricularActivities; // List of extracurricular activities the student participates in

    @JsonProperty("isScholarshipHolder")
    private boolean isScholarshipHolder; // Indicates if the student has a scholarship

    @ElementCollection
    @CollectionTable(name = "student_achievements", joinColumns = @JoinColumn(name = "student_id"))
    private List<String> achievements; // List of notable achievements or awards
}

