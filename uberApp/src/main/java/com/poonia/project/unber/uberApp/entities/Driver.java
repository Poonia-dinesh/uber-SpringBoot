package com.poonia.project.unber.uberApp.entities;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private  Boolean isavailable;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point currentLocation;
}
