package Database.tables;

import org.json.JSONObject;

public class Person {

    protected String ssn;
    protected String fullName;
    protected String username;
    protected String password;
    protected String email;

    public Person(String ssn, String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Person(){

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public JSONObject asJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ssn", this.ssn);
            jsonObject.put("fullName", this.fullName);
            jsonObject.put("username", this.username);
            jsonObject.put("password", this.password);
            jsonObject.put("email", this.email);

            return jsonObject;
        }catch (Exception e){
            System.err.println(e.toString());
            return null;
        }
    }
}
