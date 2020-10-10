package tugas.besar;

import java.util.ArrayList;

public class DaftarMotor {
    public ArrayList<Motor> MOTOR;
    public DaftarMotor(){
        MOTOR = new ArrayList();
        MOTOR.add(motor1);
        MOTOR.add(motor2);
        MOTOR.add(motor3);
        MOTOR.add(motor4);
        MOTOR.add(motor5);
        MOTOR.add(motor6);

    }
    public static final Motor motor1 = new Motor("CB 150 R",200000);
    public static final Motor motor2 = new Motor("R15", 200000);
    public static final Motor motor3 = new Motor("Vario", 100000);
    public static final Motor motor4 = new Motor("Mio",  100000);
    public static final Motor motor5 = new Motor("Supra",  50000);
    public static final Motor motor6 = new Motor("Shogun",  50000);

}