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
    public static final Motor motor1 = new Motor("CB 150 R",200000, "https://d2pa5gi5n2e1an.cloudfront.net/webp/global/images/product/motorcycle/Honda_CB150R_ID/Honda_CB150R_ID_L_4.jpg");
    public static final Motor motor2 = new Motor("R15", 200000, "https://d2pa5gi5n2e1an.cloudfront.net/webp/global/images/product/motorcycle/Yamaha_YZF_R15_MY2017/Yamaha_YZF_R15_MY2017_L_1.jpg");
    public static final Motor motor3 = new Motor("Vario", 100000, "https://cf.shopee.co.id/file/9c32d64269b2f95439ea8e6858d3cf6e");
    public static final Motor motor4 = new Motor("Mio",  100000,"https://ecs7.tokopedia.net/img/cache/700/product-1/2020/5/16/101164598/101164598_faa874bd-5a34-4f65-8afe-1cdb96c6fee1_1080_1080");
    public static final Motor motor5 = new Motor("Supra",  50000,"https://s0.bukalapak.com/img/0854044732/large/kampas_ganda_sprocket_honda_supra_x_125kiranakarisma_125_Fed.jpg");
    public static final Motor motor6 = new Motor("Shogun",  50000,"https://asset.kompas.com/crops/SvGvKfCvxUoMbJdYJT77wFkexF8=/0x15:964x657/750x500/data/photo/2020/03/24/5e78fda5be995.jpg");

}