package tugas.besar.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PenyewaDAO implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("nama_penyewa")
    private String nama_penyewa;

    @SerializedName("nama_motor")
    private String nama_motor;

    @SerializedName("durasi_sewa")
    private String durasi_sewa;

    @SerializedName("harga")
    private String harga;

    public PenyewaDAO(String id, String nama_penyewa, String nama_motor, String durasi_sewa, String harga) {
        this.id=id;
        this.nama_motor=nama_motor;
        this.nama_penyewa=nama_penyewa;
        this.durasi_sewa=durasi_sewa;
        this.harga=harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_penyewa() {
        return nama_penyewa;
    }

    public void setNama_penyewa(String nama_penyewa) {
        this.nama_penyewa = nama_penyewa;
    }

    public String getNama_motor() {
        return nama_motor;
    }

    public void setNama_motor(String nama_motor) {
        this.nama_motor = nama_motor;
    }

    public String getDurasi_sewa() {
        return durasi_sewa;
    }

    public void setDurasi_sewa(String durasi_sewa) {
        this.durasi_sewa = durasi_sewa;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
