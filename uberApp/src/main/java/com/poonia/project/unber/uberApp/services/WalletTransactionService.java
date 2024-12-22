package com.poonia.project.unber.uberApp.services;

import com.poonia.project.unber.uberApp.dto.WalletTransactionDto;
import com.poonia.project.unber.uberApp.entities.Payment;
import com.poonia.project.unber.uberApp.entities.WalletTransaction;
import org.springframework.stereotype.Service;


public interface WalletTransactionService {
  void  createNewWalletTransaction(WalletTransaction walletTransaction);

}
