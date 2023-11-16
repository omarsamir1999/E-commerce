package com.example.bankserviceimpl.dto;

import com.example.bankserviceimpl.enums.Status;
import com.example.bankserviceimpl.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private Long accountId;
    private TransactionType transactionType;
    private float amount;
    private LocalDateTime createdAt;
    private Status status;

}
