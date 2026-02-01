package com.jobportal.backend.repository;


import com.jobportal.backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

    Optional<JobApplication> findByJobIdAndUserId(Long jobId, Long userId);// One user can apply only once on one job

    List<JobApplication> findByJobId(Long jobId); // Recruiter view: job ke saare applicants

    List<JobApplication> findByUserId(Long userId);// User view: user ne jitni jobs apply ki

}
