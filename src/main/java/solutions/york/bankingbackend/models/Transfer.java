package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
public class Transfer extends Transaction {
    private Long recipientAccountNumber;

    public Transfer() {}
    public Transfer(Account account, Long recipientAccountNumber, double amount) {
        super(Type.TRANSFER, account, amount);
        this.recipientAccountNumber = recipientAccountNumber;
    }
    public Long getRecipientAccountNumber() {
        return recipientAccountNumber;
    }
    public void setRecipientAccountNumber(Long recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }
}
