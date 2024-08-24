package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideDto;
import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.enums.RideRequestStatus;
import com.poonia.project.unber.uberApp.repositories.RideRequestRepository;
import com.poonia.project.unber.uberApp.repositories.RiderRepository;
import com.poonia.project.unber.uberApp.services.RiderService;
import com.poonia.project.unber.uberApp.strategies.DriverMatchingStrategy;
import com.poonia.project.unber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderSeriveImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto resuestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);

        rideRequest.setFare(fare);

       RideRequest savedRideRequest =  rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDriver(rideRequest);

       return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
       return riderRepository.save(rider);

    }
}
