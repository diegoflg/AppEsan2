package pe.edu.esan.appesan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diegoflg on 7/21/2015.
 */
public class HorarioBD extends SQLiteOpenHelper {

    String sqlCreate="CREATE TABLE Horario (usuario TEXT, curso TEXT,dia TEXT, hora TEXT, salon TEXT)";



    public HorarioBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Horario");
        db.execSQL(sqlCreate);
    }
}
