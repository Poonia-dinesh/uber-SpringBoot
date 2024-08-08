package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideDto;
import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;
import com.poonia.project.unber.uberApp.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderSeriveImpl implements RiderService {
    @Override
    public RideRequestDto resuestRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDider(Long rideId, Integer rating) {
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
}
