package com.dance.dance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dance.dance.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{
    
    public List<Employer> findByName(String name);

    @Query("SELECT e FROM Employer e WHERE e.emailaddress= :email")
    public List<Employer> findByEmail(String email);
}
