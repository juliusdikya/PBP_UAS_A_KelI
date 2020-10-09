package tugas.besar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

import org.w3c.dom.Text;

public class SewaActivity extends AppCompatActivity {

    private TextInputEditText inputSewa, inputHari;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        inputSewa = findViewById(R.id.inputSewa);
        inputHari =  findViewById(R.id.inputHari);
        btnProses = findViewById(R.id.btnProses);

        final String cekSewa = inputSewa.getText().toString();

//        int value = Integer.parseInt(inputSewa.getText().toString());

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputSewa.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Kode Motor Kosong!",Toast.LENGTH_SHORT).show();
                }else if(inputHari.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Hari Kosong!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(SewaActivity.this,CheckoutActivity.class);
                    startActivity(i);
                }
            }
        });

    }
}
