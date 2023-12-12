package com.cash.back_cash_manager.repositories;

import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long/*, Account*/> {
    Optional<List<Transaction>> findByAccount(Account account);
    Optional<Transaction> findById(Long id);
}
