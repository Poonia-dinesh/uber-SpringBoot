package com.poonia.project.unber.uberApp.strategies;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.RideRequest;


public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10D;
    double calculateFare(RideRequest rideRequest);

}
