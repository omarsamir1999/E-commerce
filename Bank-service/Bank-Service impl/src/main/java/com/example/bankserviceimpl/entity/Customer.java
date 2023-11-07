package com.example.bankserviceimpl.entity;

import com.example.bankserviceimpl.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nationalId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Gender gender;
    @Lob
    private byte[] image;
    

}
