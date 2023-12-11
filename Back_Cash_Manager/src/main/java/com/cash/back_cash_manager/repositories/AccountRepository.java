package com.cash.back_cash_manager.repositories;

import java.util.Optional;

import com.cash.back_cash_manager.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(int accountNumber);
    Optional<Account> findById(Long id);
}
