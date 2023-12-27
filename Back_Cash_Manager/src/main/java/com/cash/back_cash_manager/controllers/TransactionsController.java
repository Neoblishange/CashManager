package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import com.cash.back_cash_manager.dto.UserDTO;
import com.cash.back_cash_manager.entities.Account;
import com.cash.back_cash_manager.entities.Transaction;
import com.cash.back_cash_manager.models.Payment;
import com.cash.back_cash_manager.services.AccountService;
import com.cash.back_cash_manager.services.TransactionService;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get all transactions", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions displayed")}
    )
    @GetMapping(path = "")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @Operation(summary = "Get all transactions by account number", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions displayed by account number",
                    content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Transaction.class))) }
            ),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content) }
    )
    @GetMapping(path = "{accountNumber}")
    public ResponseEntity<Object> getAllTransactionsOfOneAccount(@PathVariable int accountNumber) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Account not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Payment transaction", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment done"),
            @ApiResponse(responseCode = "400", description = "Payment refused",
                    content = @Content)}
    )
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

    @Operation(summary = "Delete transaction by id", security = @SecurityRequirement(name = "Bearer Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted"),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content)}
    )
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            transactionService.deleteTransaction(transaction);
            return new ResponseEntity<>(new JsonResponse("Your transaction has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Account not found"), HttpStatus.NOT_FOUND);
        }
    }
}
