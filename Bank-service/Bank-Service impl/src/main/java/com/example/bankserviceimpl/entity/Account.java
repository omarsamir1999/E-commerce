package com.example.bankserviceimpl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "national_id")
    private Customer customer;
    private String cardName;
    private String cardNumber;
    private int CVV;
    private float balance;
}
