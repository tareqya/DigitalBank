package Database.tables;

import org.json.JSONObject;

public class Employee extends Person {

    private int employeeNumber;

    public Employee(String ssn, String fullName, String username, String password, String email, int employeeNumber){
        super(ssn, fullName, username, password, email);
        this.employeeNumber = employeeNumber;
    }

    public Employee(){

    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }


    public JSONObject asJson(){

        try {
            JSONObject jsonObject = super.asJson();
            if(jsonObject == null)
                return null;
            jsonObject.put("employeeNumber", this.employeeNumber);

            return jsonObject;
        }catch (Exception e){
            System.err.println(e.toString());
            return null;
        }

    }

    public static Employee jsonToEmployee(JSONObject jsonObject, String ssn){
        Employee employee = new Employee();
        try {
            JSONObject employeeJson = jsonObject.getJSONObject(ssn);
            employee.ssn = employeeJson.getString("ssn");
            employee.employeeNumber = employeeJson.getInt("employeeNumber");
            employee.email = employeeJson.getString("email");
            employee.password = employeeJson.getString("password");
            employee.fullName = employeeJson.getString("fullName");
            employee.username = employeeJson.getString("username");
        }catch (Exception e){
            return null;
        }
        return employee;
    }
}
