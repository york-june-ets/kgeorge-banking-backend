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

    @ManyToOne
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber",  nullable = false)
    private Account account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Type transactionType;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Transaction() {}
    public Transaction(Type transactionType, Account account, double amount) {
        if (account == null) {throw new IllegalArgumentException("Account is required");}
        if (transactionType == null) {throw new IllegalArgumentException("Transaction type is required");}
        if (amount <= 0) {throw new IllegalArgumentException("Amount must be greater than or equal to zero");}

        // Ensuring all transactions of type transfer are made through Transfer class with recipientAccountNumber prop
        if (transactionType == Type.TRANSFER && this.getClass() == Transaction.class) {throw new IllegalArgumentException("Transactions of type TRANSFER must be created with Transfer class");}
        // ensuring transactions are only made for active accounts
        if (account.getAccountStatus() != Account.Status.ACTIVE) {throw new IllegalArgumentException("Transactions can only be made for active accounts");}

        this.account = account;
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
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
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
