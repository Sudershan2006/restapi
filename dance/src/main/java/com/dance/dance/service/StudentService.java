package com.dance.dance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Service;

import com.dance.dance.entity.Student;
import com.dance.dance.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentrepo;

    public Student addEmployee(Student student){
        return studentrepo.save(student);
    }
    
    public List<Student> getEmployee(){
        return studentrepo.findAll();
    }

    public Student findById(Long id){
        return studentrepo.findById(id).orElse(null);
    }

    public List<Student> findByName(String name){
        return studentrepo.findByName(name);
    }
    public List<Student> findByEmail(String email){
        return studentrepo.findByEmail(email);
    }

    public Page<Student> getPage(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return studentrepo.findAll(pageable);
    }

    public List<Student> getSorted(){
        return studentrepo.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }


    public void deleteStudent(Long id){
        if(studentrepo.existsById(id)){
            studentrepo.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Student not found");
        }
    }
    
    public Student updateStudent(Long id,Student st){
        Student student = studentrepo.findById(id).orElseThrow(()->new EntityNotFoundException("Student not found"));
        st.setId(id);
        student.setId(st.getId());
        student.setName(st.getName());
        student.setEmailaddress(st.getEmailaddress());
        student.setPassword(st.getPassword());
        student.setPhone(st.getPhone());

        return studentrepo.save(student);
    }


}
