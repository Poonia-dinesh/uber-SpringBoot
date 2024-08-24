package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.services.DistanceService;
import com.poonia.project.unber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
       double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
               rideRequest.getDropoffLocation()
       );
       return distance * RIDE_FARE_MULTIPLIER;

    }
}
