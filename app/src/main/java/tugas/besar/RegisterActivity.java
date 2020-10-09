package tugas.besar;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //Deklarasi Variable
    private TextInputEditText myEmail, myPassword;
    private Button regButtton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private String getEmail, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inisialisasi Widget dan Membuat Objek dari Firebae Authenticaion
//        myEmail = findViewById(R.id.regEmail);
////        myPassword = findViewById(R.id.regPassword);
////        regButtton = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();

        //Menyembunyikan / Hide Password
        myPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        regButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekDataUser();
            }
        });
    }

    //Method ini digunakan untuk mengecek dan mendapatkan data yang dimasukan user
    private void cekDataUser(){
        //Mendapatkan dat yang diinputkan User
        getEmail = myEmail.getText().toString();
        getPassword = myPassword.getText().toString();

        //Mengecek apakah email dan sandi kosong atau tidak
        if(TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)){
            Toast.makeText(this, "Email atau Sandi Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
            //Mengecek panjang karakter password baru yang akan didaftarkan
            if(getPassword.length() < 6){
                Toast.makeText(this, "Sandi Terlalu Pendek, Minimal 6 Karakter", Toast.LENGTH_SHORT).show();
            }else {
                progressBar.setVisibility(View.VISIBLE);
                createUserAccount();
            }
        }
    }

    //Method ini digunakan untuk membuat akun baru user
    private void createUserAccount(){
        auth.createUserWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Mengecek status keberhasilan saat medaftarkan email dan sandi baru
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}