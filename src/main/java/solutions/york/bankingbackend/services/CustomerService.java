package solutions.york.bankingbackend.services;

import org.springframework.stereotype.Service;
import solutions.york.bankingbackend.dto.CustomerRequest;
import solutions.york.bankingbackend.models.Customer;
import solutions.york.bankingbackend.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(CustomerRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getFirstName() == null || request.getFirstName().isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (request.getLastName() == null || request.getLastName().isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (request.getEmail() == null || request.getEmail().isBlank()) {throw new IllegalArgumentException("Email is required");}

        boolean customerExists = customerRepository.findByEmail(request.getEmail()).isPresent();

        if (customerExists) {throw new IllegalArgumentException("A customer with that email already exists");} // is this the right exception? custom exception?

        Customer newCustomer = new Customer(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber());
        return customerRepository.save(newCustomer);
    }

    public Customer update(Long id, CustomerRequest request) {
        if (request == null) { throw new IllegalArgumentException("Request body is required");}
        if (request.getFirstName() == null || request.getFirstName().isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (request.getLastName() == null || request.getLastName().isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (request.getEmail() == null || request.getEmail().isBlank()) {throw new IllegalArgumentException("Email is required");}

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        existingCustomer.updateCustomerData(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {throw new IllegalArgumentException("Customer not found");}
        return customer.get();
    }

    public void deleteById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {throw new IllegalArgumentException("Customer not found");}
        customerRepository.deleteById(id);
    }
}
