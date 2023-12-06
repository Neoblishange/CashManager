package com.cash.back_cash_manager.services;

import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements DTO {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }

    public void createUser(User user) {
        Optional<User> optionalUser = userRepository.findByName(user.getName());
        if(optionalUser.isPresent()) {
            throw new IllegalArgumentException();
        }
        userRepository.save(user);
    }

    public void updateUser(Long id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException();
        }

        User userFromDB = user.get();

        if(!userFromDB.getName().equals(updatedUser.getName()) && !updatedUser.getName().isEmpty()) {
            userFromDB.setName(updatedUser.getName());
        }

        if(!userFromDB.getPassword().equals(updatedUser.getPassword()) && !updatedUser.getPassword().isEmpty()) {
            userFromDB.setPassword(updatedUser.getPassword());
        }
        userRepository.save(userFromDB);
    }

    public void deleteUser(User user) {
        Optional<User> optionalUser = userRepository.findByName(user.getName());
        if(optionalUser.isEmpty()) {
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
