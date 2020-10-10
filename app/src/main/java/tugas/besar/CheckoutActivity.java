package tugas.besar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import tugas.besar.databinding.ActivityCheckoutBinding;

//import tugas.besar.databinding.ActivityCheckoutBinding;

public class CheckoutActivity extends AppCompatActivity {

    ArrayList<Motor> ListMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_checkout);
        ActivityCheckoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ListMotor = new DaftarMotor().MOTOR;
        binding.setMotor(ListMotor.get(0));
    }
}
