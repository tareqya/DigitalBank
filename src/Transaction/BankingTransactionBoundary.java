package Transaction;

import java.util.Date;

public class BankingTransactionBoundary {
    private String comment;
    private Date timestamp;
    private double amount;
    private AccountBoundary source;
    private AccountBoundary destination;

    public BankingTransactionBoundary(){

    }

    public BankingTransactionBoundary(String comment, Date timestamp, double amount, AccountBoundary source, AccountBoundary destination){
        this.amount = amount;
        this.timestamp = timestamp;
        this.comment = comment;
        this.source = source;
        this.destination = destination;
    }


    public AccountBoundary getDestination() {
        return destination;
    }

    public AccountBoundary getSource() {
        return source;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDestination(AccountBoundary destination) {
        this.destination = destination;
    }

    public void setSource(AccountBoundary source) {
        this.source = source;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
