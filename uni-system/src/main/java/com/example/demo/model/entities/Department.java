package com.example.demo.model.entities;

import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Entity
@Table(name = "department")
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates an all-args constructor
public class Department {

    // Basic Information
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the department (e.g., "CS101")

    @NotBlank(message = "Department name is required")
    private String name; // Full name of the department (e.g., "Department of Computer Science")

    @NotBlank(message = "Department description is required")
    private String description; // Brief description of the department's purpose and focus

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Professor> faculty; // List of professors working in the department

    @ManyToOne
    @JoinColumn(name = "head_of_department_id")
    private Professor headOfDepartment; // The professor currently leading the department

    @Embedded
    @Valid
    private Address address; // The address of the department

    private String buildingLocation; // Physical location of the department (building and room number)

    @Embedded
    @Valid
    private ContactInfo contactInfo; // Contact information about the department

    // Academic Programs & Courses

    @NotEmpty(message = "Degree programs cannot be empty")
    private List<String> degreePrograms; // List of degree programs offered (e.g., BSc, MSc, PhD)

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> coursesOffered; // List of courses available under this department

    //@NotEmpty(message = "Research areas cannot be empty")
    private List<String> researchAreas; // Research areas the department specializes in (e.g., "AI", "Cybersecurity")

    @Min(value = 0, message = "Student count cannot be negative")
    private int studentCount; // Total number of students currently enrolled in the department

    // Administrative & Miscellaneous
    @URL(message = "Not a valid website URL")
    private String website; // Official website of the department

    private int foundedYear; // Year in which the department was established

    @DecimalMin(value = "0.0", message = "Budget must be a positive value")
    private double budget; // Allocated budget for the department

    @ElementCollection
    private List<String> affiliatedOrganizations; // External organizations or research centers affiliated with the department

}
