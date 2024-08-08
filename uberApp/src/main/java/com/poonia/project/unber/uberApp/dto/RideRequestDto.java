package com.poonia.project.unber.uberApp.dto;


import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.entities.enums.PaymentMethod;
import com.poonia.project.unber.uberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
       private long id;
    private Point pickupLocation;
    private  Point dropoffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;


}