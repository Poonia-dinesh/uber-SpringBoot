package com.poonia.project.unber.uberApp.strategies.Impl;

import com.poonia.project.unber.uberApp.entities.Driver;
import com.poonia.project.unber.uberApp.entities.Payment;
import com.poonia.project.unber.uberApp.entities.Wallet;
import com.poonia.project.unber.uberApp.entities.enums.PaymentStatus;
import com.poonia.project.unber.uberApp.entities.enums.TransactionMethod;
import com.poonia.project.unber.uberApp.repositories.PaymentRepository;
import com.poonia.project.unber.uberApp.services.PaymentService;
import com.poonia.project.unber.uberApp.services.WalletService;
import com.poonia.project.unber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformCommision = payment.getAmount() * PLATFORM_CHARGE;

        walletService.diductMoneyToWallet(driver.getUser(), platformCommision, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
