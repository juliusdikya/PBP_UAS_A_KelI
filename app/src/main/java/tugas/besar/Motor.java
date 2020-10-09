package tugas.besar;

public class Motor{
    public String namaMotor;
    public String jenisMotor;
    public int hargaSewa;

    public Motor(String namaMotor, String jenisMotor, int hargaSewa) {
        this.namaMotor = namaMotor;
        this.jenisMotor = jenisMotor;
        this.hargaSewa = hargaSewa;
    }

    public String getNamaMotor() {
        return namaMotor;
    }

    public void setNamaMotor(String namaMotor) {
        this.namaMotor = namaMotor;
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenisMotor) {
        this.jenisMotor = jenisMotor;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

}

