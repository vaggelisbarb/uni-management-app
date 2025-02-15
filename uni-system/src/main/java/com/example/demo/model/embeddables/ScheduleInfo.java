package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
public class ScheduleInfo {

    @NotBlank(message = "Semester is required")
    @Pattern(regexp = "^(Fall|Spring) \\d{4}$", message = "Semester must be in the format 'Fall 2024' or 'Spring 2024'")
    private String semester;    // e.g., Fall 2024

    @NotBlank(message = "Schedule is required")
    private String schedule;    // e.g., Monday & Wednesday 10:00 - 12:00

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location can be up to 100 characters long")
    private String location;     // Classroom or online

    public ScheduleInfo() {}

    public ScheduleInfo(String semester, String location, String schedule) {
        this.semester = semester;
        this.location = location;
        this.schedule = schedule;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "ScheduleInfo{" +
                "semester='" + semester + '\'' +
                ", schedule='" + schedule + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
