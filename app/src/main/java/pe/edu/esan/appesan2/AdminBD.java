package pe.edu.esan.appesan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminBD extends SQLiteOpenHelper {

    String sqlCreate="CREATE TABLE Persona (usuario TEXT, password TEXT)";




    public AdminBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Persona");
        db.execSQL(sqlCreate);
    }
}
