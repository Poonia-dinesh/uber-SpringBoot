package com.poonia.project.unber.uberApp.services;

import com.fasterxml.jackson.databind.node.LongNode;
import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideBy(Long rideId);


    void matchWithDriver(RideRequestDto rideRequestDto);

     Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

     Ride updateRideStatus(Long rideID, RideStatus rideStatus);


     Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

     Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest );
}
