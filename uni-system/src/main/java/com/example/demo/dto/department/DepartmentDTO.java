package com.example.demo.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor   // Generates a no-args constructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDTO {

    private Long id;
    private String name;               // Department name
    private String description;        // Short description of department
    private String buildingLocation;   // Location in university
    private List<String> researchAreas; // List of research fields
    private int studentCount;          // Number of students
    private String website;            // Department website
    private int foundedYear;           // Year founded
    private List<String> courses;      // Course names (instead of full Course objects)
    private List<String> degreePrograms; // List of degree programs
}
