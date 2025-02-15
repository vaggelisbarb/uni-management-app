package com.example.demo.model.entities;

import com.example.demo.model.embeddables.ContactInfo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professor")
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

    public Professor() {}

    public Professor(String researchArea, String name, Long id, ContactInfo contactInfo, Department department, String fieldOfExpertise, String degree, int yearsOfExperience, List<Course> coursesTaught, List<Course> currentCourses, List<Publication> publications) {
        this.researchArea = researchArea;
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.department = department;
        this.fieldOfExpertise = fieldOfExpertise;
        this.degree = degree;
        this.yearsOfExperience = yearsOfExperience;
        this.coursesTaught = coursesTaught;
        this.currentCourses = currentCourses;
        this.publications = publications;
    }

    public Professor(String name, Long id, ContactInfo contactInfo, Department department, String fieldOfExpertise, String degree, int yearsOfExperience) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.department = department;
        this.fieldOfExpertise = fieldOfExpertise;
        this.degree = degree;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Course> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(List<Course> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }

    public void setFieldOfExpertise(String fieldOfExpertise) {
        this.fieldOfExpertise = fieldOfExpertise;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "researchArea='" + researchArea + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", " + contactInfo.toString() + '\'' +
                ", department='" + department + '\'' +
                ", fieldOfExpertise='" + fieldOfExpertise + '\'' +
                ", degree='" + degree + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", coursesTaught=" + coursesTaught +
                ", currentCourses=" + currentCourses +
                ", publications=" + publications +
                '}';
    }
}
