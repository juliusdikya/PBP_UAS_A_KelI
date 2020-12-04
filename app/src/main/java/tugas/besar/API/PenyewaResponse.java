package tugas.besar.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tugas.besar.Models.PenyewaDAO;

public class PenyewaResponse {
    @SerializedName("data")
    @Expose
    private List<PenyewaDAO> penyewas = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public List<PenyewaDAO> getPenyewas(){ return penyewas;}

    public String getMessage() { return message;}

    public  void  setPenyewas(List<PenyewaDAO> users) { this.penyewas = penyewas;}

    public void setMessage(String message) { this.message = message;}
}
