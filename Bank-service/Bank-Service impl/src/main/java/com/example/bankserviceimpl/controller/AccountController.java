package com.example.bankserviceimpl.controller;

import com.example.bankserviceimpl.entity.Account;
import com.example.bankserviceimpl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping({"/api/createAccount"})
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}
