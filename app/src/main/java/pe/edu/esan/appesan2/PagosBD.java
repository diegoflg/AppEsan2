package pe.edu.esan.appesan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Modulo que mostraba el estado de pagos de una base de datos interna
 * Este modulo ya no se usa
 */
public class PagosBD extends SQLiteOpenHelper{
    String sqlCreate="CREATE TABLE Pagos (usuario TEXT, concepto TEXT, monto TEXT, vencimiento TEXT)";

    public PagosBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Pagos");
        db.execSQL(sqlCreate);
    }
}





