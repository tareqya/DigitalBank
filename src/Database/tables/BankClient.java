package Database.tables;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BankClient extends Person {

    private ArrayList<Account> accounts;
    private ArrayList<Request> requests;

    public BankClient(String ssn, String fullName, String username, String password, String email){
        super(ssn, fullName, username, password, email);
        this.accounts = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public BankClient(){
        this.accounts = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public static BankClient jsonToClient(String ssn, JSONObject jsonClients, JSONObject jsonAccounts, JSONObject jsonRequests, JSONObject jsonTransactions) {
        BankClient bankClient = new BankClient();

        try {
            JSONObject bankClientJson = jsonClients.getJSONObject(ssn);
            bankClient.ssn = bankClientJson.getString("ssn");
            bankClient.email = bankClientJson.getString("email");
            bankClient.password = bankClientJson.getString("password");
            bankClient.fullName = bankClientJson.getString("fullName");
            bankClient.username = bankClientJson.getString("username");

            JSONArray accounts = bankClientJson.getJSONArray("Accounts");
            for(int i = 0 ; i < accounts.length(); i++){
                bankClient.addAccount(Account.jsonToAccount(accounts.getInt(i), jsonAccounts, jsonTransactions));
            }

            JSONArray requests = bankClientJson.getJSONArray("Requests");
            for(int i = 0 ; i < requests.length(); i++){
                bankClient.addRequest(Request.jsonToRequest(requests.getInt(i), jsonRequests));
            }

        }catch (Exception e){
            return null;
        }
        return bankClient;
    }

    public boolean addAccount(Account account){
        if(this.isAccountExist(account))
            return false;
        this.accounts.add(account);
        return true;
    }

    public boolean removeAccount(Account account){
        if(this.isAccountExist(account)){
            this.accounts.remove(account);
            return true;
        }
        return false;
    }

    public boolean isAccountExist(Account account){
        return this.accounts.contains(account);
    }

    public boolean addRequest(Request request){
        if(this.isRequestExist(request))
            return false;
        this.requests.add(request);
        return true;
    }

    public boolean removeRequest(Request request){
        if(this.isRequestExist(request)){
            this.requests.remove(request);
            return true;
        }
        return false;
    }

    public boolean requestExist(int request_id){
        for(Request request: this.requests){
            if(request.getRequestID() == request_id)
                return true;
        }
        return false;
    }

    public JSONObject asJson(){
        try{
            JSONObject jsonObject = super.asJson();
            if(jsonObject == null)
                return null;

            JSONArray accounts_id = new JSONArray();
            for(Account account : this.accounts){
                accounts_id.put(account.getAccountNumber());
            }
            jsonObject.put("Accounts", accounts_id);

            JSONArray requests_id = new JSONArray();
            for(Request request: this.requests){
                requests_id.put(request.getRequestID());
            }
            jsonObject.put("Requests", requests_id);

            return jsonObject;

        }catch (Exception e){
            return null;
        }
    }

    public boolean isRequestExist(Request request){
        return this.requests.contains(request);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass())
            return false;
        BankClient other = (BankClient) obj;
        return this.ssn.equals(other.ssn);
    }
}
