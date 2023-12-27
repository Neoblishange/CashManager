package com.cash.back_cash_manager.repositories;

import java.util.Optional;

import com.cash.back_cash_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findById(Long id);
}
