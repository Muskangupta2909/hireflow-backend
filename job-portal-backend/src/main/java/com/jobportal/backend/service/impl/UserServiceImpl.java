package com.jobportal.backend.service.impl;

import com.jobportal.backend.dto.UserLoginRequest;
import com.jobportal.backend.dto.UserRegisterRequest;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.exception.EmailAlreadyExistsException;
import com.jobportal.backend.repository.UserRepository;
import com.jobportal.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jobportal.backend.exception.AuthException;


    @Service
    public class UserServiceImpl implements UserService {

        // 1️⃣ variables
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        //final = ek baar aagya fr set ho ga change ni
        //dependency injection


        // constructor injection
        // 2️⃣ constructor
        public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }


        // 3️⃣ methods
        @Override
        public User register(UserRegisterRequest request) {

            //if email already exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists");
            }


            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setRole("USER");     //default role fix
            user.setPassword(passwordEncoder.encode(request.getPassword())); //for BCrypt password

            return userRepository.save(user);
        }
        @Override
        public User login(UserLoginRequest request) {

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new AuthException("Invalid email or password"));

            if (!passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
            )) {
                throw new AuthException("Invalid email or password");
            }
            return user;
        }
        @Override
        public User registerRecruiter(UserRegisterRequest request) {

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setRole("RECRUITER"); // ✅ fixed recruiter role
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            return userRepository.save(user);
        }


    }
