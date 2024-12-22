package com.poonia.project.unber.uberApp.strategies;

import com.poonia.project.unber.uberApp.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_CHARGE = 0.3;

    void processPayment(Payment payment);

}