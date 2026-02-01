package com.jobportal.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest { //yeh class sirf input lene k lie h
//    @NotBlank → empty nahi ho sakta
//    @Email → email format check
//    @Size → password length
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;



}
