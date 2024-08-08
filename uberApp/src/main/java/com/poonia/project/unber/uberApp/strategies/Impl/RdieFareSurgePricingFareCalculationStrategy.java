package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RdieFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
