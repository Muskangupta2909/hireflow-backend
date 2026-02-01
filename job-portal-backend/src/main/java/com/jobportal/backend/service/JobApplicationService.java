package com.jobportal.backend.service;

import com.jobportal.backend.dto.ApplyJobRequest;
import com.jobportal.backend.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {
    JobApplication applyJob(ApplyJobRequest request);

    List<JobApplication> getApplicationsByJob(Long jobId); //list of job applications

    List<JobApplication> getApplicationsByUser(Long userId);//list of applied job by user

}
