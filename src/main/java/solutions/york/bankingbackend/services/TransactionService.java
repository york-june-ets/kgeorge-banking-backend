package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.TransactionRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.repositories.AccountRepository;
import solutions.york.bankingbackend.repositories.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction create(TransactionRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getAmount() <= 0.00) {throw new IllegalArgumentException("Amount must be greater than zero");}
        if (request.getAccountNumber() == null) {throw new IllegalArgumentException("Account number is required");}
        if (request.getTransactionType() == null) {throw new IllegalArgumentException("Transaction type is required");}

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        try {
            Transaction.Type type = Transaction.Type.valueOf(request.getTransactionType());
            if (type == Transaction.Type.TRANSFER) {throw new IllegalArgumentException("Transfers must be created using api/transfers endpoint");}
            Transaction transaction = new Transaction(type, account, request.getAmount());
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new IllegalArgumentException("Transaction type is invalid");
        }
    }
}
