package com.jobportal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class JobApplicationResponse {
    private Long id;
    private Long jobId;
    private Long userId;
    private String status;
    private LocalDateTime appliedAt;
}
