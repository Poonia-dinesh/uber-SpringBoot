package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.Wallet;
import com.poonia.project.unber.uberApp.entities.enums.TransactionMethod;
import org.springframework.stereotype.Service;


public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    Wallet diductMoneyToWallet(User user, Double amount,  String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withradwAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
