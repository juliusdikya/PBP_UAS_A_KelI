package tugas.besar.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MotorDAO implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("nama_motor")
    private String nama_motor;

    @SerializedName("harga_sewa")
    private String harga_sewa;

    @SerializedName("durasi")
    private String durasi;

    @SerializedName("img_motor")
    private String img_motor;

    public MotorDAO(String id, String nama_motor, String harga_sewa, String durasi, String img_motor) {
        this.id=id;
        this.nama_motor=nama_motor;
        this.harga_sewa=harga_sewa;
        this.durasi=durasi;
        this.img_motor=img_motor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_motor() {
        return nama_motor;
    }

    public String getHarga_sewa() {
        return harga_sewa;
    }

    public void setNama_motor(String nama_motor) {
        this.nama_motor = nama_motor;
    }

    public void setHarga_sewa(String harga_sewa) {
        this.harga_sewa = harga_sewa;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getImg_motor() {
        return img_motor;
    }

    public void setImg_motor(String img_motor) {
        this.img_motor = img_motor;
    }

}


