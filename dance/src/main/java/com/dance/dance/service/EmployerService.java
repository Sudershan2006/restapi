package com.dance.dance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dance.dance.entity.Employer;
import com.dance.dance.repository.EmployerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployerService {
    @Autowired
    EmployerRepository emprepo;

    public List<Employer> findAll(){
        return emprepo.findAll();
    }

    public Employer addEmployer(Employer emp){
        return emprepo.save(emp);
    }

    public Employer findById(Long id){
        return emprepo.findById(id).orElse(null);
    }

    public List<Employer> findByName(String name){
        return emprepo.findByName(name);
    }

    public List<Employer> findByEmail(String email){
        return emprepo.findByEmail(email);
    }

    public List<Employer> getSorted(){
        return emprepo.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    public void deleteEmployer(Long id){
        if(emprepo.existsById(id)){
            emprepo.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Employer not found");
        }
    }

    public Page<Employer> getPage(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return emprepo.findAll(pageable);
    }
    
    public Employer updateEmployer(Long id,Employer st){
        Employer employer = emprepo.findById(id).orElseThrow(()->new EntityNotFoundException("Employer not found"));
        st.setId(id);
        employer.setId(st.getId());
        employer.setName(st.getName());
        employer.setEmailaddress(st.getEmailaddress());
        employer.setPassword(st.getPassword());
        employer.setCompany(st.getCompany());
        employer.setDesignation(st.getDesignation());

        return emprepo.save(employer);
    }




    
}
