package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.dto.UserDTO;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = { @Content(schema = @Schema(implementation = UserDTO.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) }
    )
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> users(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            UserDTO userDTO = userService.convertUser(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "User already exists",
                    content = @Content) }
    )
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(new JsonResponse("Your account has been created"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("User already exists"), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)}
    )
    @PutMapping(path = "{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        try {
            userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(new JsonResponse("Your account has been updated"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) }
    )
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            userService.deleteUser(user);
            return new ResponseEntity<>(new JsonResponse("Your account has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Account not found"), HttpStatus.NOT_FOUND);
        }
    }
    /*
    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        JsonResponse response = new JsonResponse("test");
        return ResponseEntity.ok().body(response);
    }*/
}
