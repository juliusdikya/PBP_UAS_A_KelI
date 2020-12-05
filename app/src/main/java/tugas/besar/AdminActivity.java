package tugas.besar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import tugas.besar.Views.ShowListMotorActivity;
import tugas.besar.Views.ShowListPenyewaActivity;

public class AdminActivity extends AppCompatActivity {

    private CardView cvCreateMotor, cvShowListMotor,cvCreatePenyewa,cvShowListPenyewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        cvCreateMotor = findViewById(R.id.cvCreateMotor);
        cvShowListMotor = findViewById(R.id.cvShowListMotor);
        cvCreatePenyewa = findViewById(R.id.cvCreatePenyewa);
        cvShowListPenyewa = findViewById(R.id.cvShowListPenyewa);

        cvCreateMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, CreateMotorActivity.class);
                startActivity(i);
            }
        });

        cvShowListMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, ShowListMotorActivity.class);
                startActivity(i);
            }
        });

        cvCreatePenyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, CreatePenyewaActivity.class);
                startActivity(i);
            }
        });

        cvShowListPenyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, ShowListPenyewaActivity.class);
                startActivity(i);
            }
        });
    }
}
