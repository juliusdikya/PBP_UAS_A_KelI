package tugas.besar.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sewamotor.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "id text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table motor(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "lama int," +
                "total double," +
                "foreign key(merk) references motor (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into motor values (" +
                "'CB 150 R'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into motor values (" +
                "'R 15'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into motor values (" +
                "'Vario'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into motor values (" +
                "'Mio'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into motor values (" +
                "'Supra'," +
                "50000" +
                ");" +
                "");
        db.execSQL("insert into motor values (" +
                "'Shogun'," +
                "50000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from motor";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}