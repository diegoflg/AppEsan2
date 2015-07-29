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

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);


        et1.setText("alumno");
        et2.setText("alumno");

        AdminBD admin = new AdminBD(this, "BDESAN3", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Log.v("prueba", "1");

        Cursor fila1 = bd.rawQuery("select usuario from Persona", null);
        if (fila1.moveToFirst()) {
            //Toast t=Toast.makeText(this,"haydatos", Toast.LENGTH_SHORT);
            // t.show();

        } else {

            Log.v("prueba", "2");

            String usuario = "alumno";
            String password = "alumno";

            ContentValues registro = new ContentValues();
            registro.put("usuario", usuario);
            registro.put("password", password);
            bd.insert("Persona", null, registro);


            String usuario2 = "alumnodos";
            String password2 = "alumnodos";

            ContentValues registro2 = new ContentValues();
            registro2.put("usuario", usuario2);
            registro2.put("password", password2);
            bd.insert("Persona", null, registro2);

            String usuario3 = "servicios";
            String password3 = "servicios";

            ContentValues registro3 = new ContentValues();
            registro3.put("usuario", usuario3);
            registro3.put("password", password3);
            bd.insert("Persona", null, registro3);


        }

        //Cerramos la base de datos
        bd.close();


        NotasBD notas = new NotasBD(this, "BDNOTAS2", null, 1);
        SQLiteDatabase bdn = notas.getWritableDatabase();

        Cursor filanotas = bdn.rawQuery("select usuario from Notas", null);
        if (filanotas.moveToFirst()) {


        } else {


            String usuarionota = "alumno";
            String cursonota = "Matematicas";
            String cursonota2 = "Lengua";
            String cursonota3 = "Programacion";
            String cursonota4 = "Filosofia";
            int ep1 = 10;
            int ep2 = 07;
            int ep3 = 13;
            int ep4 = 12;
            int ta1 = 18;
            int ta2 = 03;
            int ta3 = 13;
            int ta4 = 10;
            int ef1 = 11;
            int ef2 = 17;
            int ef3 = 12;
            int ef4 = 17;
            int pg1 = 15;
            int pg2 = 14;
            int pg3 = 12;
            int pg4 = 9;


            ContentValues registronota1 = new ContentValues();
            registronota1.put("usuario", usuarionota);
            registronota1.put("curso", cursonota);
            registronota1.put("ep", ep1);
            registronota1.put("ta", ta1);
            registronota1.put("ef", ef1);
            registronota1.put("pg", pg1);

            bdn.insert("Notas", null, registronota1);

            ContentValues registronota2 = new ContentValues();
            registronota2.put("usuario", usuarionota);
            registronota2.put("curso", cursonota2);
            registronota2.put("ep", ep2);
            registronota2.put("ta", ta2);
            registronota2.put("ef", ef2);
            registronota2.put("pg", pg2);

            bdn.insert("Notas", null, registronota2);

            ContentValues registronota3 = new ContentValues();
            registronota3.put("usuario", usuarionota);
            registronota3.put("curso", cursonota3);
            registronota3.put("ep", ep3);
            registronota3.put("ta", ta3);
            registronota3.put("ef", ef3);
            registronota3.put("pg", pg3);

            bdn.insert("Notas", null, registronota3);

            ContentValues registronota4 = new ContentValues();
            registronota4.put("usuario", usuarionota);
            registronota4.put("curso", cursonota4);
            registronota4.put("ep", ep4);
            registronota4.put("ta", ta4);
            registronota4.put("ef", ef4);
            registronota4.put("pg", pg4);

            bdn.insert("Notas", null, registronota4);



        }

        //Cerramos la base de datos
        bd.close();

        HorarioBD adminH = new HorarioBD(this, "BDHORARIO1", null, 1);
        SQLiteDatabase bdH = adminH.getWritableDatabase();

        Cursor c = bdH.rawQuery("SELECT usuario FROM Horario", null);

        if(c.moveToFirst())
        {   }else{


            String usuarioH="alumno1";
            String curso0= "Matemática II";
            String dia0="Lunes";
            String dia1="Miercoles";
            String hora0= "7:00-8:00";
            String hora1="8:00-9:00";
            String hora2= "9:00-10:00";
            String salon0="A-505";

            ContentValues rH0 = new ContentValues();
            rH0.put("usuario",usuarioH);
            rH0.put("curso",curso0);
            rH0.put("dia",dia0);
            rH0.put("hora",hora0);
            rH0.put("salon",salon0);
            bdH.insert("Horario",null,rH0);

            ContentValues rH1 = new ContentValues();
            rH1.put("usuario",usuarioH);
            rH1.put("curso",curso0);
            rH1.put("dia",dia0);
            rH1.put("hora",hora1);
            rH1.put("salon",salon0);
            bdH.insert("Horario",null,rH1);

            ContentValues rH2 = new ContentValues();
            rH2.put("usuario",usuarioH);
            rH2.put("curso",curso0);
            rH2.put("dia",dia1);
            rH2.put("hora",hora1);
            rH2.put("salon",salon0);
            bdH.insert("Horario",null,rH2);

            ContentValues rH3 = new ContentValues();
            rH3.put("usuario",usuarioH);
            rH3.put("curso",curso0);
            rH3.put("dia",dia1);
            rH3.put("hora",hora2);
            rH3.put("salon",salon0);
            bdH.insert("Horario",null,rH3);
        }
        //Cerramos la base de datos
        bdH.close();



        PagosBD adminP = new PagosBD(this, "BDPAGOS2", null, 1);
        SQLiteDatabase bdP = adminP.getWritableDatabase();

        Cursor j = bdP.rawQuery("SELECT usuario,concepto,monto,vencimiento FROM Pagos", null);

        if(j.moveToFirst())
        {   Toast t = Toast.makeText(this, "hay datos pagos", Toast.LENGTH_SHORT);
            t.show();

        }else{


            String usuarioP="alumno";
            String concepto= "Matricula";
            String monto="500";
            String vencimiento="08/09/2017";

            ContentValues p1 = new ContentValues();
            p1.put("usuario",usuarioP);
            p1.put("concepto",concepto);
            p1.put("monto",monto);
            p1.put("vencimiento",vencimiento);
            bdP.insert("Pagos",null,p1);






            String concepto2= "Cuota 1";
            String monto2="1200";
            String vencimiento2="15/09/2017";

            ContentValues p2 = new ContentValues();
            p2.put("usuario",usuarioP);
            p2.put("concepto",concepto2);
            p2.put("monto",monto2);
            p2.put("vencimiento",vencimiento2);
            bdP.insert("Pagos",null,p2);


            String concepto3= "Cuota 2";
            String monto3="1400";
            String vencimiento3="18/10/2017";

            ContentValues p3 = new ContentValues();
            p3.put("usuario",usuarioP);
            p3.put("concepto",concepto3);
            p3.put("monto",monto3);
            p3.put("vencimiento",vencimiento3);
            bdP.insert("Pagos",null,p3);



            String concepto4= "Cuota 3";
            String monto4="1100";
            String vencimiento4="20/10/2017";

            ContentValues p4 = new ContentValues();
            p4.put("usuario",usuarioP);
            p4.put("concepto",concepto4);
            p4.put("monto",monto4);
            p4.put("vencimiento", vencimiento4);
            bdP.insert("Pagos", null, p4);


            Toast t = Toast.makeText(this, "Se grabaron los datos de pagos", Toast.LENGTH_SHORT);
            t.show();






        }
        //Cerramos la base de datos
        bdP.close();





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

                    Log.v("tipo", String.valueOf(tipo));

                    Intent i = new Intent(this,MainActivity2Activity.class);
                    i.putExtras(b);

                    startActivity(i);
                    finish();


                }


                todo=todo+usuario+" " +password+" " + "\n";
            }while(c.moveToNext());

            Toast t=Toast.makeText(this,mensaje, Toast.LENGTH_SHORT);
            t.show();


        }


        //Intent i = new Intent(this,MainActivity2Activity.class);
        //AstartActivity(i);
    }
}