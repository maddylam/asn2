package com.cmpt276.asn2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.asn2.models.Student;
import com.cmpt276.asn2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentsController {

    @Autowired
    private StudentRepository studentRepo; 
    @GetMapping("/students/view")
    public String getAllStudents(Model model){
        System.out.println("Getting all students");
        // get all students from database 
        List<Student> students = studentRepo.findAll();
        // end of database call 
        model.addAttribute("st", students); 
        return "students/showAll";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHairColour = newStudent.get("hairColour");
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        //int newGpa = Integer.parseInt(newStudent.get("gpa"));
        String newFavColour = newStudent.get("favColour");
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColour, newGpa, newFavColour)); 
        response.setStatus(201);

        return "students/addedStudent"; 
    }
}
