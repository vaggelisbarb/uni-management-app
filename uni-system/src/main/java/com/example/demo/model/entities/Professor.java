package com.example.demo.model.entities;

import com.example.demo.model.embeddables.ContactInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "professor")
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates an all-args constructor
public class Professor {

    // Personal Information
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique professor ID (auto-generated)

    @Column(nullable = false)
    private String name;    // Professor's name

    @Embedded
    private ContactInfo contactInfo; // Embedded contact info

    // Educational Information
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;  // Professor's department

    private String fieldOfExpertise;    // Area of expertise
    private String degree;  // Highest degree earned

    // Courses Information
    private int yearsOfExperience;  // Years of teaching experience

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> coursesTaught; // Courses previously taught

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> currentCourses;    // Courses currently teaching

    // Publications Information

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications; // List of publications
    private String researchArea;    // Research focus area
    /*
    private List<Student> studentsSupervised;
    private List<Affiliation> professionalAffiliations;
     */
}
