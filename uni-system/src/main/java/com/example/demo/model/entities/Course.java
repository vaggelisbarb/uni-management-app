package com.example.demo.model.entities;

import com.example.demo.model.embeddables.ScheduleInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "course")
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates an all-args constructor
public class Course {

    // Basic information
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Unique identifier for the course
    private String name;    // Course name

    @Column(length =  1000)
    private String description; // Detailed course description
    private int credits;    // Number of credits for the course
    private String category;    // e.g., Computer Science, Mathematics
    private String level;   // e.g., Undergraduate, Graduate

    // Instructor and teaching information
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;   // The main professor teaching the course

    @ManyToMany
    @JoinTable(name = "course_teacher_assistants",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> teachingAssistants; // List of TAs (optional)

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;  // Department offering the course

    // Schedule and availability
    @Embedded
    private ScheduleInfo scheduleInfo; // Embedded Schedule information

    // Enrollments and requirements
    private int maxStudents;    // Maximum number of students allowed

   @ManyToMany
   @JoinTable(name = "course_enrollment",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> enrolledStudents; // List of students in the course

    @ManyToMany
    @JoinTable(
            name = "course_prerequisites",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id"))
    private List<Course> prerequisites; // List of prerequisite courses
    private boolean elective; // Whether the course is elective or required

    // Assessments and materials
    private String gradingPolicy;   // e.g., Exams 50%, Assignments 30%, Participation 20%

    @Column(length = 2000)
    private String syllabus;    // URL or content of the syllabus

    @ElementCollection
    @CollectionTable(name = "course_resources",
            joinColumns = @JoinColumn(name = "course_id"))
    private List<String> resources; // List of links to materials or books

}
