package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.SignupDto;

public interface AuthService {
    String login(String email, String password);

    SignupDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}
