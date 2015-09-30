package pe.edu.esan.appesan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Base de datos que guardaba datos de usuario, curso, dia, hora y salon para mandarlos luego
 * a la ventana de Horario, sin embargo esta clase ya no es utilizada en la aplicacion. Es decir, es
 * una clase extra que puede, si se desea, ser utilizada de nuevo.
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






