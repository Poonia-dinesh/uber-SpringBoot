package com.poonia.project.unber.uberApp.repositories;

import com.poonia.project.unber.uberApp.entities.Payment;
import com.poonia.project.unber.uberApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
   Optional<Payment>  findByRide(Ride ride);
}
