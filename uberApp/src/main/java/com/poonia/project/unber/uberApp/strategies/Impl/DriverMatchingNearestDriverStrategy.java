package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
