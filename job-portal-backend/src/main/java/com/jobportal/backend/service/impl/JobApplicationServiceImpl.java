package com.jobportal.backend.service.impl;

import com.jobportal.backend.dto.ApplyJobRequest;
import com.jobportal.backend.entity.JobApplication;
import com.jobportal.backend.repository.JobApplicationRepository;
import com.jobportal.backend.service.JobApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public JobApplication applyJob(ApplyJobRequest request) {

        // check: same user same job pe dubara apply na kare
        if (repository.findByJobIdAndUserId(request.getJobId(), request.getUserId()).isPresent()) {
            throw new RuntimeException("You already applied for this job");
        }

        JobApplication application = new JobApplication();
        application.setJobId(request.getJobId());
        application.setUserId(request.getUserId());
        application.setStatus("PENDING");
        application.setAppliedAt(LocalDateTime.now());

        return repository.save(application);
    }

    @Override
    public List<JobApplication> getApplicationsByJob(Long jobId) {
        return repository.findByJobId(jobId);
    }

    @Override
    public List<JobApplication> getApplicationsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}
