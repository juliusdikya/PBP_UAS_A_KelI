package tugas.besar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.PenyewaResponse;


public class CreatePenyewaAdmin extends AppCompatActivity {

    private ImageButton ibBack;
    private EditText etNamaMotor, etPembayaran, etDurasi, etNamaPenyewa;
    private MaterialButton btnCancel, btnCreate;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_create_penyewa);

        progressDialog = new ProgressDialog(this);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        etNamaMotor = findViewById(R.id.etNamaMotor);
        etPembayaran = findViewById(R.id.etPembayaran);
        etDurasi = findViewById(R.id.etDurasi);
        etNamaPenyewa = findViewById(R.id.etNamaPenyewa);

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
                else if (etNamaPenyewa.getText().toString().isEmpty())
                {
                    etNamaPenyewa.setError("Isikan Dengan benar");
                    etNamaPenyewa.requestFocus();
                }
                else if (etDurasi.getText().toString().isEmpty())
                {
                    etDurasi.setError("Isikan Dengan benar");
                    etDurasi.requestFocus();
                }
                else if (etPembayaran.getText().toString().isEmpty())
                {
                    etPembayaran.setError("Isikan Dengan benar");
                    etPembayaran.requestFocus();
                }
                else
                {
                    progressDialog.show();
                    savePenyewa();
                }
            }
        });
    }


    private void savePenyewa(){
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<PenyewaResponse> add = apiService.createPenyewa(etNamaPenyewa.getText().toString(),
                etNamaMotor.getText().toString(),
                etDurasi.getText().toString(),
                etPembayaran.getText().toString());

        add.enqueue(new Callback<PenyewaResponse>() {
            @Override
            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response) {
                Toast.makeText(CreatePenyewaAdmin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                Toast.makeText(CreatePenyewaAdmin.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
