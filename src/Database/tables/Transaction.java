package Database.tables;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private int transactionID;
    private String type;
    private float amount;
    private Date create_date;
    private Account destinationAccount;

    public Transaction(int transactionID, String type, float amount, Account destinationAccount){
        this.amount = amount;
        this.create_date = Calendar.getInstance().getTime();
        this.type = type;
        this.transactionID = transactionID;
        this.destinationAccount = destinationAccount;
    }

    public Transaction(int transactionID, String type, float amount, Account destinationAccount, Date create_date){
        this.amount = amount;
        this.create_date = create_date;
        this.type = type;
        this.transactionID = transactionID;
        this.destinationAccount = destinationAccount;
    }

    public Transaction(){
    }

    public static Transaction jsonToTransaction(int transactionID, JSONObject jsonTransactions){
        Transaction transaction = new Transaction();
        try{
            JSONObject accountJson = jsonTransactions.getJSONObject(transactionID+"");
            transaction.transactionID = accountJson.getInt("transactionID");
            transaction.create_date = new Date(accountJson.getLong("create_date"));
            transaction.amount = (float) accountJson.getLong("amount");
            JSONObject desAccount = (JSONObject) jsonTransactions.get("destinationAccount");
            transaction.destinationAccount = new Account();
            transaction.destinationAccount.setBankID(desAccount.getString("bankID"));
            transaction.destinationAccount.setAccountNumber(desAccount.getInt("accountNumber"));
            transaction.destinationAccount.setAccountName(desAccount.getString("accountName"));
            transaction.destinationAccount.setAccountType(desAccount.getString("accountType"));

        }catch (Exception e){
            return null;
        }

        return transaction;
    }
    public Date getCreate_date() {
        return create_date;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public float getAmount() {
        return amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj.getClass() != this.getClass())
            return false;

        Transaction other = (Transaction) obj;
        return other.transactionID == this.transactionID;
    }

    public JSONObject asJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transactionID", this.transactionID);
            jsonObject.put("type", this.type);
            jsonObject.put("amount", this.amount);
            jsonObject.put("create_date", this.create_date.getTime());
            jsonObject.put("destinationAccount", this.destinationAccount.asJson());

            return jsonObject;
        }catch (Exception e){
            System.err.println(e.toString());
            return null;
        }
    }
}
