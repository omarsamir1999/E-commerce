package com.example.bankserviceimpl.service;

import com.example.bankserviceimpl.dto.AccountDto;
import com.example.bankserviceimpl.dto.CustomerDto;
import com.example.bankserviceimpl.dto.LoginDto;
import com.example.bankserviceimpl.dto.TransactionDto;
import com.example.bankserviceimpl.entity.Account;
import com.example.bankserviceimpl.entity.Customer;
import com.example.bankserviceimpl.entity.Transaction;
import com.example.bankserviceimpl.enums.Status;
import com.example.bankserviceimpl.enums.TransactionType;
import com.example.bankserviceimpl.error.BadRequestException;
import com.example.bankserviceimpl.error.NotFoundException;
import com.example.bankserviceimpl.repository.AccountRepo;
import com.example.bankserviceimpl.repository.CustomerRepo;
import com.example.bankserviceimpl.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {
    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public BankService(AccountRepo accountRepo, CustomerRepo customerRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
        this.transactionRepo = transactionRepo;
    }

    @Transactional
    public void addAccount(AccountDto accountDto) throws Exception {
        Customer customer = customerRepo.findById(accountDto.getCustomerId())
                .orElseThrow(() -> new Exception("Customer not found"));

        Account accountDb = new Account();
        accountDb.setCustomer(customer);
        accountDb.setCardName(accountDto.getCardName());
        accountDb.setCardNumber(accountDto.getCardNumber());
        accountDb.setCVV(accountDto.getCVV());
        accountDb.setBalance(accountDto.getInitialBalance());

        accountRepo.save(accountDb);
    }

    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        customer.setGender(customerDto.getGender());

        customerRepo.save(customer);
    }

    @Transactional
    public void createTransaction(TransactionDto transactionDto) {
        Account account = accountRepo.findById(transactionDto.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        if (transactionDto.getTransactionType() == TransactionType.Withdraw && account.getBalance() < transactionDto.getAmount()) {
            throw new BadRequestException("Insufficient balance for withdrawal");
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus(Status.Success);

        if (transactionDto.getTransactionType() == TransactionType.Withdraw) {
            account.setBalance(account.getBalance() - transactionDto.getAmount());
        } else {
            account.setBalance(account.getBalance() + transactionDto.getAmount());
        }

        accountRepo.save(account);
        transactionRepo.save(transaction);
    }

    public boolean accountLogin(LoginDto loginDto) {
        Optional<Customer> optionalCustomer = customerRepo.findById(loginDto.getCustomerId());
        return optionalCustomer.map(customer -> customer.getPassword().equals(loginDto.getPassword())).orElse(false);
    }

    public double getAccountBalance(Long accountId) {
        return accountRepo.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public List<TransactionDto> getAccountTransactions(Long accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));

        return transactionRepo.findByAccount(account)
                .stream()
                .map(transaction -> new TransactionDto(
                        transaction.getId(),
                        transaction.getAccount().getId(),
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getCreatedAt(),
                        transaction.getStatus()))
                .collect(Collectors.toList());
    }
}
