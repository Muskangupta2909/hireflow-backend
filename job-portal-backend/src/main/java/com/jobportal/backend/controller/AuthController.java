package com.jobportal.backend.controller;

import com.jobportal.backend.dto.LoginResponse;
import com.jobportal.backend.dto.UserLoginRequest;
import com.jobportal.backend.dto.UserRegisterRequest;
import com.jobportal.backend.dto.UserResponse;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.security.JwtUtil;
import com.jobportal.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//LOgin and register
@RestController
@AllArgsConstructor

@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService; //Dependency Injection
    private final JwtUtil jwtUtil;

//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRegisterRequest request) {
        User savedUser = userService.register(request); //interface call krne k lie
        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {

        User user = userService.login(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new LoginResponse(response, token));
    }@PostMapping("/register-recruiter")
    public ResponseEntity<UserResponse> registerRecruiter(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        User savedUser = userService.registerRecruiter(request);

        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

        return ResponseEntity.ok(response);
    }



}
