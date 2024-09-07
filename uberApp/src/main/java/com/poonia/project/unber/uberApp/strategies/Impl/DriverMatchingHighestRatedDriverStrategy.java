package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.repositories.DriverRepository;
import com.poonia.project.unber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    private  final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
  return driverRepository.findTenNearByTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
