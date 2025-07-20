package solutions.york.bankingbackend.dto;

public class TransactionRequest {
    double amount;
    Long accountNumber;
    String transactionType;

    public double getAmount() {
        return amount;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public String getTransactionType() {
        return transactionType;
    }
}
