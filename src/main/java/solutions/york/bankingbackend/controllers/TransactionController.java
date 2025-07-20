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

//    @GetMapping
//    public ResponseEntity<List<Transaction>> filterTransactions(
//            @RequestParam(required = false) Long accountNumber,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
//            @RequestParam(required = false) Double minAmount,
//            @RequestParam(required = false) Double maxAmount
//    ) {
//        List<Transaction> transactions = transactionService.filterTransactions(
//                accountNumber, type, fromDate, toDate, minAmount, maxAmount
//        );
//        return ResponseEntity.ok(transactions);
//    }


}
