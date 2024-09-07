package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
