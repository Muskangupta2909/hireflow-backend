package com.jobportal.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplyJobRequest {
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "User ID is required")
    private Long userId;
}
