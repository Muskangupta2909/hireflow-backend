package com.jobportal.backend.controller;

import com.jobportal.backend.dto.ApplyJobRequest;
import com.jobportal.backend.dto.JobApplicationResponse;
import com.jobportal.backend.entity.JobApplication;
import com.jobportal.backend.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    // Apply job
    @PostMapping
    public ResponseEntity<JobApplicationResponse> apply(
            @Valid @RequestBody ApplyJobRequest request) {

        JobApplication saved = service.applyJob(request);

        JobApplicationResponse response = new JobApplicationResponse(
                saved.getId(),
                saved.getJobId(),
                saved.getUserId(),
                saved.getStatus(),
                saved.getAppliedAt()
        );

        return ResponseEntity.ok(response);
    }


    // jobid wise applicants list
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationResponse>> getByJob(
            @PathVariable Long jobId) {

        List<JobApplication> list = service.getApplicationsByJob(jobId);

        //Entity list -> DTO list convert
        List<JobApplicationResponse> responseList = list.stream()
                .map(app -> new JobApplicationResponse(
                        app.getId(),
                        app.getJobId(),
                        app.getUserId(),
                        app.getStatus(),
                        app.getAppliedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // user wise applied jobs list
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplicationResponse>> getByUser(@PathVariable Long userId) {

        List<JobApplication> list = service.getApplicationsByUser(userId);

        //Entity list -> DTO list convert
        List<JobApplicationResponse> responseList = list.stream()
                .map(app -> new JobApplicationResponse(
                        app.getId(),
                        app.getJobId(),
                        app.getUserId(),
                        app.getStatus(),
                        app.getAppliedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
