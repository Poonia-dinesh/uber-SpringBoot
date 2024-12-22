package com.poonia.project.unber.uberApp.dto;

import com.poonia.project.unber.uberApp.entities.Ride;
import com.poonia.project.unber.uberApp.entities.Wallet;
import com.poonia.project.unber.uberApp.entities.enums.TransactionMethod;
import com.poonia.project.unber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private  String transactionId;
    private WalletDto wallet;
    private LocalDateTime timeStamp;
}
