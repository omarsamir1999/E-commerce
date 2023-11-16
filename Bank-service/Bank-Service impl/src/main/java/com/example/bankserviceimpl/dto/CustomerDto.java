package com.example.bankserviceimpl.dto;

import com.example.bankserviceimpl.enums.Gender;
import lombok.Data;

@Data
public class CustomerDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    private String address;

    private Gender gender;
}
