package com.jobportal.backend.controller;

import com.jobportal.backend.dto.JobCreateRequest;
import com.jobportal.backend.dto.JobResponse;
import com.jobportal.backend.dto.JobUpdateRequest;
import com.jobportal.backend.entity.Job;
import com.jobportal.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Job post create
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping //post job
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobCreateRequest request) {
        Job saved = jobService.createJob(request);

        JobResponse response = new JobResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getLocation(),
                saved.getSalary(),
                saved.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping //get all jobs
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();

        List<JobResponse> responseList = jobs.stream()
                .map(job -> new JobResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getLocation(),
                        job.getSalary(),
                        job.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
    @PutMapping("/{jobId}") //update job
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long jobId,
            @RequestBody JobUpdateRequest request
    ) {
        Job updated = jobService.updateJob(jobId, request);

        JobResponse response = new JobResponse(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getLocation(),
                updated.getSalary(),
                updated.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{jobId}") //Delete job by using jobid
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {

        jobService.deleteJob(jobId);

        return ResponseEntity.ok("Job deleted successfully");
    }




}
