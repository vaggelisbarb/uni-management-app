package com.example.demo.controller;


import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    ProfessorService professorService;

    @GetMapping("/home")
    public void home() {
        System.out.println("Welcome to the HomeController");
    }
    @GetMapping("/professor")
    public String getProfessorInfo() {
        return null;
    }

}
