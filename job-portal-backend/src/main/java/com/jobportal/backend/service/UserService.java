package com.jobportal.backend.service;

import com.jobportal.backend.dto.UserLoginRequest;
import com.jobportal.backend.dto.UserRegisterRequest;
import com.jobportal.backend.entity.User;

public interface UserService {
    User register(UserRegisterRequest request);
    User login(UserLoginRequest request);
    User registerRecruiter(UserRegisterRequest request);


}
