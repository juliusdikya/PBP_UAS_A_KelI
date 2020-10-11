package tugas.besar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText emailReg, passReg;
    Button SignIn, SignUpReg;
    FirebaseAuth FirebaseAuthentication;
    private FirebaseAuth.AuthStateListener AuthStateListener;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_register);

        emailReg = findViewById(R.id.inputEmailReg);
        passReg = findViewById(R.id.inputPasswordReg);
        SignUpReg = findViewById(R.id.btnSignUpReg);
        FirebaseAuthentication = FirebaseAuth.getInstance();

        SignUpReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailReg.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Email masih kosong",Toast.LENGTH_SHORT).show();
                }else if(passReg.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Password masih kosong",Toast.LENGTH_SHORT).show();
                }else if(!validasiEmail(emailReg.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(), "Email invalid", Toast.LENGTH_SHORT).show();
                }else if(passReg.getText().toString().length()<6){
                    Toast.makeText(getApplicationContext(), "Password minimal tediri dari 6 digit", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    String input1 = emailReg.getText().toString();
                    String input2 = passReg.getText().toString();
                    FirebaseAuthentication.createUserWithEmailAndPassword(input1,input2).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Sign Up Gagal, Harap Ulangi!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Sign Up Sukses!", Toast.LENGTH_SHORT).show();
                                emailReg.setText("");
                                passReg.setText("");
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validasiEmail(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}