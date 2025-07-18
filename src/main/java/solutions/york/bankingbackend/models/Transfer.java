package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
public class Transfer extends Transaction {
    private Long recipientAccountNumber;

    public Transfer() {}
    public Transfer(Long accountNumber, Long recipientAccountNumber, double amount) {
        super(Type.TRANSFER, accountNumber, amount);
        this.recipientAccountNumber = recipientAccountNumber;
    }
    public Long getRecipientAccountNumber() {
        return recipientAccountNumber;
    }
    public void setRecipientAccountNumber(Long recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }
}
