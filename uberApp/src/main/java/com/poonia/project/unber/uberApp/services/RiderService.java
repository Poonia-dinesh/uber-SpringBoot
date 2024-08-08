package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideDto;
import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;

import java.util.List;

public interface RiderService {

    RideRequestDto resuestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDider(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();
}
