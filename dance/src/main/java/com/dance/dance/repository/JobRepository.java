package com.dance.dance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dance.dance.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    public List<Job> findByCompany(String company);

    @Query("SELECT e FROM Job e WHERE e.position= :position")
    public List<Job> findByPosition(String position);

    @Query("SELECT e FROM Job e WHERE e.deadline<= :date")
    public List<Job> findByDate(LocalDate date);
}

