package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.SignupDto;
import com.poonia.project.unber.uberApp.dto.UserDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.enums.Role;
import com.poonia.project.unber.uberApp.exceptions.ResourceNotFoundException;
import com.poonia.project.unber.uberApp.exceptions.RuntimeConflictException;
import com.poonia.project.unber.uberApp.repositories.UserRepository;
import com.poonia.project.unber.uberApp.security.JWTService;
import com.poonia.project.unber.uberApp.services.AuthService;
import com.poonia.project.unber.uberApp.services.DriverService;
import com.poonia.project.unber.uberApp.services.RiderService;
import com.poonia.project.unber.uberApp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private  final ModelMapper modelMapper;
    private  final RiderService riderService;
    private final WalletService walletService;
    private  final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;



    @Override
    public String[] login(String email, String password) {
        Authentication authenticate =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authenticate.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new String[]{accessToken, refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
    User user =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
    if(user !=null) {
      throw new RuntimeConflictException("Cannot signup, User already exists with email " + signupDto.getEmail());
    };
      User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        // create user related entities

      riderService.createNewRider(savedUser);
      walletService.createNewWallet(savedUser);

      // TODO add wallet related services here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
       User user = userRepository.findById(userId).orElseThrow(()->
               new ResourceNotFoundException("User not found with user id :" + userId));
       if(user.getRoles().contains(Role.DRIVER)){
           throw new RuntimeConflictException("user is already a Driver with the id : " + userId);
       }

       Driver createdDriver = Driver.builder()
               .user(user)
               .rating(0.0)
               .vehicleId(vehicleId)
               .available(true)
               .build();
       user.getRoles().add(Role.DRIVER);
       userRepository.save(user);

       Driver savedDriver = driverService.createDriver(createdDriver);

       return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User is not found with the id: " + userId));

        return jwtService.generateAccessToken(user);
    }
}
