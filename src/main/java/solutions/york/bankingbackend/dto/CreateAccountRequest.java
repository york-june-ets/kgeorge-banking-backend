package solutions.york.bankingbackend.dto;

public class CreateAccountRequest {
    private Long customerId;
    private String accountType;
    private String accountStatus;

    public Long getCustomerId() {
        return customerId;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getAccountStatus() {
        return accountStatus;
    }
}
