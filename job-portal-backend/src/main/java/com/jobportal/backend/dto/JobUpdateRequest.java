package com.jobportal.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobUpdateRequest {
    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    private Double salary;
}
