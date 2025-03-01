package com.example.demo.model.entities;

import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Represents a student entity in the university system")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Student ID" ,example = "1")
    private Long id; // Unique identifier for the student (e.g., "S12345")

    @Embedded
    @Valid
    @Schema(description = "Personal details of the student")
    private PersonalInfo personalInfo; // Personal information for the student

    @Embedded
    @Valid
    @Schema(description = "Contact details of the student")
    private ContactInfo contactInfo; // // Embedded contact info

    @Embedded
    @Valid
    @Schema(description = "Address details of the student")
    private Address address; // Embedded address info

    @Schema(description = "Nationality of the student", example = "Greek")
    private String nationality; // Nationality of the student

    @Embedded
    @Valid
    @Schema(description = "Enrollment details of the student")
    private EnrollmentInfo enrollmentInfo; // Enrollment info for the student

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; // Department the student belongs to

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Schema(description = "List of courses the student is enrolled in")
    private List<Course> enrolledCourses; // List of courses the student is currently enrolled in

    @Schema(description = "Student's Grade Point Average (GPA)", example = "3.75")
    private double gpa; // Grade Point Average (GPA) of the student

    @Schema(description = "Total number of credits the student has completed", example = "90")
    private int creditsCompleted; // Total number of credits completed

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    @Schema(description = "Professor assigned as the student's academic advisor")
    private Professor advisor; // Name of the academic advisor assigned to the student

    @ElementCollection
    @CollectionTable(name = "student_extracurriculars",
                joinColumns = @JoinColumn(name = "student_id"))
    @Schema(description = "List of extracurricular activities the student participates in")
    private List<String> extracurricularActivities; // List of extracurricular activities the student participates in

    @Column(name = "scholarshipHolder", columnDefinition = "BOOLEAN")
    @Schema(description = "Indicates whether the student is a scholarship holder", example = "true")
    private boolean scholarshipHolder; // Indicates if the student has a scholarship

    @ElementCollection
    @CollectionTable(name = "student_achievements",
            joinColumns = @JoinColumn(name = "student_id"))
    @Schema(description = "List of notable achievements or awards received by the student")
    private List<String> achievements; // List of notable achievements or awards
}

