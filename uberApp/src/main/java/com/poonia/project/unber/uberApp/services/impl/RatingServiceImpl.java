package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.DriverDto;
import com.poonia.project.unber.uberApp.dto.RiderDto;
import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Rating;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.exceptions.ResourceNotFoundException;
import com.poonia.project.unber.uberApp.repositories.DriverRepository;
import com.poonia.project.unber.uberApp.repositories.RatingRepository;
import com.poonia.project.unber.uberApp.repositories.RiderRepository;
import com.poonia.project.unber.uberApp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private  final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
         Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found with the ride id " + ride.getId()));
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating :: getDriverRating)
                .average().orElse(0.0);
        driver.setRating(newRating);

       Driver savedDriver = driverRepository.save(driver);

       return modelMapper.map(savedDriver, DriverDto.class);

    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found with the ride id " + ride.getId()));
        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating :: getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);

      Rider savedRider =   riderRepository.save(rider);
      return modelMapper.map(savedRider, RiderDto.class);

    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
