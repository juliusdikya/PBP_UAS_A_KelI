package tugas.besar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{
    EditText email, pass;
    Button SignIn, SignUp;
    FirebaseAuth FirebaseAuthentication;
    private FirebaseAuth.AuthStateListener AuthStateListener;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String mag = "Succesful";
                        if (!task.isSuccessful()){
                            mag = "Failed";
                        }
                        Toast.makeText(MainActivity.this, mag, Toast.LENGTH_SHORT).show();
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseAuthentication = FirebaseAuth.getInstance();
        //
        email = findViewById(R.id.inputEmail);
        pass = findViewById(R.id.inputPassword);
        SignUp = findViewById(R.id.btnSignUp);
        SignIn = findViewById(R.id.btnSignIn);

        AuthStateListener = new FirebaseAuth.AuthStateListener() {
            com.google.firebase.auth.FirebaseUser FirebaseUser = FirebaseAuthentication.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(FirebaseUser != null){
                    Toast.makeText(MainActivity.this,"Login Sukses",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent (MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this,"Login Dahulu!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        //Register Button
        SignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        }});

        //Sign In Button
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Email masih kosong",Toast.LENGTH_SHORT).show();
                }else if(pass.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Password masih kosong",Toast.LENGTH_SHORT).show();
                }else if(!validasiEmail(email.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(), "Email invalid", Toast.LENGTH_SHORT).show();
                }else if(pass.getText().toString().length()<6){
                    Toast.makeText(getApplicationContext(), "Password minimal tediri dari 6 digit", Toast.LENGTH_SHORT).show();
                }else{
                    String input1 = email.getText().toString();
                    String input2 = pass.getText().toString();
                    FirebaseAuthentication.signInWithEmailAndPassword(input1,input2).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Password salah!", Toast.LENGTH_SHORT).show();
                            }else{
                                if(task.isSuccessful()){
                                    if(FirebaseAuthentication.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(getApplicationContext(), "Sign In Sukses!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                    } else {
                                        Toast.makeText(MainActivity.this, "Email belum diverifikasi", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Bonjour!")
                .setContentText("Anda Berhasil Login ke akun ini!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this,HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification.notify(0,builder.build());
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

