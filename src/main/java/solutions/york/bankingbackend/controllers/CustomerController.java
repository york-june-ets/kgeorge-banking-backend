package solutions.york.bankingbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.york.bankingbackend.dto.CustomerRequest;
import solutions.york.bankingbackend.dto.CustomerResponse;
import solutions.york.bankingbackend.models.Account;
import solutions.york.bankingbackend.models.Customer;
import solutions.york.bankingbackend.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse createdCustomer = customerService.create(request);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        CustomerResponse updatedCustomer = customerService.update(id, request);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerResponse> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<Account>> getAccountsForCustomer(@PathVariable Long id) {
        List<Account> accounts = customerService.findAccounts(id);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> archiveCustomer(@PathVariable Long id) {
        customerService.archive(id);
        return ResponseEntity.ok("Customer account closed successfully");
    }
}
