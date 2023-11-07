package com.example.bankserviceimpl.service;

import com.example.bankserviceimpl.entity.Account;
import com.example.bankserviceimpl.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }
}
