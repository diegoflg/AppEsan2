package pe.edu.esan.appesan2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText et1,et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);



        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        AdminBD usdbh = new AdminBD(this, "DB1", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        Log.v("prueba","1");

        Cursor fila1 = db.rawQuery("select usuario from Persona",null);
        if(fila1.moveToFirst())
        {
            Toast t=Toast.makeText(this,"haydatos", Toast.LENGTH_SHORT);
            t.show();

        }else{

            Log.v("prueba", "2");

            String usuario="alumno";
            String password="alumno";

            ContentValues registro = new ContentValues();
            registro.put("usuario",usuario);
            registro.put("password",password);
            db.insert("Persona", null, registro);

            Toast t=Toast.makeText(this,"Se grabaron los datos de la persona", Toast.LENGTH_SHORT);
            t.show();
        }

            //Cerramos la base de datos
            db.close();


    }

    public void acceder(View v){

        AdminBD usdbh = new AdminBD(this, "DB1", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        String usuario=et1.getText().toString();;
        String password=et2.getText().toString();

        Log.v("prueba", usuario);



        Cursor fila = db.rawQuery("select password from Persona where usuario = "+usuario, null);

        Log.v("prueba", "3");



        db.close();



        //Intent i = new Intent(this,MainActivity2Activity.class);
        //AstartActivity(i);
    }
}
