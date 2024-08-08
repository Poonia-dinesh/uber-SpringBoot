package com.poonia.project.unber.uberApp.entities;

import com.poonia.project.unber.uberApp.entities.enums.TransactionMethod;
import com.poonia.project.unber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private  Ride ride;

    private  String transactionId;

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;
}
