package com.jobportal.backend.service;

import com.jobportal.backend.dto.JobCreateRequest;
import com.jobportal.backend.dto.JobUpdateRequest;
import com.jobportal.backend.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobCreateRequest request);
    List<Job> getAllJobs();// new method: saari jobs laayega DB se

    Job updateJob(Long id, JobUpdateRequest request); //job update
    void deleteJob(Long id); // job delete
}
