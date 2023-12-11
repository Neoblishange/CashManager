package com.cash.back_cash_manager.services;

import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountByAccountNumber(int accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) {
            throw new NoSuchElementException();
        }
        return account.get();
    }

    public void createAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        if(optionalAccount.isPresent()) {
            throw new IllegalArgumentException();
        }
        accountRepository.save(account);
    }
}
