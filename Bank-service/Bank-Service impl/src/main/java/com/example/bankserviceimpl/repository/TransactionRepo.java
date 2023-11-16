package com.example.bankserviceimpl.repository;

import com.example.bankserviceimpl.entity.Account;
import com.example.bankserviceimpl.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
