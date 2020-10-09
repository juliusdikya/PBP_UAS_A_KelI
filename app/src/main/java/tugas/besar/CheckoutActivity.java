package tugas.besar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import tugas.besar.databinding.ActivityCheckoutBinding;

public class CheckoutActivity extends AppCompatActivity {

    ArrayList<Motor> ListMotor;
    ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);

        ListMotor = new  DaftarMotor().MOTOR;
        binding.setMtr(ListMotor.get(0));
    }
}
