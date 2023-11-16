package com.example.bankserviceimpl.repository;

import com.example.bankserviceimpl.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
