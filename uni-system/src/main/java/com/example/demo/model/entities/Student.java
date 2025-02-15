package com.example.demo.model.entities;

import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.EnrollmentInfo;
import com.example.demo.model.embeddables.PersonalInfo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // Unique identifier for the student (e.g., "S12345")

    @Embedded
    private PersonalInfo personalInfo; // Personal information for the student

    @Embedded
    private ContactInfo contactInfo; // // Embedded contact info

    @Embedded
    private Address address; // Embedded address info

    private String nationality; // Nationality of the student

    @Embedded
    private EnrollmentInfo enrollmentInfo; // Enrollment info for the student

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; // Department the student belongs to

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> enrolledCourses; // List of courses the student is currently enrolled in
    private double gpa; // Grade Point Average (GPA) of the student
    private int creditsCompleted; // Total number of credits completed

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Professor advisor; // Name of the academic advisor assigned to the student

    @ElementCollection
    @CollectionTable(name = "student_extracurriculars",
                joinColumns = @JoinColumn(name = "student_id"))
    private List<String> extracurricularActivities; // List of extracurricular activities the student participates in

    private boolean isScholarshipHolder; // Indicates if the student has a scholarship

    @ElementCollection
    @CollectionTable(name = "student_achievements", joinColumns = @JoinColumn(name = "student_id"))
    private List<String> achievements; // List of notable achievements or awards

    public Student() {}

    public Student(String id, PersonalInfo personalInfo, ContactInfo contactInfo, Address address,String nationality, EnrollmentInfo enrollmentInfo, Department department, List<Course> enrolledCourses, double gpa, int creditsCompleted, Professor advisor, List<String> extracurricularActivities, boolean isScholarshipHolder, List<String> achievements) {
        this.id = id;
        this.personalInfo = personalInfo;
        this.contactInfo = contactInfo;
        this.address = address;
        this.nationality = nationality;
        this.enrollmentInfo = enrollmentInfo;
        this.department = department;
        this.enrolledCourses = enrolledCourses;
        this.gpa = gpa;
        this.creditsCompleted = creditsCompleted;
        this.advisor = advisor;
        this.extracurricularActivities = extracurricularActivities;
        this.isScholarshipHolder = isScholarshipHolder;
        this.achievements = achievements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public ContactInfo getContactInfo() { return contactInfo; }

    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public EnrollmentInfo getEnrollmentInfo() {
        return enrollmentInfo;
    }

    public void setEnrollmentInfo(EnrollmentInfo enrollmentInfo) {
        this.enrollmentInfo = enrollmentInfo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCreditsCompleted() {
        return creditsCompleted;
    }

    public void setCreditsCompleted(int creditsCompleted) {
        this.creditsCompleted = creditsCompleted;
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Professor advisor) {
        this.advisor = advisor;
    }

    public List<String> getExtracurricularActivities() {
        return extracurricularActivities;
    }

    public void setExtracurricularActivities(List<String> extracurricularActivities) {
        this.extracurricularActivities = extracurricularActivities;
    }

    public boolean isScholarshipHolder() {
        return isScholarshipHolder;
    }

    public void setScholarshipHolder(boolean scholarshipHolder) {
        isScholarshipHolder = scholarshipHolder;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                personalInfo.toString() + '\'' +
                contactInfo.toString() + '\'' +
                address.toString() + '\'' +
                ", nationality='" + nationality + '\'' +
                enrollmentInfo.toString() + '\'' +
                ", department=" + department +
                ", enrolledCourses=" + enrolledCourses +
                ", gpa=" + gpa +
                ", creditsCompleted=" + creditsCompleted +
                ", advisor=" + advisor +
                ", extracurricularActivities=" + extracurricularActivities +
                ", isScholarshipHolder=" + isScholarshipHolder +
                ", achievements=" + achievements +
                '}';
    }
}

