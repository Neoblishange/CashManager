package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.dto.UserDTO;
import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.services.AccountService;
import com.cash.back_cash_manager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get account by account number", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found",
                    content = { @Content(schema = @Schema(implementation = Account.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content) }
    )
    @GetMapping(path = "{accountNumber}")
    public ResponseEntity<Object> getAccountById(@PathVariable("accountNumber") int accountNumber) {
        try {
            Account account = accountService.getAccountByAccountNumber(accountNumber);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Account not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new account", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "Account already exists",
                    content = @Content) }
    )
    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody Account account) {
        try {
            accountService.createAccount(account);
            return new ResponseEntity<>(new JsonResponse("Your account has been created"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Account already exists"), HttpStatus.BAD_REQUEST);
        }
    }
}
