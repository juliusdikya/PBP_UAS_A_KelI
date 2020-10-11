package tugas.besar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;

import java.util.List;

import tugas.besar.database.Database;

public class SewaActivity extends AppCompatActivity implements OnItemSelectedListener {

    private TextInputEditText inputSewa, inputHari;
    private Button btnProses, btnKembali;
    private Spinner spinner;

    EditText nama, id, no_hp, lama;
    Button selesai;
    private String CHANNEL_ID = "Channel 1";

    String sNama, sId, sNo, sMerk, sLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_sewa);

//        inputSewa = findViewById(R.id.inputSewa);
//        inputHari =  findViewById(R.id.inputHari);

        btnProses = findViewById(R.id.btnProses);
        btnKembali = findViewById(R.id.btnKembali);
        dbHelper = new Database(this);
        spinner = findViewById(R.id.spinner);
        nama = findViewById(R.id.eTNama);
        id = findViewById(R.id.eTId);
        no_hp = findViewById(R.id.eTHP);
        lama = findViewById(R.id.eTLamaSewa);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNama = nama.getText().toString();
                sId = id.getText().toString();
                sNo = no_hp.getText().toString();
                sLama = lama.getText().toString();
                if (sNama.isEmpty() || sId.isEmpty() || sNo.isEmpty() || sLama.isEmpty()) {
                    Toast.makeText(SewaActivity.this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sMerk.equals("CB 150 R ")) {
                    iHarga = 200000;
                } else if (sMerk.equals("R 15")) {
                    iHarga = 200000;
                } else if (sMerk.equals("Vario")) {
                    iHarga = 100000;
                } else if (sMerk.equals("Mio")) {
                    iHarga = 100000;
                } else if (sMerk.equals("Supra")) {
                    iHarga = 50000;
                } else if (sMerk.equals("Shogun")) {
                    iHarga = 50000;
                }

                iLama = Integer.parseInt(sLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, id, no_hp) VALUES ('" +
                        sNama + "','" +
                        sId + "','" +
                        sNo + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, lama, total) VALUES ('" +
                        sMerk + "','" +
                        sNama + "','" +
                        iLama + "','" +
                        dTotal + "');");
//                PenyewaActivity.m.RefreshList();
                createNotificationChannel();
                addNotification();
                Toast.makeText(SewaActivity.this,"Sewa berhasil!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent o = new Intent(SewaActivity.this, HomeActivity.class);
//                startActivity(o);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSpinnerData() {
        Database db = new Database(getApplicationContext());
        List categories = db.getAllCategories();

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                .setContentTitle("Berhasil!")
                .setContentText("Anda Telah melakukan sewa motor!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this,HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification.notify(0,builder.build());
    }
}
