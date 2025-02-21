package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {

    @NotBlank(message = "Semester is required")
    @Pattern(regexp = "^(Fall|Spring) \\d{4}$", message = "Semester must be in the format 'Fall 2024' or 'Spring 2024'")
    private String semester;    // e.g., Fall 2024

    @NotBlank(message = "Schedule is required")
    private String schedule;    // e.g., Monday & Wednesday 10:00 - 12:00

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location can be up to 100 characters long")
    private String location;     // Classroom or online

}
