package com.poonia.project.unber.uberApp.entities;

import com.poonia.project.unber.uberApp.entities.enums.PaymentMethod;
import com.poonia.project.unber.uberApp.entities.enums.RideRequestStatus;
import com.poonia.project.unber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private  Point dropoffLocation;



    // Ride creation  time
    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Driver driver;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus reideStatus;

    private  Double fare;

    private String otp;
    // Ride staretd time
    private LocalDateTime startedAt;

    private LocalDateTime endedAt;


}