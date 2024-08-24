package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Todo add service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
