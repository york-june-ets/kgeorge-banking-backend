package solutions.york.bankingbackend.models;

import jakarta.persistence.*;

@Entity
public class Transfer extends Transaction {
    @ManyToOne
    @JoinColumn(name = "recipientAccountNumber", referencedColumnName = "accountNumber",  nullable = true)
    private Account recipientAccount;

    public Transfer() {}
    public Transfer(Account account, Account recipientAccount, double amount) {
        super(Type.TRANSFER, account, amount);
        this.recipientAccount = recipientAccount;
    }
    public Account getRecipientAccount() {
        return recipientAccount;
    }
    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }
}
