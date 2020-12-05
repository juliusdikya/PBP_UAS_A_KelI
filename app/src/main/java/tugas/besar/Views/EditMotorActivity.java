package tugas.besar.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;
import tugas.besar.R;

public class EditMotorActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private EditText etNamaMotor, etHargaSewa;
    private MaterialButton btnCancel, btnUpdate;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_edit_motor);

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

        etNamaMotor = findViewById(R.id.etNamaMotor);
        etHargaSewa = findViewById(R.id.etHargaSewa);

        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNamaMotor.getText().toString().isEmpty()) {
                    etNamaMotor.setError("Isikan dengan benar");
                    etNamaMotor.requestFocus();
                }
                else if(etHargaSewa.getText().toString().isEmpty()) {
                    etHargaSewa.setError("Isikan dengan benar");
                    etHargaSewa.requestFocus();
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
        Call<MotorResponse> call = apiService.getMotorById(id, "data");

        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                String NamaMotor = response.body().getMotors().get(0).getNama_motor();
                String HargaSewa = response.body().getMotors().get(0).getHarga_sewa();

                etNamaMotor.setText(NamaMotor);
                etHargaSewa.setText(HargaSewa);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(EditMotorActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                Log.i("Error", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
    public void update() {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<MotorResponse> req = apiService.updateMotor(id, etNamaMotor.getText().toString(),
                etHargaSewa.getText().toString(),"","");

        req.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                Toast.makeText(EditMotorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(EditMotorActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                Log.i("EDIT", t.getMessage());
                progressDialog.dismiss();
            }
        });

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}