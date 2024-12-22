package com.poonia.project.unber.uberApp.services;

import com.fasterxml.jackson.databind.node.LongNode;
import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideRequestDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);


    void matchWithDriver(RideRequestDto rideRequestDto);

     Ride createNewRide(RideRequest rideRequest, Driver driver);

     Ride updateRideStatus(Ride ride, RideStatus rideStatus);


     Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

     Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest );
}
