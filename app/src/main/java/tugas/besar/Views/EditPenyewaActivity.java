package tugas.besar.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;
import tugas.besar.API.PenyewaResponse;
import tugas.besar.R;

public class EditPenyewaActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private EditText etNamaPenyewa, etNamaMotor, etDurasi, etPembayaran;
    private MaterialButton btnCancel, btnUpdatePenyewa;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_penyewa);

        id = getIntent().getStringExtra("id");
        Log.i("EDITUSERID", "ID User: " + id);

        progressDialog = new ProgressDialog(this);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        etNamaPenyewa = findViewById(R.id.etNamaPenyewa);
        etNamaMotor = findViewById(R.id.etNamaMotor);
        etDurasi = findViewById(R.id.etDurasi);
        etPembayaran = findViewById(R.id.etPembayaran);

        btnCancel = findViewById(R.id.btnCancel);
        btnUpdatePenyewa = findViewById(R.id.btnUpdatePenyewa);



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnUpdatePenyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNamaPenyewa.getText().toString().isEmpty()) {
                    etNamaPenyewa.setError("Isikan dengan benar");
                    etNamaPenyewa.requestFocus();
                }
                else if(etNamaMotor.getText().toString().isEmpty()) {
                    etNamaMotor.setError("Isikan dengan benar");
                    etNamaMotor.requestFocus();
                }
                else if(etDurasi.getText().toString().isEmpty()) {
                    etDurasi.setError("Isikan dengan benar");
                    etDurasi.requestFocus();
                }
                else if(etPembayaran.getText().toString().isEmpty()) {
                    etPembayaran.setError("Isikan dengan benar");
                    etPembayaran.requestFocus();
                }
                else {
                    progressDialog.show();
                    update();
                }
            }
        });

        loadUser(id);
    }

    public void loadUser(String id) {

        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<PenyewaResponse> call = apiService.getPenyewaById(id, "data");

        call.enqueue(new Callback<PenyewaResponse>() {
            @Override
            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response) {
                String NamaPenyewa = response.body().getPenyewas().get(0).getNama_penyewa();
                String NamaMotor = response.body().getPenyewas().get(0).getNama_motor();
                String Pembayaran = response.body().getPenyewas().get(0).getHarga();
                String Durasi = response.body().getPenyewas().get(0).getDurasi_sewa();

                etNamaPenyewa.setText(NamaPenyewa);
                etNamaMotor.setText(NamaMotor);
                etDurasi.setText(Durasi);
                etPembayaran.setText(Pembayaran);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                Toast.makeText(EditPenyewaActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                Log.i("Error", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
    public void update() {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<PenyewaResponse> req = apiService.updatePenyewa(id, etNamaPenyewa.getText().toString(),
                etNamaMotor.getText().toString(),
                etDurasi.getText().toString(),
                etPembayaran.getText().toString());

        req.enqueue(new Callback<PenyewaResponse>() {
            @Override
            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response) {
                Toast.makeText(EditPenyewaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                Toast.makeText(EditPenyewaActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                Log.i("EDIT", t.getMessage());
                progressDialog.dismiss();
            }
        });

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}