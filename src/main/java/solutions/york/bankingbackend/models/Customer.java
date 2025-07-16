package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;

    public Customer() {}
    public Customer(String firstName, String lastName, String email, String phoneNumber) {
        if (firstName == null || firstName.isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (lastName == null || lastName.isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (email == null || email.isBlank()) {throw new IllegalArgumentException("Email is required");}
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public void updateCustomerData(String firstName, String lastName, String email, String phoneNumber) {
        if (firstName == null || firstName.isBlank()) {throw new IllegalArgumentException("First name is required");}
        if (lastName == null || lastName.isBlank()) {throw new IllegalArgumentException("Last name is required");}
        if (email == null || email.isBlank()) {throw new IllegalArgumentException("Email is required");}
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
