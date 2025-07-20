package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.TransactionRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction create(TransactionRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getAmount() <= 0.00) {throw new IllegalArgumentException("Amount must be greater than zero");}
        if (request.getAccountNumber() == null) {throw new IllegalArgumentException("Account number is required");}
        if (request.getTransactionType() == null) {throw new IllegalArgumentException("Transaction type is required");}

        Account account = accountService.findByAccountNumber(request.getAccountNumber());
        if (account.getAccountStatus() != Account.Status.ACTIVE) {throw new IllegalArgumentException("Account is not active");}

        try {
            Transaction.Type type = Transaction.Type.valueOf(request.getTransactionType());
            if (type == Transaction.Type.TRANSFER) {throw new IllegalArgumentException("Transfers must be created using api/transfers endpoint");}
            Transaction transaction = new Transaction(type, account, request.getAmount());
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new IllegalArgumentException("Transaction type is invalid");
        }
    }

//    public List<Transaction> filterTransactions(
//            Long accountNumber,
//            String type,
//            java.time.LocalDate fromDate,
//            java.time.LocalDate toDate,
//            Double minAmount,
//            Double maxAmount
//    ) {
//        try {
//            Transaction.Type transactionType = Transaction.Type.valueOf(type);
//            return transactionRepository.findWithFilters(
//                    accountNumber, transactionType, fromDate, toDate, minAmount, maxAmount
//            );
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid data type");
//        }
//    }

    public List<Transaction> findTransactionsByAccountNumber(Long accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        if (account == null) {throw new IllegalArgumentException("Account not found");}
        return transactionRepository.findByAccount_AccountNumber(account.getAccountNumber());
    }
}
