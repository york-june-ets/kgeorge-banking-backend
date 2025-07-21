package solutions.york.bankingbackend.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.york.bankingbackend.dto.TransactionRequest;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.services.TransactionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.create(request);
        return ResponseEntity.ok(transaction);
    }
}
