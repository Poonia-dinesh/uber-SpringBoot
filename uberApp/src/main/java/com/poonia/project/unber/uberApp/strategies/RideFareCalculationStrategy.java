package com.poonia.project.unber.uberApp.strategies;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;


public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);

}
