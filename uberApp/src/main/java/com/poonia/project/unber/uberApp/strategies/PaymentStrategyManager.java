package com.poonia.project.unber.uberApp.strategies;


import com.poonia.project.unber.uberApp.entities.enums.PaymentMethod;
import com.poonia.project.unber.uberApp.strategies.Impl.CashPaymentStrategy;
import com.poonia.project.unber.uberApp.strategies.Impl.WalletPaymentStartegy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private  final WalletPaymentStartegy walletPaymentStartegy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
      return  switch (paymentMethod){
            case  WALLET ->  walletPaymentStartegy;
            case CASH ->  cashPaymentStrategy;
        };
    }
}
