package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.AccountRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Customer;
import solutions.york.bankingbackend.models.Transaction;
import solutions.york.bankingbackend.repositories.AccountRepository;
import solutions.york.bankingbackend.repositories.CustomerRepository;
import solutions.york.bankingbackend.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account create(AccountRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getCustomerId() == null) {throw new IllegalArgumentException("Customer id is required");}
        if (request.getAccountType() == null) {throw new IllegalArgumentException("Account type is required");}
        if (request.getAccountStatus() == null) {throw new IllegalArgumentException("Account status is required");}

        try {
            Account.Type type = Account.Type.valueOf(request.getAccountType());
            Account.Status status = Account.Status.valueOf(request.getAccountStatus());

            Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
            if (customer.isEmpty()) {throw new IllegalArgumentException("Customer not found");}
            if (customer.get().isArchived()) {throw new IllegalArgumentException("Cannot create account for archived customer");}
            Account newAccount = new Account(customer.get(), type, status);
            return accountRepository.save(newAccount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Account type or status is invalid");
        }
    }

    public Account findByAccountNumber(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}
        return account.get();
    }

    public List<Account> findByCustomerId(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {throw new IllegalArgumentException("Customer not found");}
        return accountRepository.findByCustomerId(customerId);
    }

    public void updateAccountStatus(Long accountNumber, String accountStatus) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}
        if (accountStatus == null || accountStatus.isBlank()) {throw new IllegalArgumentException("Account status is required");}
        try {
            Account.Status status = Account.Status.valueOf(accountStatus);
            account.get().setAccountStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Account status is invalid");
        }
    }

    public void closeAccount(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}

        if (account.get().getBalance() != 0.00) {throw new IllegalArgumentException("Account balance must be zero");}
        account.get().setAccountStatus(Account.Status.CLOSED);
    }

}
