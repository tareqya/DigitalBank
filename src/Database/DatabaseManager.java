package Database;

import Database.tables.Account;
import Database.tables.BankClient;
import Database.tables.Employee;
import Database.tables.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class DatabaseManager {
    private static final String projectHomeDir = Paths.get(System.getProperty("user.dir")).toString();

    private static final Path AccountTable = Paths.get(projectHomeDir,"src", "Database", "source", "Account.json");
    private static final Path BankClientTable = Paths.get(projectHomeDir, "src", "Database", "source", "BankClient.json");
    private static final Path EmployeeTable = Paths.get(projectHomeDir, "src", "Database", "source", "Employee.json");
    private static final Path TransactionTable = Paths.get(projectHomeDir, "src", "Database", "source", "Transaction.json");
    private static final Path RequestTable = Paths.get(projectHomeDir, "src", "Database", "source", "Request.json");


    public DatabaseManager(){

    }

    public boolean write(String jsonData, Path filename){

        try{
            Writer writer = Files.newBufferedWriter(filename);
            writer.write(jsonData);
            writer.close();
        }catch (Exception e){
            System.err.println(e.toString());
            return false;
        }

        return true;
    }

    public String read(Path filename){
        try{
            Reader reader = Files.newBufferedReader(filename);
            StringBuilder jsonData = new StringBuilder();
            int i ;
            while((i = reader.read()) != -1){
                jsonData.append((char) i);
            }
            reader.close();
            return jsonData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmployeeExist(Employee employee){
        try {
            JSONObject employees_data = new JSONObject(this.read(EmployeeTable));
            employees_data.getJSONObject(employee.getSsn());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean addNewEmployee(Employee employee){
        try {
            JSONObject employees_data = new JSONObject(this.read(EmployeeTable));
            if(this.isEmployeeExist(employee))
                return false;

            employees_data.put(employee.getSsn(), employee.asJson());
            return this.write(employees_data.toString(), EmployeeTable);
        }
        catch (Exception e){
            System.err.println(e.toString());
            return false;
        }

    }

    public Employee getEmployee(String ssn){
        try{
            JSONObject employees_data = new JSONObject(this.read(EmployeeTable));
            return Employee.jsonToEmployee(employees_data, ssn);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateEmployee(Employee employee){

        Employee old_employee  = this.getEmployee(employee.getSsn());
        if(old_employee == null)
            return false;

        try {
            JSONObject employees_data = new JSONObject(this.read(EmployeeTable));
            employees_data.put(old_employee.getSsn(), employee.asJson());
            return this.write(employees_data.toString(), EmployeeTable);
        }catch (Exception e){
            return false;
        }
    }

    public boolean isClientExist(BankClient bankClient){
        try {
            JSONObject employees_data = new JSONObject(this.read(BankClientTable));
            employees_data.getJSONObject(bankClient.getSsn());
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean addNewClient(BankClient bankClient){
        try{
            JSONObject clients_data = new JSONObject(this.read(BankClientTable));
            if(this.isClientExist(bankClient))
                return false;

            clients_data.put(bankClient.getSsn(), bankClient.asJson());
            return this.write(clients_data.toString(), BankClientTable);
        }catch (Exception e){
            System.err.println(e.toString());
            return false;
        }
    }

    public BankClient getBankClient(String ssn){
        try{
            JSONObject bankClients_data = new JSONObject(this.read(BankClientTable));
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            JSONObject transactions_data = new JSONObject(this.read(TransactionTable));
            return BankClient.jsonToClient(ssn, bankClients_data, accounts_data, requests_data, transactions_data);
        }catch (Exception e){
            return null;
        }
    }

    public BankClient getBankClientByEmail(String email){
        try{
            JSONObject bankClients_data = new JSONObject(this.read(BankClientTable));
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            JSONObject transactions_data = new JSONObject(this.read(TransactionTable));

            JSONArray keys = bankClients_data.names();
            for(int i = 0 ; i < keys.length(); i++){
                BankClient bankClient = BankClient.jsonToClient(keys.getString(i), bankClients_data, accounts_data, requests_data, transactions_data);
                if(bankClient != null && bankClient.getEmail().equals(email))
                    return bankClient;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    public boolean updateBankClient(BankClient bankClient){
        BankClient old_client  = this.getBankClient(bankClient.getSsn());
        if(old_client == null)
            return false;

        try {
            JSONObject clients_data = new JSONObject(this.read(BankClientTable));
            clients_data.put(old_client.getSsn(), bankClient.asJson());
            return this.write(clients_data.toString(), BankClientTable);
        }catch (Exception e){
            return false;
        }
    }

    public boolean addAccountToClient(String ssn, Account account){
        try{
            JSONObject bankClients_data = new JSONObject(this.read(BankClientTable));
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            JSONObject transactions_data = new JSONObject(this.read(TransactionTable));

            BankClient bankClient = BankClient.jsonToClient(ssn, bankClients_data, accounts_data, requests_data, transactions_data);
            if(bankClient == null)
                return false;
            if(!bankClient.addAccount(account))
                return false;
            if(!this.addNewAccount(account))
                return false;

            return this.updateBankClient(bankClient);
        }catch (Exception e){
            return false;
        }
    }

    public boolean isAccountExist(Account account){
        try {
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            accounts_data.getJSONObject(account.getAccountNumber()+"");
            return true;
        }catch (Exception e){
            return false;
        }

    }

    private boolean addNewAccount(Account account) {
        try{
            if(this.isAccountExist(account))
                return false;
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));

            accounts_data.put(account.getAccountNumber()+"", account.asJson());
            return this.write(accounts_data.toString(), AccountTable);
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateAccount(Account account){
        Account old_account  = this.getAccount(account.getAccountNumber());
        if(old_account == null)
            return false;

        try {
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            accounts_data.put(old_account.getAccountNumber()+"", account.asJson());
            return this.write(accounts_data.toString(), AccountTable);
        }catch (Exception e){
            return false;
        }
    }

    public Account getAccount(int accountNumber) {
        try{
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            JSONObject transactions_data = new JSONObject(this.read(TransactionTable));

            return Account.jsonToAccount(accountNumber, accounts_data, transactions_data);
        }catch (Exception e){
            return null;
        }
    }

    public boolean isRequestExist(Request request){
        try {
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            requests_data.getJSONObject(request.getRequestID()+"");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean addNewRequest(Request request){
        try{
            if(this.isRequestExist(request))
                return false;
            JSONObject requests_data = new JSONObject(this.read(RequestTable));

            requests_data.put(request.getRequestID()+"", request.asJson());
            return this.write(requests_data.toString(), RequestTable);
        }catch (Exception e){
            return false;
        }
    }

    public boolean addRequestToClient(String ssn, Request request){
        try{
            JSONObject bankClients_data = new JSONObject(this.read(BankClientTable));
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONObject accounts_data = new JSONObject(this.read(AccountTable));
            JSONObject transactions_data = new JSONObject(this.read(TransactionTable));
            BankClient bankClient = BankClient.jsonToClient(ssn, bankClients_data, accounts_data, requests_data, transactions_data);
            if(bankClient == null)
                return false;
            if(!bankClient.addRequest(request))
                return false;
            if(!this.addNewRequest(request))
                return false;

            return this.updateBankClient(bankClient);
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Request> getAllClientsOpenRequests(){
        ArrayList<Request> requests = new ArrayList<>();
        try{
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONArray keys =requests_data.names();

            for(int i = 0; i < keys.length(); i++){
                int request_id = Integer.parseInt(keys.getString(i));
                Request request = Request.jsonToRequest(request_id, requests_data);
                if (request != null && !request.isStatus())
                    requests.add(request);
            }

        }catch (Exception e){
            return requests;
        }

        return requests;
    }

    public ArrayList<Request> getAllClientsRequests(){
        ArrayList<Request> requests = new ArrayList<>();
        try{
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            JSONArray keys =requests_data.names();

            for(int i = 0; i < keys.length(); i++){
                int request_id = Integer.parseInt(keys.getString(i));
                Request request = Request.jsonToRequest(request_id, requests_data);
                if (request != null)
                    requests.add(request);
            }

        }catch (Exception e){
            return requests;
        }

        return requests;
    }

    public BankClient getClientRequest(int request_id){
        try{
            JSONObject clients_data = new JSONObject(this.read(BankClientTable));
            JSONArray clients_ssn = clients_data.names();
            for(int i = 0 ; i < clients_ssn.length(); i++){
                BankClient bankClient = this.getBankClient(clients_ssn.getString(i));
                if(bankClient.requestExist(request_id))
                    return bankClient;
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Request getRequest(int request_id) {
        try{
            JSONObject request_data = new JSONObject(this.read(RequestTable));

            return Request.jsonToRequest(request_id, request_data);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateRequestStatus(int request_id){
        Request request  = this.getRequest(request_id);
        if(request == null)
            return false;
        request.setStatus(true);
        try {
            JSONObject requests_data = new JSONObject(this.read(RequestTable));
            requests_data.put(request.getRequestID()+"", request.asJson());
            return this.write(requests_data.toString(), RequestTable);
        }catch (Exception e){
            return false;
        }
    }

}
