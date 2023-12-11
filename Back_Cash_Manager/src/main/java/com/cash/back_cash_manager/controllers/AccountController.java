package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.User;
import com.cash.back_cash_manager.services.AccountService;
import com.cash.back_cash_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "{accountNumber}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountNumber") int accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>(new JsonResponse("Your account has been created"), HttpStatus.CREATED);
    }
}
