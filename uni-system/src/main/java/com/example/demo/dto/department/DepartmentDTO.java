package com.example.demo.dto.department;

import com.example.demo.dto.CourseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor   // Generates a no-args constructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Data Transfer Object representing a department")
public class DepartmentDTO {

    @Schema(description = "Unique identifier for the department", example = "10")
    private Long id;

    @Schema(description = "Name of the department", example = "Computer Science")
    private String name;               // Department name

    @Schema(description = "Short description of the department", example = "The Department of Computer Science focuses on algorithms, programming, and AI research.")
    private String description;        // Short description of departmentDTO

    @Schema(description = "Building location of the department in the university", example = "Science Building, Room 305")
    private String buildingLocation;   // Location in university

    @Schema(description = "List of research fields in the department", example = "[\"Artificial Intelligence\", \"Cybersecurity\", \"Data Science\"]")
    private List<String> researchAreas; // List of research fields

    @Schema(description = "Number of students currently in the department", example = "1200")
    private int studentCount;          // Number of students

    @Schema(description = "Official website of the department", example = "https://cs.university.edu")
    private String website;            // Department website

    @Schema(description = "Year the department was founded", example = "1985")
    private int foundedYear;           // Year founded

    @Schema(description = "List of course names offered by the department")
    private List<CourseDTO> courses;      // Course names (instead of full Course objects)

    @Schema(description = "List of degree programs available in the department", example = "[\"BSc Computer Science\", \"MSc Artificial Intelligence\", \"PhD Data Science\"]")
    private List<String> degreePrograms; // List of degree programs
}
