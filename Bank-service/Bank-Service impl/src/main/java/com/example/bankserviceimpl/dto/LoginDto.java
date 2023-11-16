package com.example.bankserviceimpl.dto;

import lombok.Data;


@Data
public class LoginDto {

    private Long customerId;

    private String password;
}

