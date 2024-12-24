package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RideDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.RideRequest;
import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.enums.RideRequestStatus;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import com.poonia.project.unber.uberApp.exceptions.ResourceNotFoundException;
import com.poonia.project.unber.uberApp.repositories.DriverRepository;
import com.poonia.project.unber.uberApp.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private  final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

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
        Driver saveDriver =  updateDriverAvailability(currentDriver, false);
        Ride ride =  rideService.createNewRide(rideRequest, saveDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Can't cancel the ride as the different driver");
        }

        if(! ride.getReideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled: "+ride.getReideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);

        updateDriverAvailability(driver, true);

        return modelMapper.map(ride, RideDto.class);
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

      paymentService.createNewPayment(saveRide);
      ratingService.createNewRating(saveRide);

       return modelMapper.map(saveRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot end a ride as he hasn't accepted earlier");
        }
        if(!ride.getReideStatus().equals(RideStatus.ONGOING)){
            new RuntimeException("Ride status is ongoing hence cannot be ended, status: "+ ride.getReideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
      Ride savedRide =   rideService.updateRideStatus(ride,RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(ride);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not the owner of ride");
        }
        if(!ride.getReideStatus().equals(RideStatus.ENDED)){
            new RuntimeException("Ride status is not ended hence cannot be rate"+ ride.getReideStatus());
        }
       return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user).orElseThrow(()->
                new ResourceNotFoundException("User is not associated as Driver with id : " + user.getId()));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public Driver createDriver(Driver driver) {
       return driverRepository.save(driver);
    }
}
