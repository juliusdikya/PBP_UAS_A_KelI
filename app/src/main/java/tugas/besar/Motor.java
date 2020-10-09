package tugas.besar;

public class Motor{
    public String namaMotor;

    public String jenisMotor;
    public double hargaSewa;
    public Motor(String namaMotor, String jenisMotor, double hargaSewa) {
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

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

}

