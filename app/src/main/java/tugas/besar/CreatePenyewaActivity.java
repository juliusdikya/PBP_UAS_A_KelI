package tugas.besar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;
import tugas.besar.API.PenyewaResponse;
import tugas.besar.Views.EditPenyewaActivity;



public class CreatePenyewaActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private EditText etNamaMotor, etPembayaran, etDurasi, etNamaPenyewa;
    private MaterialButton btnCancel, btnCreate;
    private ProgressDialog progressDialog;
    private String id;

    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_create_penyewa);

        user = FirebaseAuth.getInstance().getCurrentUser();

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

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userHelper = snapshot.getValue(UserHelper.class);
                assert userHelper != null;
                
                etNamaPenyewa.setText(userHelper.getNama());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                Toast.makeText(CreatePenyewaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                Toast.makeText(CreatePenyewaActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
