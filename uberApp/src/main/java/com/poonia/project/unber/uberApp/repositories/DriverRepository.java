package com.poonia.project.unber.uberApp.repositories;

import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Rider;
import com.poonia.project.unber.uberApp.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

@Query(value = "Select d.*, ST_Distance(d.current_location, :pickupLocation) AS distance "
        + " FROM driver d " +
        "where d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 1000000) " +
        "ORDER BY distance " +
        "LIMIT 10", nativeQuery = true)
       List<Driver> findTenNearestDriver(Point pickupLocation);


@Query(value = "SELECT d.* FROM driver d" +
        " WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 1500000) " +
        "ORDER BY d.rating DESC " +
        "LIMIT 10", nativeQuery = true)
List<Driver> findTenNearByTopRatedDrivers(Point pickupLocation);

    Optional<Driver> findByUser(User user);
}
