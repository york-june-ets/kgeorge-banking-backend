package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.TransferRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.models.Transfer;
import solutions.york.bankingbackend.repositories.TransferRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final AccountService accountService;
    public TransferService(TransferRepository transferRepository, AccountService accountService) {
        this.transferRepository = transferRepository;
        this.accountService = accountService;
    }

    public Transfer create(TransferRequest request) {
        if (request == null) {throw new IllegalArgumentException("Request body is required");}
        if (request.getAmount() <= 0.00) {throw new IllegalArgumentException("Amount must be greater than zero");}
        if (request.getAccountNumber() == null) {throw new IllegalArgumentException("Account number is required");}
        if (request.getTransactionType() == null) {throw new IllegalArgumentException("Transaction type is required");}
        if (request.getRecipientAccountNumber() == null) {throw new IllegalArgumentException("Recipient account number is required");}

        Account account = accountService.findByAccountNumber(request.getAccountNumber());
        Account recipientAccount = accountService.findByAccountNumber(request.getRecipientAccountNumber());

        try {
            Transaction.Type type = Transaction.Type.valueOf(request.getTransactionType());

            Transfer transfer = new Transfer(account, recipientAccount, request.getAmount());
            return transferRepository.save(transfer);

        } catch (Exception e) {
            throw new IllegalArgumentException("Transaction type is invalid");
        }
    }
}
