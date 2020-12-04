package tugas.besar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.view.LayoutInflater;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    TextInputLayout input_nama, input_email, input_password;
    TextInputEditText emailReg, passReg, namaReg;
    Button SignIn, SignUpReg;
    FirebaseAuth FirebaseAuthentication;
    private FirebaseAuth.AuthStateListener AuthStateListener;
    private String CHANNEL_ID = "Channel 1";
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        SignUpReg = findViewById(R.id.btnSignUpReg);
        FirebaseAuthentication = FirebaseAuth.getInstance();

        input_nama = (TextInputLayout) findViewById(R.id.input_nama);
        input_email = (TextInputLayout) findViewById(R.id.input_email);
        input_password = (TextInputLayout) findViewById(R.id.input_password);

        namaReg = (TextInputEditText) findViewById(R.id.namaReg);
        emailReg = (TextInputEditText) findViewById(R.id.emailReg);
        passReg = (TextInputEditText) findViewById(R.id.passwordReg);

        SignUpReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    public void signup () {

        if (emailReg.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Email masih kosong", Toast.LENGTH_SHORT).show();
        } else if (passReg.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Password masih kosong", Toast.LENGTH_SHORT).show();
        } else if (!validasiEmail(emailReg.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Email invalid", Toast.LENGTH_SHORT).show();
        } else if (passReg.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password minimal tediri dari 6 digit", Toast.LENGTH_SHORT).show();
        } else {
            String nama_layout = input_nama.getEditText().getText().toString();
            String email_layout = input_email.getEditText().getText().toString();
            String password_layout = input_password.getEditText().getText().toString();

            String namaRegis = namaReg.getText().toString();
            String emailRegis = emailReg.getText().toString();
            String passwordRegis = passReg.getText().toString();

//                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
//                    String input1 = emailReg.getText().toString();
//                    String input2 = passReg.getText().toString();
            FirebaseAuthentication.createUserWithEmailAndPassword(emailRegis, passwordRegis).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sign Up Gagal, Harap Ulangi!", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseAuthentication.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();

                                    //memasukkan data ke database realtime

                                    FirebaseUser rUser = FirebaseAuthentication.getCurrentUser();
                                    assert rUser != null;
                                    String userId = rUser.getUid();
                                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("userId", userId);
                                    hashMap.put("nama", nama_layout);
                                    hashMap.put("email", email_layout);
                                    hashMap.put("password", password_layout);
//                                          hashMap.put("imageURL","default");

                                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(i);
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private boolean validasiEmail (String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}