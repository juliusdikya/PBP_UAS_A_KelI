package tugas.besar;

public class Motor{
    public String namaMotor;
    public int hargaSewa;

    public Motor(String namaMotor, int hargaSewa) {
        this.namaMotor = namaMotor;
        this.hargaSewa = hargaSewa;
    }

    public String getNamaMotor() {
        return namaMotor;
    }

    public void setNamaMotor(String namaMotor) {
        this.namaMotor = namaMotor;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

}

