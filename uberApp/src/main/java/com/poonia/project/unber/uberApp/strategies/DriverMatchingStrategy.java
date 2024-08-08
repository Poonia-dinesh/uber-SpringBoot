package com.poonia.project.unber.uberApp.strategies;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);

}
