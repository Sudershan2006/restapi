package com.dance.dance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dance.dance.entity.Employer;
import com.dance.dance.service.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    EmployerService empserv;


    @GetMapping("/get/all")
    public List<Employer> findAll(){
        return empserv.findAll();
    }

    @PostMapping("/add")
    public Employer addEmployer(@RequestBody Employer emp){
        return empserv.addEmployer(emp);
    }

    @GetMapping("/get/id:{id}")
    public Employer getEmployeebyId(@PathVariable Long id){
        return empserv.findById(id);
    }

    @GetMapping("/get/name:{name}")
    public List<Employer> getEmployeebyName(@PathVariable String name){
        return empserv.findByName(name);
    }
    @GetMapping("/get/email:{email}")
    public List<Employer> findByEmail(@PathVariable String email){
        return empserv.findByEmail(email);
    }

    @GetMapping("/pagination")
    public Page<Employer> getPage(@RequestParam int page,@RequestParam int size){
        return empserv.getPage(page,size);
    }   

    @GetMapping("/get/sortedbyname")
    public List<Employer> getSorted(){
        return empserv.getSorted();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable Long id){
        empserv.deleteEmployer(id);
        return ResponseEntity.ok("Employer deleted Successfully");
    } 

    @PutMapping("/update/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id,@RequestBody Employer employer1){
        Employer employer = empserv.updateEmployer(id,employer1);
        return ResponseEntity.ok(employer);
    }
}
