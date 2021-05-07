package Database.tables;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Account {

    private String bankID;
    private int accountNumber;
    private String accountName;
    private String accountType;
    private float balance;
    private ArrayList<Transaction> transactions;

    public Account(){
        this.transactions = new ArrayList<>();
    }

    public Account(String bankID, int accountNumber, String accountName, String accountType, float balance){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankID = bankID;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public static Account jsonToAccount(int accountNumber, JSONObject jsonAccounts, JSONObject jsonTransactions) {
        Account account = new Account();

        try {
            JSONObject accountJson = jsonAccounts.getJSONObject(accountNumber+"");
            account.bankID = accountJson.getString("bankID");
            account.accountNumber = accountJson.getInt("accountNumber");
            account.accountName = accountJson.getString("accountName");
            account.accountType = accountJson.getString("accountType");
            account.balance = (float)accountJson.getLong("balance");

            JSONArray transactions_id = jsonAccounts.getJSONArray("Transactions");
            for(int i = 0 ; i < transactions_id.length(); i++){
                account.addTransaction(Transaction.jsonToTransaction(transactions_id.getInt(i), jsonTransactions));
            }
        }catch (Exception e){
            return null;
        }
        return account;
    }

    public float getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getBankID() {
        return bankID;
    }

    public String getAccountType() {
        return accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public boolean isTransactionExist(Transaction transaction){
        return this.transactions.contains(transaction);
    }

    public boolean addTransaction(Transaction transaction){
        if(this.isTransactionExist(transaction))
            return false;

        this.transactions.add(transaction);
        return true;
    }

    public boolean removeTransaction(Transaction transaction){
        if(this.isTransactionExist(transaction)){
            this.transactions.remove(transaction);
            return true;
        }
        return false;
    }

    public JSONObject asJson(){
        try{
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("bankID", this.bankID);
            jsonObject.put("accountNumber", this.accountNumber);
            jsonObject.put("accountName", this.accountName);
            jsonObject.put("accountType", this.accountType);
            jsonObject.put("balance", this.balance);

            JSONArray transactions_id = new JSONArray();
            for(Transaction transaction: this.transactions){
                transactions_id.put(transaction.getTransactionID());
            }

            jsonObject.put("Transactions", transactions_id);

            return jsonObject;

        }catch (Exception e){
            return null;
        }
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass())
            return false;

        Account other = (Account) obj;
        return other.accountNumber == this.accountNumber && other.bankID.equals(this.bankID);
    }
}
