package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.entities.Payment;
import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.enums.PaymentStatus;
import org.springframework.stereotype.Service;


public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
