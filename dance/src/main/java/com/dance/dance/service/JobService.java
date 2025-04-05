package com.dance.dance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dance.dance.entity.Job;
import com.dance.dance.repository.JobRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JobService {
    @Autowired
    JobRepository jobrepo;

    public List<Job> findAll(){
        return jobrepo.findAll();
    }

    public Job addJob(Job job){
        return jobrepo.save(job);
    }

    public Job findById(Long id){
        return jobrepo.findById(id).orElse(null);
    }

    public List<Job> findByCompany(String company){
        return jobrepo.findByCompany(company);
    }

    public List<Job> findByPosition(String position){
        return jobrepo.findByPosition(position);
    }

    public List<Job> findByDate(LocalDate date){
        return jobrepo.findByDate(date);
    }

    public List<Job> getSorted(){
        return jobrepo.findAll(Sort.by(Sort.Direction.ASC,"company"));
    }

    public void deleteJob(Long id){
        if(jobrepo.existsById(id)){
            jobrepo.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Job not found");
        }
    }

    public Page<Job> getPage(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return jobrepo.findAll(pageable);
    }
    
    public Job updateJob(Long id,Job st){
        Job job = jobrepo.findById(id).orElseThrow(()->new EntityNotFoundException("Job not found"));
        st.setId(id);
        job.setId(st.getId());
        job.setCompany(st.getCompany());
        job.setDescription(st.getDescription());
        job.setPosition(st.getPosition());
        job.setDeadline(st.getDeadline());

        return jobrepo.save(job);
    }



}
