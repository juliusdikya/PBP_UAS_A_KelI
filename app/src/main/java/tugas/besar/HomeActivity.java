package tugas.besar;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btnSewa, btnList, btnUser, SignOut, btnLokasi, btnAbout, btnListMotor;
    FirebaseAuth FirebaseAuth;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_home);

        btnSewa = findViewById(R.id.btnSewa);
        btnList = findViewById(R.id.btnList);
        btnUser = findViewById(R.id.btnProfile);
        btnLokasi = findViewById(R.id.btnLokasi);
        btnAbout = findViewById(R.id.btnAbout);
        SignOut = findViewById(R.id.btnSignOut);
        btnListMotor = findViewById(R.id.btnListMotor);

        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, SewaActivity.class);
                startActivity(a);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(HomeActivity.this, PenyewaActivity.class);
                startActivity(p);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(a);
            }
        });

        btnListMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, ListMotorActivity.class);
                startActivity(a);
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(HomeActivity.this, GeolocationActivity.class);
                startActivity(z);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(m);
            }
        });



        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(HomeActivity.this,"Sign Out Sukses",Toast.LENGTH_SHORT).show();
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
                .setContentTitle("Hasta la Vista!")
                .setContentText("Anda Berhasil Sign Out dari akun ini!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}


