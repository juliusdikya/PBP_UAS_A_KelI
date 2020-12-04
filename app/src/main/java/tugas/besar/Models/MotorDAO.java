package tugas.besar.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MotorDAO implements Serializable {

    @SerializedName("nama_motor")
    private String nama_motor;

    @SerializedName("harga_sewa")
    private int harga_sewa;

    @SerializedName("durasi")
    private int durasi;

    @SerializedName("img_motor")
    private String img_motor;

    public MotorDAO(String nama_motor, int harga_sewa, int durasi, String img_motor) {
        this.nama_motor=nama_motor;
        this.harga_sewa=harga_sewa;
        this.durasi=durasi;
        this.img_motor=img_motor;
    }

    public String getNama_motor() {
        return nama_motor;
    }

    public int getHarga_sewa() {
        return harga_sewa;
    }

    public void setNama_motor(String nama_motor) {
        this.nama_motor = nama_motor;
    }

    public void setHarga_sewa(int harga_sewa) {
        this.harga_sewa = harga_sewa;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getImg_motor() {
        return img_motor;
    }

    public void setImg_motor(String img_motor) {
        this.img_motor = img_motor;
    }

}


