package com.example.jobs.Repository;

import com.example.jobs.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
}
