package Database.tables;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Request {
    private int requestID;
    private Date create_date;
    private String subject;
    private String description;
    private boolean status;

    public Request(int requestID, String subject, String description){
        this.status = false;
        this.requestID = requestID;
        this.create_date = Calendar.getInstance().getTime();
        this.subject = subject;
        this.description = description;
    }

    public Request(int requestID, String subject, String description, Date create_date){
        this.status = false;
        this.requestID = requestID;
        this.create_date = create_date;
        this.subject = subject;
        this.description = description;
    }

    public Request(){

    }

    public static Request jsonToRequest(int requestID, JSONObject jsonRequests) {
        Request request = new Request();

        try {
            JSONObject accountJson = jsonRequests.getJSONObject(requestID+"");
            request.requestID = accountJson.getInt("requestID");
            request.create_date = new Date(accountJson.getLong("create_date"));
            request.subject = accountJson.getString("subject");
            request.description = accountJson.getString("description");
            request.status = accountJson.getBoolean("status");

        }catch (Exception e){
            return null;
        }
        return request;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public int getRequestID() {
        return requestID;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public JSONObject asJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("requestID", this.requestID);
            jsonObject.put("create_date", this.create_date.getTime()); // save in milliseconds
            jsonObject.put("subject", this.subject);
            jsonObject.put("description", this.description);
            jsonObject.put("status", this.status);

            return jsonObject;
        }catch (Exception e){
            System.err.println(e.toString());
            return null;
        }
    }

}
