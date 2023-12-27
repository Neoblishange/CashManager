package com.cash.back_cash_manager.services;

import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.Transaction;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.repositories.AccountRepository;
import com.cash.back_cash_manager.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public AccountRepository accountRepository;

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()) {
            throw new NoSuchElementException();
        }
        return transaction.get();
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) {
            throw new NoSuchElementException();
        }
        Optional<List<Transaction>> transactions = transactionRepository.findByAccount(account.get());
        if(transactions.isEmpty()) {
            throw new NoSuchElementException();
        }
        return transactions.get();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Transaction transaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transaction.getId());
        if(optionalTransaction.isEmpty()) {
            throw new IllegalArgumentException();
        }
        transactionRepository.delete(transaction);
    }
}
