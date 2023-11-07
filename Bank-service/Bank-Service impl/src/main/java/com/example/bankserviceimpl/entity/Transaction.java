package com.example.bankserviceimpl.entity;

import com.example.bankserviceimpl.enums.Status;
import com.example.bankserviceimpl.enums.TransactionType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private TransactionType transactionType;
    private float amount;
    @Timestamp
    private LocalDateTime createdAt;
    private Status status;

}
