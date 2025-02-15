package com.example.demo.model.entities;

import com.example.demo.model.embeddables.Address;
import com.example.demo.model.embeddables.ContactInfo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    // Basic Information
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the department (e.g., "CS101")

    @NotBlank(message = "Department name is required")
    private String name; // Full name of the department (e.g., "Department of Computer Science")

    @NotBlank(message = "Department description is required")
    private String description; // Brief description of the department's purpose and focus

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Professor> faculty; // List of professors working in the department

    @ManyToOne
    @JoinColumn(name = "head_of_department_id")
    private Professor headOfDepartment; // The professor currently leading the department

    @Embedded
    @Valid
    private Address address; // The address of the department

    private String buildingLocation; // Physical location of the department (building and room number)

    @Embedded
    @Valid
    private ContactInfo contactInfo; // Contact information about the department

    // Academic Programs & Courses

    @ElementCollection
    @NotEmpty(message = "Degree programs cannot be empty")
    private List<String> degreePrograms; // List of degree programs offered (e.g., BSc, MSc, PhD)

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> coursesOffered; // List of courses available under this department

    @ElementCollection
    @NotEmpty(message = "Research areas cannot be empty")
    private List<String> researchAreas; // Research areas the department specializes in (e.g., "AI", "Cybersecurity")

    @Min(value = 0, message = "Student count cannot be negative")
    private int studentCount; // Total number of students currently enrolled in the department

    // Administrative & Miscellaneous
    @URL(message = "Not a valid website URL")
    private String website; // Official website of the department

    private int foundedYear; // Year in which the department was established

    @DecimalMin(value = "0.0", message = "Budget must be a positive value")
    private double budget; // Allocated budget for the department

    @ElementCollection
    private List<String> affiliatedOrganizations; // External organizations or research centers affiliated with the department

    public Department() {}

    public Department(Long id, String name, String description, List<Professor> faculty, Professor headOfDepartment, Address address, String buildingLocation, ContactInfo contactInfo, List<String> degreePrograms, List<Course> coursesOffered, List<String> researchAreas, int studentCount, String website, int foundedYear, double budget, List<String> affiliatedOrganizations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
        this.address = address;
        this.buildingLocation = buildingLocation;
        this.contactInfo = contactInfo;
        this.degreePrograms = degreePrograms;
        this.coursesOffered = coursesOffered;
        this.researchAreas = researchAreas;
        this.studentCount = studentCount;
        this.website = website;
        this.foundedYear = foundedYear;
        this.budget = budget;
        this.affiliatedOrganizations = affiliatedOrganizations;
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

    public List<Professor> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<Professor> faculty) {
        this.faculty = faculty;
    }

    public Professor getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Professor headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBuildingLocation() {
        return buildingLocation;
    }

    public void setBuildingLocation(String buildingLocation) {
        this.buildingLocation = buildingLocation;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<String> getDegreePrograms() {
        return degreePrograms;
    }

    public void setDegreePrograms(List<String> degreePrograms) {
        this.degreePrograms = degreePrograms;
    }

    public List<Course> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<Course> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public List<String> getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(List<String> researchAreas) {
        this.researchAreas = researchAreas;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<String> getAffiliatedOrganizations() {
        return affiliatedOrganizations;
    }

    public void setAffiliatedOrganizations(List<String> affiliatedOrganizations) {
        this.affiliatedOrganizations = affiliatedOrganizations;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", faculty=" + faculty +
                ", headOfDepartment=" + headOfDepartment +
                address.toString() + '\'' +
                ", buildingLocation='" + buildingLocation + '\'' +
                contactInfo.toString() + '\'' +
                ", degreePrograms=" + degreePrograms +
                ", coursesOffered=" + coursesOffered +
                ", researchAreas=" + researchAreas +
                ", studentCount=" + studentCount +
                ", website='" + website + '\'' +
                ", foundedYear=" + foundedYear +
                ", budget=" + budget +
                ", affiliatedOrganizations=" + affiliatedOrganizations +
                '}';
    }
}
