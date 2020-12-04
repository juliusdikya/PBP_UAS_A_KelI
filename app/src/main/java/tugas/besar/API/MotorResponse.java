package tugas.besar.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tugas.besar.Models.MotorDAO;

public class MotorResponse {
    @SerializedName("data")
    @Expose
    private List<MotorDAO> motors = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public  List<MotorDAO> getMotors() {return motors;}

    public String getMessage() { return message;}

    public  void  setMotors(List<MotorDAO> users) { this.motors = motors;}

    public void setMessage(String message) { this.message = message;}
}
