package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @Column(nullable = false)
    private String accountType;
    @Column(nullable = false, unique = true)
    private String accountNumber;
    @Column(nullable = false)
    private String accountStatus;
    @Column(nullable = false)
    private double balance;

    public Account() {}
    public Account(Customer customer, String accountType, String accountNumber, String accountStatus) {
        if (customer == null || customer.getId() ==  null) {throw new IllegalArgumentException("Customer is required");}
        if (!(accountType.equals("CHECKING") || accountType.equals("SAVINGS"))) {throw new IllegalArgumentException("Account type is required: CHECKING or SAVINGS");}
        if (accountNumber == null || accountNumber.isBlank()) {throw new IllegalArgumentException("Account number is required");}
        if (!(accountStatus.equals("ACTIVE") || accountStatus.equals("CLOSED") || accountStatus.equals("SUSPENDED"))) {throw new IllegalArgumentException("Account status is required: ACTIVE, CLOSED, or SUSPENDED");}

        this.customer = customer;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
        this.balance = 0.00;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountStatus() {
        return accountStatus;
    }
}
