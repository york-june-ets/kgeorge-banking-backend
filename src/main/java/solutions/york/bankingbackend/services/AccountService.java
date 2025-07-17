package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.CreateAccountRequest;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Customer;
import solutions.york.bankingbackend.repositories.AccountRepository;
import solutions.york.bankingbackend.repositories.CustomerRepository;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account create(CreateAccountRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getCustomerId() == null) {throw new IllegalArgumentException("Customer id is required");}
        if (!(request.getAccountType().equals("CHECKING") || request.getAccountType().equals("SAVINGS")));
        if (!(request.getAccountStatus().equals("ACTIVE") || request.getAccountStatus().equals("CLOSED") || request.getAccountStatus().equals("SUSPENDED"))) {throw new IllegalArgumentException("Account status is required: ACTIVE, CLOSED, or SUSPENDED");}

        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
        if (customer.isEmpty()) {throw new IllegalArgumentException("Customer not found");}
        Account newAccount = new Account(customer.get(), request.getAccountType(), request.getAccountStatus());
        return accountRepository.save(newAccount);
    }

    public Account findByAccountNumber(Long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}
        return account.get();
    }

    public void updateAccountStatus(Long accountNumber, String accountStatus) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}

        if (!(accountStatus.equals("ACTIVE") || accountStatus.equals("SUSPENDED"))) {throw new IllegalArgumentException("Account status is required: ACTIVE or SUSPENDED");}
        account.get().setAccountStatus(accountStatus);
    }

    public void closeAccount(Long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty()) {throw new IllegalArgumentException("Account not found");}

        if (account.get().getBalance() != 0.00) {throw new IllegalArgumentException("Account balance must be zero");}
        account.get().setAccountStatus("CLOSED");
    }
}
