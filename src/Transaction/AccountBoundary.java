package Transaction;

public class AccountBoundary {
    private String accountNumber;
    private String bankId;
    private String branch;
    private String customerName;

    public AccountBoundary() {
    }

    public AccountBoundary(String accountNumber, String bankId, String branch, String customerName) {
        super();
        this.accountNumber = accountNumber;
        this.bankId = bankId;
        this.branch = branch;
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
