package com.example.bankserviceimpl.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AccountDto {

    private Long customerId;

    private String cardName;

    private String cardNumber;

    private Integer CVV;

    private Float initialBalance;
}

