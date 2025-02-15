package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class ScheduleInfo {

    private String semester;    // e.g., Fall 2024
    private String schedule;    // e.g., Monday & Wednesday 10:00 - 12:00
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
