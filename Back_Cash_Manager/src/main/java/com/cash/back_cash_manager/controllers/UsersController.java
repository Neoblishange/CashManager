package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.dto.UserDTO;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "{id}")
    public ResponseEntity<UserDTO> users(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userService.convertUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(new JsonResponse("Your account has been created"), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(new JsonResponse("Your account has been updated"), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return new ResponseEntity<>(new JsonResponse("Your account has been deleted"), HttpStatus.OK);
    }
    /*
    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        JsonResponse response = new JsonResponse("test");
        return ResponseEntity.ok().body(response);
    }*/
}
