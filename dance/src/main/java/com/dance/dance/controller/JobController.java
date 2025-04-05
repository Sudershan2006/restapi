package com.dance.dance.controller;

import java.time.LocalDate;
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

import com.dance.dance.entity.Job;
import com.dance.dance.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobserv;
    
    
    @GetMapping("/get/all")
    public List<Job> findAll(){
        return jobserv.findAll();
    }

    @PostMapping("/add")
    public Job addJob(@RequestBody Job job){
        return jobserv.addJob(job);
    }

    @GetMapping("/get/id:{id}")
    public Job getEmployeebyId(@PathVariable Long id){
        return jobserv.findById(id);
    }

    @GetMapping("/get/position:{position}")
    public List<Job> getPosition(@PathVariable String position){
        return jobserv.findByPosition(position);
    }

    @GetMapping("/get/date:{date}")
    public List<Job> getByDate(@PathVariable LocalDate date){
        return jobserv.findByDate(date);
    }

    @GetMapping("/pagination")
    public Page<Job> getPage(@RequestParam int page,@RequestParam int size){
        return jobserv.getPage(page,size);
    }   

    @GetMapping("/get/sortedbycompany")
    public List<Job> getSorted(){
        return jobserv.getSorted();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobserv.deleteJob(id);
        return ResponseEntity.ok("Job deleted Successfully");
    } 

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,@RequestBody Job Job1){
        Job Job = jobserv.updateJob(id,Job1);
        return ResponseEntity.ok(Job);
    }

}
