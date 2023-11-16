package com.example.bankserviceimpl.controller;

import com.example.bankserviceimpl.dto.AccountDto;
import com.example.bankserviceimpl.dto.CustomerDto;
import com.example.bankserviceimpl.dto.LoginDto;
import com.example.bankserviceimpl.dto.TransactionDto;
import com.example.bankserviceimpl.entity.Account;
import com.example.bankserviceimpl.entity.Transaction;
import com.example.bankserviceimpl.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-api")
@Validated
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @PostMapping("/add-account")
    public ResponseEntity<String> addAccount(@Valid @RequestBody AccountDto accountDto) throws Exception {
        bankService.addAccount(accountDto);
        return ResponseEntity.ok("Account added successfully");
    }

    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        bankService.createCustomer(customerDto);
        return ResponseEntity.ok("Customer created successfully");
    }


    @PostMapping("/create-transaction")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionDto transaction) {
        bankService.createTransaction(transaction);
        return ResponseEntity.ok("Transaction created successfully");
    }

    @PostMapping("/account-login")
    public ResponseEntity<String> accountLogin(@Valid @RequestBody LoginDto loginDto) {
        if (bankService.accountLogin(loginDto)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }

    @GetMapping("/view-balance/{accountId}")
    public ResponseEntity<Double> viewBalance(@PathVariable Long accountId) {
        double balance = bankService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/view-transactions/{accountId}")
    public ResponseEntity<List<TransactionDto>> viewTransactions(@PathVariable Long accountId) {
        List<TransactionDto> transactions = bankService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
