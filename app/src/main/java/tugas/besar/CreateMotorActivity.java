package tugas.besar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;

public class CreateMotorActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private EditText etNamaMotor, etHargaSewa, etDurasi, etImgMotor;
    private MaterialButton btnCancel, btnCreate;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_motor);

        progressDialog = new ProgressDialog(this);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        etNamaMotor = findViewById(R.id.etNamaMotor);
        etHargaSewa = findViewById(R.id.etHargaSewa);
        etDurasi = findViewById(R.id.etDurasi);
        etImgMotor = findViewById(R.id.etImgMotor);

        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnCreate);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNamaMotor.getText().toString().isEmpty())
                {
                    etNamaMotor.setError("Isikan Dengan benar");
                    etNamaMotor.requestFocus();
                }
                else if (etHargaSewa.getText().toString().isEmpty())
                {
                    etHargaSewa.setError("Isikan Dengan benar");
                    etHargaSewa.requestFocus();
                }
                else
                {
                    progressDialog.show();
                    saveMotor();
                }
            }
        });
    }

    private void saveMotor(){
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<MotorResponse> add = apiService.createMotor(etNamaMotor.getText().toString(),
                etHargaSewa.getText().toString(),
                etDurasi.getText().toString(),
                etImgMotor.getText().toString());

        add.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                Toast.makeText(CreateMotorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(CreateMotorActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
