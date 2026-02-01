package com.jobportal.backend.service.impl;

import com.jobportal.backend.dto.JobCreateRequest;
import com.jobportal.backend.dto.JobUpdateRequest;
import com.jobportal.backend.entity.Job;
import com.jobportal.backend.repository.JobRepository;
import com.jobportal.backend.service.JobService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(JobCreateRequest request) {

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setCreatedAt(LocalDateTime.now());

        return jobRepository.save(job);

    }

    @Override
    public List<Job> getAllJobs() {
        //database se saari jobs laa raha hai
        return jobRepository.findAll();
    }
    @Override
    public Job updateJob(Long jobId, JobUpdateRequest dto) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if(dto.getTitle() != null)
            job.setTitle(dto.getTitle());

        if(dto.getDescription() != null)
            job.setDescription(dto.getDescription());

        if(dto.getLocation() != null)
            job.setLocation(dto.getLocation());

        if(dto.getSalary() != null)
            job.setSalary(dto.getSalary());

        return jobRepository.save(job);
    }
    @Override
    public void deleteJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found");
        }
        jobRepository.deleteById(jobId);
    }
}
