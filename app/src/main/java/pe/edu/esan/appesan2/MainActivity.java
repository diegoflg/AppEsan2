package pe.edu.esan.appesan2;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends ActionBarActivity {
    private final String TAG= "APP";
    EditText et1,et2;
    String langloc=Locale.getDefault().getDisplayLanguage();
    int langinicial=0;
    int lang=0;
    Button botonacceder; //SOLO SE USA PARA CAMBIAR LA FUENTE
    String loggresult="";
    String mensaje="Usuario o password invalida";
    int tipo=1;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_main);

        Log.v("lang", langloc);

        if(langloc.equals("español")){
            langinicial=0;

        }
        if(langloc.equals("English")){
            langinicial=1;

        }
        if(langloc.equals("français")){
            langinicial=2;

        }


        ArrayList<ItemData> list=new ArrayList<>();
        switch(langinicial){
            case 0:
                list.add(new ItemData("Español",R.drawable.es));
                list.add(new ItemData("Ingles",R.drawable.um));
                list.add(new ItemData("Frances",R.drawable.fr));
                break;
            case 1:
                list.add(new ItemData("Spanish",R.drawable.es));
                list.add(new ItemData("English",R.drawable.um));
                list.add(new ItemData("French",R.drawable.fr));
                break;
            case 2:
                list.add(new ItemData("Espagnol",R.drawable.es));
                list.add(new ItemData("Anglais",R.drawable.um));
                list.add(new ItemData("Français",R.drawable.fr));
                break;
        }



        Spinner sp=(Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinerlayout,R.id.txt,list);
        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                lang = index;

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        SpannableString s = new SpannableString("ESAN");
        s.setSpan(new TypefaceSpan("HelveticaNeue-Roman.ttf"), 0 , s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);


        android.support.v7.app.ActionBar abLogin = getSupportActionBar();
        CharSequence titulo = abLogin.getTitle().toString();
        Log.i(TAG, "Título del app: " + titulo);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        botonacceder = (Button) findViewById(R.id.button); //SOLO ES USADO PARA LA FUENTE

        et1.setText("1005831");
        et2.setText("esan1520");


        //ESTO ES PARA LA FUENTE
        String font_path = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        et1.setTypeface(TF);
        et2.setTypeface(TF);
        botonacceder.setTypeface(TF);


       AdminBD admin = new AdminBD(this, "BDESAN3", null, 1);
       SQLiteDatabase bd = admin.getWritableDatabase();

      Log.v("prueba", "1");

      Cursor fila1 = bd.rawQuery("select usuario from Persona", null);
      if (fila1.moveToFirst()) {
           Toast t=Toast.makeText(this,"haydatos", Toast.LENGTH_SHORT);
            t.show();

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
        {   //Toast t = Toast.makeText(this, "hay datos pagos", Toast.LENGTH_SHORT);
            //t.show();

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








        }
        //Cerramos la base de datos
        bdP.close();





    }

    public void acceder(){

            String usuario, password,todo,us,pass;
            us=et1.getText().toString();
            pass=et2.getText().toString();


            AdminBD admin= new AdminBD(this, "BDESAN3",null, 1);
            SQLiteDatabase bd=admin.getWritableDatabase();


            String mensaje2="Incorrect user or password";
            String mensaje3="Mot de passe ou utilisateur incorrect";

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

                    if(us.equals(usuario) && pass.equals(password)) {

                        mensaje = "correcto";

                        if (us.equals("alumno")) {
                            tipo = 1;
                        }
                        if (us.equals("servicios")) {
                            tipo = 2;
                        }

                        b.putInt("tipo", tipo);

                        Log.v("tipo", String.valueOf(tipo));

                        Intent i = new Intent(this, MainActivity2Activity.class);
                        i.putExtras(b);





                        Datah.getInstance().setLang(lang);




                        switch(lang){
                            case 0:

                                Locale locale = new Locale("es");
                                Locale.setDefault(locale);
                                Configuration mConfig = new Configuration();
                                mConfig.locale = locale;
                                getBaseContext().getResources().updateConfiguration(mConfig,
                                        getBaseContext().getResources().getDisplayMetrics());

                                break;

                            case 1:

                                Locale locale2 = new Locale("en");
                                Locale.setDefault(locale2);
                                Configuration mConfig2 = new Configuration();
                                mConfig2.locale = locale2;
                                getBaseContext().getResources().updateConfiguration(mConfig2,
                                        getBaseContext().getResources().getDisplayMetrics());

                                break;

                            case 2:

                                Locale locale3 = new Locale("fr");
                                Locale.setDefault(locale3);
                                Configuration mConfig3 = new Configuration();
                                mConfig3.locale = locale3;
                                getBaseContext().getResources().updateConfiguration(mConfig3,
                                        getBaseContext().getResources().getDisplayMetrics());

                                break;

                        }





                        startActivity(i);
                        finish();


                    }


                    todo=todo+usuario+" " +password+" " + "\n";
                }while(c.moveToNext());


                if(mensaje.equals("correcto")){

                }else{



                        switch(lang){
                            case 0:
                                Toast t=Toast.makeText(this,mensaje, Toast.LENGTH_SHORT);
                                t.show();



                                break;

                            case 1:
                                Toast y=Toast.makeText(this,mensaje2, Toast.LENGTH_SHORT);
                                y.show();



                                break;

                            case 2:
                                Toast u=Toast.makeText(this,mensaje3, Toast.LENGTH_SHORT);
                                u.show();



                                break;







                    }





                }




            }


            //Intent i = new Intent(this,MainActivity2Activity.class);
            //AstartActivity(i);

    }


    public void logstart(View v){

        if(isNetworkAvailable()==true){
            Datah.getInstance().setUser(et1.getText().toString());
            Datah.getInstance().setPass(et2.getText().toString());
            new logg().execute();

        }else{
            Toast t=Toast.makeText(this,"No hay coneccion a internet", Toast.LENGTH_SHORT);
            t.show();

        }





    }

    private class logg extends AsyncTask<String, Void, String> {
        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "Please wait, Loading Page...", true);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
            else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }

        }

        @Override
        protected String doInBackground(String... params) {

            Log.v("v1", "paso2");

            try {

                Connection.Response res1 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php").method(Connection.Method.GET).timeout(0).execute();
                Document doc = res1.parse();
                Map welcomCookies = res1.cookies();



                Connection.Response res2 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php")
                        .data("username", et1.getText().toString())
                        .data("password", et2.getText().toString())
                        .cookies(welcomCookies)
                        .timeout(10000)
                        .method(Connection.Method.POST)
                        .execute();

                Document docl = res2.parse();
                Log.v("titulo", docl.title());

                if(docl.title().equals("ESANVIRTUAL")){
                    loggresult="si";
                    mensaje="correcto";

                }

                if(docl.title().equals("ESANVIRTUAL: Entrar al sitio")){
                    loggresult="no";

                }






            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();



            if(loggresult.equals("si")){



                dialog.dismiss();

                Bundle b=new Bundle();


                mensaje = "correcto";


                b.putInt("tipo", tipo);

                Log.v("tipo", String.valueOf(tipo));

                Intent i = new Intent(getApplicationContext(), MainActivity2Activity.class);
                i.putExtras(b);





                Datah.getInstance().setLang(lang);




                switch(lang){
                    case 0:

                        Locale locale = new Locale("es");
                        Locale.setDefault(locale);
                        Configuration mConfig = new Configuration();
                        mConfig.locale = locale;
                        getBaseContext().getResources().updateConfiguration(mConfig,
                                getBaseContext().getResources().getDisplayMetrics());

                        break;

                    case 1:

                        Locale locale2 = new Locale("en");
                        Locale.setDefault(locale2);
                        Configuration mConfig2 = new Configuration();
                        mConfig2.locale = locale2;
                        getBaseContext().getResources().updateConfiguration(mConfig2,
                                getBaseContext().getResources().getDisplayMetrics());

                        break;

                    case 2:

                        Locale locale3 = new Locale("fr");
                        Locale.setDefault(locale3);
                        Configuration mConfig3 = new Configuration();
                        mConfig3.locale = locale3;
                        getBaseContext().getResources().updateConfiguration(mConfig3,
                                getBaseContext().getResources().getDisplayMetrics());

                        break;

                }





                startActivity(i);
                finish();



            }else{
                acceder();
            }

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);





        }







    }
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}