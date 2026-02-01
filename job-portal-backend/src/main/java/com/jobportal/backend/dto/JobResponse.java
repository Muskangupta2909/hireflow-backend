package com.jobportal.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JobResponse {
    //Job ki id
    private Long id;

    // Job details
    private String title;
    private String description;
    private String location;
    private Double salary;

    // Job kab post hui
    private LocalDateTime createdAt;
}
