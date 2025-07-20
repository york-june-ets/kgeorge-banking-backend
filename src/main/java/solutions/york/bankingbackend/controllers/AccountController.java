package solutions.york.bankingbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.york.bankingbackend.dto.AccountRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(AccountRequest request) {
        Account createdAccount = accountService.create(request);
        return ResponseEntity.ok(createdAccount);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<String> updateAccountStatus(@PathVariable Long accountNumber, String accountStatus) {
        accountService.updateAccountStatus(accountNumber, accountStatus);
        return ResponseEntity.ok("Account status updated successfully");
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
        accountService.closeAccount(accountNumber);
        return ResponseEntity.ok("Account closed successfully");
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Long accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        List<Transaction> transactions = accountService.findTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}
