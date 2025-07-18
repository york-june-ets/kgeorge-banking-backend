package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {
    public enum Type {DEPOSIT, WITHDRAWAL, TRANSFER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    protected Type transactionType;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction() {}
    public Transaction(Type transactionType, Long accountNumber, double amount) {
        if (accountNumber == null) {throw new IllegalArgumentException("Account number is required");}
        if (transactionType == null) {throw new IllegalArgumentException("Transaction type is required");}
        if (amount <= 0) {throw new IllegalArgumentException("Amount must be greater than or equal to zero");}
        // Ensuring all transactions of type transfer are made through Transfer class with recipientAccountNumber prop
        if (transactionType == Type.TRANSFER && this.getClass() == Transaction.class) {throw new IllegalArgumentException("Transactions of type TRANSFER must be created with Transfer class");}
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Type getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(Type transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
