package com.poonia.project.unber.uberApp.dto;

import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.entities.enums.PaymentMethod;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private long id;
    private Point pickupLocation;
    private  Point dropoffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus reideStatus;
    private  Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;



}
