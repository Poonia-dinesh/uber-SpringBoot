package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.SignupDto;
import com.poonia.project.unber.uberApp.services.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public SignupDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
