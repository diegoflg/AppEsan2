package pe.edu.esan.appesan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diegoflg on 7/21/2015.
 */
public class NotasBD extends SQLiteOpenHelper {

    String sqlCreateNotas="CREATE TABLE Notas (usuario TEXT, curso TEXT, ep REAL, ta REAL, ef REAL, pg REAL)";



    public NotasBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreateNotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Notas");
        db.execSQL(sqlCreateNotas);
    }
}


//961062059
//52q8ds