package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.entities.Transaction;
import com.cash.back_cash_manager.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;

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

    /*@PostMapping(path = "buy")
    public ResponseEntity<Object> buy() {

    }*/

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        transactionService.deleteTransaction(transaction);
        return new ResponseEntity<>(new JsonResponse("Your transaction has been deleted"), HttpStatus.OK);
    }
}
