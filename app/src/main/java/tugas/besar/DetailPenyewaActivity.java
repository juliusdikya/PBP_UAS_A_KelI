package tugas.besar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import tugas.besar.R;
import tugas.besar.database.Database;

public class DetailPenyewaActivity extends AppCompatActivity {

    String sNama, sId, sHP, sMerk, sHarga;
    int iLama, iPromo, iTotal;
    double dTotal;

    protected Cursor cursor;
    Database dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_detail_penyewa);

        Button btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent o = new Intent(SewaActivity.this, HomeActivity.class);
//                startActivity(o);
            }
        });

        dbHelper = new Database(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from penyewa, motor, sewa where penyewa.nama = sewa.nama AND motor.merk = sewa.merk AND penyewa.nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            sNama = cursor.getString(0);
            sId = cursor.getString(1);
            sHP = cursor.getString(2);
            sMerk = cursor.getString(3);
            sHarga = cursor.getString(4);
            iLama = cursor.getInt(7);
            dTotal = cursor.getDouble(8);
        }

        TextView tvNama = findViewById(R.id.HNama);
        TextView tvId = findViewById(R.id.HaId);
        TextView tvHP = findViewById(R.id.HTelp);

        TextView tvMerk = findViewById(R.id.HMerk);
        TextView tvHarga = findViewById(R.id.HHarga);

        TextView tvLama = findViewById(R.id.HLamaSewa);
        TextView tvTotal = findViewById(R.id.HTotal);

        tvNama.setText("     " + sNama);
        tvId.setText("     " + sId);
        tvHP.setText("     " + sHP);

        tvMerk.setText("     " + sMerk);
        tvHarga.setText("     Rp. " + sHarga);

        tvLama.setText("     " + iLama + " hari");
        iTotal = (int) dTotal;
        tvTotal.setText("     Rp. " + iTotal);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


