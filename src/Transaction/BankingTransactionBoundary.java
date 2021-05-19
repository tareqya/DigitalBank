package Transaction;

import java.util.Date;

public class BankingTransactionBoundary {
    private AccountBoundary source;
    private AccountBoundary destination;
    private String comment;
    private Date timestamp;
    private double amount;

    public BankingTransactionBoundary() {
    }

    public BankingTransactionBoundary(AccountBoundary source, AccountBoundary destination, String comment,
                                      Date timestamp, double amount) {
        super();
        this.source = source;
        this.destination = destination;
        this.comment = comment;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public AccountBoundary getSource() {
        return source;
    }

    public void setSource(AccountBoundary source) {
        this.source = source;
    }

    public AccountBoundary getDestination() {
        return destination;
    }

    public void setDestination(AccountBoundary destination) {
        this.destination = destination;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
