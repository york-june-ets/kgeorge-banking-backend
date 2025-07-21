package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
public class Account {
    public enum Status {ACTIVE, SUSPENDED, CLOSED}
    public enum Type {CHECKING, SAVINGS}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_seq", initialValue = 1000000000, allocationSize = 1)
    private Long accountNumber;

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status accountStatus;

    @Column(nullable = false)
    private double balance;

    public Account() {}
    public Account(Customer customer, Type accountType, Status accountStatus) {
        if (customer == null || customer.getId() ==  null) {throw new IllegalArgumentException("Customer is required");}
        if (accountType == null) {throw new IllegalArgumentException("Account type is required: CHECKING or SAVINGS");}
        if (accountStatus == null) {throw new IllegalArgumentException("Account status is required: ACTIVE, CLOSED, or SUSPENDED");}
        if (customer.isArchived()) {throw new IllegalArgumentException("Customer is archived");}

        this.customer = customer;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.balance = 0.00;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Type getAccountType() {
        return accountType;
    }
    public void setAccountType(Type accountType) {
        this.accountType = accountType;
    }
    public Status getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(Status accountStatus) {
        this.accountStatus = accountStatus;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
