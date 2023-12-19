package com.cash.back_cash_manager.services;

import com.cash.back_cash_manager.dto.UserDTO;
import com.cash.back_cash_manager.entities.User;

public interface DTO {
    default UserDTO convertUser(User user) {
        return new UserDTO(user.getId(), user.getName());
    }
}
