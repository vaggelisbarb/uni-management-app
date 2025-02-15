package com.example.demo.model.entities;

import com.example.demo.model.embeddables.ScheduleInfo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
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
    private boolean isElective; // Whether the course is elective or required

    // Assessments and materials
    private String gradingPolicy;   // e.g., Exams 50%, Assignments 30%, Participation 20%

    @Column(length = 2000)
    private String syllabus;    // URL or content of the syllabus

    @ElementCollection
    @CollectionTable(name = "course_resources",
            joinColumns = @JoinColumn(name = "course_id"))
    private List<String> resources; // List of links to materials or books

    public Course () {}

    public Course(Long id, String name, String description, int credits, String category, Professor professor, String level, List<Professor> teachingAssistants, Department department, ScheduleInfo scheduleInfo, int maxStudents, List<Student> enrolledStudents, List<Course> prerequisites, boolean isElective, String gradingPolicy, String syllabus, List<String> resources) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.category = category;
        this.professor = professor;
        this.level = level;
        this.teachingAssistants = teachingAssistants;
        this.department = department;
        this.scheduleInfo = scheduleInfo;
        this.maxStudents = maxStudents;
        this.enrolledStudents = enrolledStudents;
        this.prerequisites = prerequisites;
        this.isElective = isElective;
        this.gradingPolicy = gradingPolicy;
        this.syllabus = syllabus;
        this.resources = resources;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getTeachingAssistants() {
        return teachingAssistants;
    }

    public void setTeachingAssistants(List<Professor> teachingAssistants) {
        this.teachingAssistants = teachingAssistants;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ScheduleInfo getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(ScheduleInfo scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public boolean isElective() {
        return isElective;
    }

    public void setElective(boolean elective) {
        isElective = elective;
    }

    public String getGradingPolicy() {
        return gradingPolicy;
    }

    public void setGradingPolicy(String gradingPolicy) {
        this.gradingPolicy = gradingPolicy;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", category='" + category + '\'' +
                ", level='" + level + '\'' +
                ", professor=" + professor +
                ", teachingAssistants=" + teachingAssistants +
                ", department=" + department +
                ", " + scheduleInfo.toString() +
                ", maxStudents=" + maxStudents +
                ", enrolledStudents=" + enrolledStudents +
                ", prerequisites=" + prerequisites +
                ", isElective=" + isElective +
                ", gradingPolicy='" + gradingPolicy + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", resources=" + resources +
                '}';
    }
}
