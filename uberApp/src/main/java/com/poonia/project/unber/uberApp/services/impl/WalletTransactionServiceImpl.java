package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.dto.WalletTransactionDto;
import com.poonia.project.unber.uberApp.entities.WalletTransaction;
import com.poonia.project.unber.uberApp.repositories.WalletTransactionRepository;
import com.poonia.project.unber.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private  final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);

    }
}
