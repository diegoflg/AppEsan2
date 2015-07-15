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

    EditText et1,et2,et3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);

        et1.setText("alumno");
        et2.setText("alumno");



        AdminBD admin= new AdminBD(this, "BDESAN3",null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        Log.v("prueba", "1");

        Cursor fila1 = bd.rawQuery("select usuario from Persona",null);
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
            bd.insert("Persona", null, registro);


            String usuario2="alumnodos";
            String password2="alumnodos";

            ContentValues registro2 = new ContentValues();
            registro2.put("usuario",usuario2);
            registro2.put("password",password2);
            bd.insert("Persona", null, registro2);

            String usuario3="servicios";
            String password3="servicios";

            ContentValues registro3 = new ContentValues();
            registro3.put("usuario",usuario3);
            registro3.put("password",password3);
            bd.insert("Persona", null, registro3);

            Toast t=Toast.makeText(this,"Se grabaron los datos de la persona", Toast.LENGTH_SHORT);
            t.show();
        }

            //Cerramos la base de datos
            bd.close();


    }

    public void acceder(View v){

        String usuario, password,todo,us,pass;
        us=et1.getText().toString();
        pass=et2.getText().toString();

        Log.v("us", us);
        Log.v("pass", pass);







        AdminBD admin= new AdminBD(this, "BDESAN3",null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        String mensaje="usuario o password invalida";
        int tipo=1;
        Bundle b=new Bundle();


        Cursor c=bd.rawQuery("SELECT usuario,password FROM Persona",null);
        todo="";

        if(c.moveToFirst())
        {
            do {
                            usuario=c.getString(0);
                    Log.v("usuario", usuario);
                    password=c.getString(1);
                    Log.v("password", password);

                    if(us.equals(usuario) && pass.equals(password)){

                        mensaje="usuario y password correctos";

                        if(us.equals("alumno")){
                            tipo=1;
                        }
                        if(us.equals("servicios")){
                            tipo=2;
                        }

                        b.putInt("tipo",tipo);

                        Intent i = new Intent(this,MainActivity2Activity.class);
                        i.putExtras(b);

                        startActivity(i);


                }


                todo=todo+usuario+" " +password+" " + "\n";
            }while(c.moveToNext());

            Toast t=Toast.makeText(this,"usuario o password invalida", Toast.LENGTH_SHORT);
            t.show();

            et3.setText(todo);
        }


        //Intent i = new Intent(this,MainActivity2Activity.class);
        //AstartActivity(i);
    }
}
