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

import com.dance.dance.entity.Student;
import com.dance.dance.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
    // @Value("${my.name}")
    // private String name;

    @Autowired
    StudentService studentserv;

    @GetMapping("/get/all")
    public List<Student> getEmployee(){
        return studentserv.getEmployee();
    }

    @GetMapping("/get/id:{id}")
    public Student getEmployeebyId(@PathVariable Long id){
        return studentserv.findById(id);
    }

    @GetMapping("/get/name:{name}")
    public List<Student> getEmployeebyName(@PathVariable String name){
        return studentserv.findByName(name);
    }
    @GetMapping("/get/email:{email}")
    public List<Student> findByEmail(@PathVariable String email){
        return studentserv.findByEmail(email);
    }

    @PostMapping("/add")
    public Student addEmployee(@RequestBody Student emp){
        return studentserv.addEmployee(emp);
    }
    
    @GetMapping("/pagination")
    public Page<Student> getPage(@RequestParam int page,@RequestParam int size){
        return studentserv.getPage(page,size);
    }   

    @GetMapping("/get/sortedbyname")
    public List<Student> getSorted(){
        return studentserv.getSorted();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentserv.deleteStudent(id);
        return ResponseEntity.ok("Student deleted Successfully");
    } 

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student student1 = studentserv.updateStudent(id,student);
        return ResponseEntity.ok(student1);
    }
}
