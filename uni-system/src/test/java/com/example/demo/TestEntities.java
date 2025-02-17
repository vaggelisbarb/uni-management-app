package com.example.demo;

import com.example.demo.model.embeddables.*;
import com.example.demo.model.entities.Course;
import com.example.demo.model.entities.Department;
import com.example.demo.model.entities.Student;
import com.example.demo.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestEntities {

    @Test
    public void testStudentCreation() {
        PersonalInfo personalInfo = new PersonalInfo("John", "Doe", "12-10-1996", "male");
        ContactInfo contactInfo = new ContactInfo("vaggelisbarbalias@outlook.com", "6900000000");
        Address address = new Address("1234 Main Str", "Country", "54321", "State", "City");
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo("10-10-2013", "ID2402205", "Active", "05-11-2018");

        Student student = new Student(1234L, personalInfo, contactInfo, address, "GREECE", enrollmentInfo, null, null, 6.4, 120, null, null, true, null );

        assertNotNull(student);
        assertEquals(1234L, student.getId());
        assertEquals("John", student.getPersonalInfo().getFirstName());
        assertEquals("1234 Main Str", student.getAddress().getStreet());
        System.out.println("Student created: " + JsonUtil.toJson(student));
    }

    @Test
    public void testCourseCreation() {
        ScheduleInfo scheduleInfo = new ScheduleInfo("Fall 2024", "Monday & Wednesday 10:00", "Classroom");

        Course course = new Course(101L
                , "Introduction to Computer Science"
                , "Introduction to Basics of Computer sciece & engineering"
                , 5
                , "Computer Science"
                , "Undergraduate"
                , null
                , null
                , null
                , scheduleInfo
                , 5
                , null
                , null
                , true
                , "Exams 50%, Assignments 30%, Participation 20%"
                , null
                , null);

        assertNotNull(course);
        assertEquals(101L, course.getId());
        assertEquals("Fall 2024", course.getScheduleInfo().getSemester());
        System.out.println("Course created: " + JsonUtil.toJson(course));
    }

}
