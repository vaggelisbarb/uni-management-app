package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a course schedule")
public class ScheduleDTO {

    @Schema(description = "The semester in which the course is offered", example = "Spring 2025")
    private String semester;

    @Schema(description = "Class schedule with days and times", example = "Mon/Wed/Fri 10:00 AM - 11:30 AM")
    private String schedule;

    @Schema(description = "Physical location where the class takes place", example = "Building A, Room 205")
    private String location;

}
