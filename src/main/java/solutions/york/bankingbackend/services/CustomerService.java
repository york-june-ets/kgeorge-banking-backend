package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.CustomerRequest;
import solutions.york.bankingbackend.dto.CustomerResponse;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Customer;
import solutions.york.bankingbackend.repositories.AccountRepository;
import solutions.york.bankingbackend.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    CustomerService(CustomerRepository customerRepository, AccountService accountService) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    public CustomerResponse create(CustomerRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getFirstName() == null || request.getFirstName().isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (request.getLastName() == null || request.getLastName().isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (request.getEmail() == null || request.getEmail().isBlank()) {throw new IllegalArgumentException("Email is required");}
        if (request.getPassword() == null || request.getPassword().isBlank()) {throw new IllegalArgumentException("Password is required");}

        boolean customerExists = customerRepository.findByEmail(request.getEmail()).isPresent();

        if (customerExists) {throw new IllegalArgumentException("A customer with that email already exists");} // is this the right exception? custom exception?

        Customer newCustomer = new Customer(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), request.getPhoneNumber());
        customerRepository.save(newCustomer);
        return new CustomerResponse(newCustomer);
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getFirstName() == null || request.getFirstName().isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (request.getLastName() == null || request.getLastName().isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (request.getEmail() == null || request.getEmail().isBlank()) {throw new IllegalArgumentException("Email is required");}
        if (request.getPassword() == null || request.getPassword().isBlank()) {throw new IllegalArgumentException("Password is required");}

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        if (existingCustomer.isArchived()) {throw new IllegalArgumentException("Cannot update archived customer");}

        existingCustomer.updateCustomerData(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), request.getPhoneNumber());
        customerRepository.save(existingCustomer);
        return new CustomerResponse(existingCustomer);
    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(CustomerResponse::new).toList();
    }

    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found")) ;
        return new CustomerResponse(customer);
    }

    public void archive(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customer.setArchived(true);
    }

    public List<Account> findAccounts(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return accountService.findByCustomerId(customer.getId());
    }
}
