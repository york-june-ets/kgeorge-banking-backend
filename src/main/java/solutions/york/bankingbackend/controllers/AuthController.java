package solutions.york.bankingbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.york.bankingbackend.dto.CustomerResponse;
import solutions.york.bankingbackend.dto.LoginRequest;
import solutions.york.bankingbackend.services.CustomerService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final CustomerService customerService;
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponse> login(@RequestBody LoginRequest request) {
        CustomerResponse customer = customerService.login(request);
        return ResponseEntity.ok(customer);
    }
}
