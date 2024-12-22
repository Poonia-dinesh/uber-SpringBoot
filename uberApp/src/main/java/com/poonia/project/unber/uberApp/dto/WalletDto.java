package com.poonia.project.unber.uberApp.dto;

import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private  Long id;
    private UserDto user;
    private  Double balance;
    private List<WalletTransactionDto> transactions;
}
