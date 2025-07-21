package solutions.york.bankingbackend.dto;

import solutions.york.bankingbackend.models.Customer;

public class CustomerResponse {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
    }
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
