package com.cmpt276.asn2.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private boolean isValidColour(String colour) {
        //if true, it will set the colour to black since certain strings can't be interpreted by thymeleaf 
        List<String> validColours = Arrays.asList("red", "orange", "yellow", "green", "blue", "purple", "pink", "brown", "grey");
        return validColours.contains(colour.toLowerCase()); 
    }
    
    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");

        //get all attributes of student 
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHairColour = newStudent.get("hairColour");
        if(!isValidColour(newHairColour)){
            newHairColour = "black"; 
        }
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        String newFavColour = newStudent.get("favColour");
        if(!isValidColour(newFavColour)){
            newFavColour = "black"; 
        }
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColour, newGpa, newFavColour)); 
        response.setStatus(201);

        return "students/addedStudent"; 
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam("uid") int uid, HttpServletResponse response){
        System.out.println("DELETE student");

        //get user id 
        Optional<Student> toDeleteSt = studentRepo.findById(uid);

        //check if user id is valid, if not then show user an error 
        if(toDeleteSt.isPresent()){
            //delete the student 
            studentRepo.delete(toDeleteSt.get());
            response.setStatus(200);
            return "students/addedStudent";

        } else {
            //show error 
            response.setStatus(404);
            return "students/failedRequest.html";
        }
    }

    //postmapping for updating student 
    @PostMapping("/students/update")
    public String updateStudent(@RequestParam Map<String, String> updatedStudent, HttpServletResponse response) {
        System.out.println("UPDATE student");

        //get for the student's uid 
        int getUid = Integer.parseInt(updatedStudent.get("uid"));
        Optional<Student> toUpdateSt = studentRepo.findById(getUid);
            
        //if the uid exists: 
        if(toUpdateSt.isPresent()){
            //take in all values again for the new student attributes
            Student changedSt = toUpdateSt.get();

            //get and set all updated values 
            String updateName = updatedStudent.get("name");
            changedSt.setName(updateName);
            int updateWeight = Integer.parseInt(updatedStudent.get("weight"));
            changedSt.setWeight(updateWeight);
            int updateHeight = Integer.parseInt(updatedStudent.get("height"));
            changedSt.setHeight(updateHeight);
            String updateHair = updatedStudent.get("hairColour");
            if(!isValidColour(updateHair)){
                updateHair = "black"; 
            }
            changedSt.setHairColour(updateHair);
            double updateGpa = Double.parseDouble(updatedStudent.get("gpa"));
            changedSt.setGpa(updateGpa);
            String updateFavCol = updatedStudent.get("favColour");
            if(!isValidColour(updateFavCol)){
                updateFavCol = "black"; 
            }
            changedSt.setFavColour(updateFavCol);

            //save updated student
            studentRepo.save(changedSt);
            response.setStatus(200);
            return "students/addedStudent";

        } else {
            //show error 
            response.setStatus(404);
            return "students/failedRequest.html";
        }
    }
    
}
