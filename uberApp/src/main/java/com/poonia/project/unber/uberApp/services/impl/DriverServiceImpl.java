package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.entities.enums.RideRequestStatus;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import com.poonia.project.unber.uberApp.exceptions.ResourceNotFoundException;
import com.poonia.project.unber.uberApp.repositories.DriverRepository;
import com.poonia.project.unber.uberApp.services.DriverService;
import com.poonia.project.unber.uberApp.services.RideRequestService;
import com.poonia.project.unber.uberApp.services.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private  final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw  new RuntimeException("Ride Request cann't be accepted "+ rideRequest.getRideRequestStatus());
        }


        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cann't accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver saveDriver = driverRepository.save(currentDriver);

        Ride ride =  rideService.createNewRide(rideRequest, saveDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
       Ride ride = rideService.getRideById(rideId);
       Driver driver = getCurrentDriver();
       if(!driver.equals(ride.getDriver())){
           throw new RuntimeException("Driver cannot start a ride as he hasn't accepted earlier");
       }

       if(!ride.getReideStatus().equals(RideStatus.CONFIRMED)){
           new RuntimeException("Ride status is not confirmed hence cannot be started, status: "+ ride.getReideStatus());
       }

       if(!otp.equals(ride.getOtp())){
           throw new RuntimeException("Otp is not correct, otp: "+ otp);
       }
       ride.setStartedAt(LocalDateTime.now());
      Ride saveRide =  rideService.updateRideStatus(ride, RideStatus.ONGOING);

       return modelMapper.map(saveRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()->
                new ResourceNotFoundException("Current driver not found with Id: " + 2));
    }


}
