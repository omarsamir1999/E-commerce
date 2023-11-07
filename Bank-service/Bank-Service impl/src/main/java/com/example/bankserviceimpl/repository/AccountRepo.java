package com.example.bankserviceimpl.repository;

import com.example.bankserviceimpl.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
