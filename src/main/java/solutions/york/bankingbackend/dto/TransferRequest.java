package solutions.york.bankingbackend.dto;

public class TransferRequest {
    Long accountNumber;
    String transactionType;
    double amount;
    Long recipientAccountNumber;

    public Long getAccountNumber() {
        return accountNumber;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public Long getRecipientAccountNumber() {
        return recipientAccountNumber;
    }
}
