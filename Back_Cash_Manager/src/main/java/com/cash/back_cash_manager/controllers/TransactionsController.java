package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.Transaction;
import com.cash.back_cash_manager.models.Payment;
import com.cash.back_cash_manager.services.AccountService;
import com.cash.back_cash_manager.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(path = "{accountNumber}")
    public ResponseEntity<List<Transaction>> getAllTransactionsOfOneAccount(@PathVariable int accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping(path = "buy")
    public ResponseEntity<Object> buy(@RequestBody Payment payment) {
        Account account = accountService.getAccountByAccountNumber(payment.getAccountNumber());
        BigDecimal amountAccount = account.getAmount();
        BigDecimal price = payment.getAmount();
        if(amountAccount.floatValue() >= price.floatValue()){
            BigDecimal newAmount = amountAccount.subtract(price);
            transactionService.createTransaction(new Transaction(account , price));
            account.setAmount(newAmount);
            accountService.updateAccount(account.getId(),account);
            return new ResponseEntity<>(new JsonResponse("Transaction acceptée"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new JsonResponse("Transaction refusée"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        transactionService.deleteTransaction(transaction);
        return new ResponseEntity<>(new JsonResponse("Your transaction has been deleted"), HttpStatus.OK);
    }
}
