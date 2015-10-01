package pe.edu.esan.appesan2;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * Modulo de registro del estacionamiento, aqui al apretar alguno de los botones del semaforo se enviara ese dato a una base de datos remota
 *
 * primero se hace la consulta de en que estado esta el estacionamiento con http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php
 *
 * para guardar un dato del estado del estacionamiento se usa http://www.estacionamientoesan.site88.net/cas/register.php
 *
 *
 * los estados del estacionamiento son:
 *
 * rojo, amarillo y verde
 *
 * */
public class Registroesta extends Fragment {
    //Declaracion de variables

    //Para el semaforo
    private ImageView sem1,sem2,sem3;

    //Dato del color activo del semaforo
    private String libres;

    //Dialogo de progreso de carga
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    private static final String REGISTER_URL = "http://www.estacionamientoesan.site88.net/cas/register.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static String url_all_empresas = "http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private String estado="waa";
    JSONArray products = null;
    JSONParser jParser = new JSONParser();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_registroesta, container, false);
        setRetainInstance(true);

        //Se da valor a los elemntos declarados llamando a los elementos correspondientes en el layout
        //Semaforo rojo
        sem1=(ImageView)v.findViewById(R.id.sema1);

        //Semaforo amarillo
        sem2=(ImageView)v.findViewById(R.id.sema2);

        //Semaforo verde
        sem3=(ImageView)v.findViewById(R.id.sema3);

        new LoadAllProducts().execute();// Este metodo busca el estado actual del estacionamiento en la base de datos



        sem1.setOnClickListener(new View.OnClickListener() {//se detecta si se hizo click a este boton(bombilla roja del semaforo)
            @Override
            public void onClick(View v) {
        //Cuando el semaforo rojo es clickeado
                //Si es que existe conexion a internet
                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoprendido);//Se activa la imagen del rojo predido
                    sem2.setImageResource(R.drawable.amarilloapagado);//Se apaga la luz amarilla
                    sem3.setImageResource(R.drawable.verdeapagado);//Se apaga la luz verde
                    libres="rojo";
                    new CreateUser().execute();// se ejecuta este metodo, que guarda el estado "rojo" en la base de datos
                //Si no existe conexion a internet
                }else{
                    Toast.makeText(getActivity(), "No hay conexion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }



            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //Cuando el semaforo amarillo es clickeado
                //Si es que existe conexion a internet
                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoapagado);
                    sem2.setImageResource(R.drawable.amarilloprendido);
                    sem3.setImageResource(R.drawable.verdeapagado);
                    libres="amarillo";
                    new CreateUser().execute();

                //Si no existe conexion a internet
                }else{
                    Toast.makeText(getActivity(), "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }


            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet
                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoapagado);
                    sem2.setImageResource(R.drawable.amarilloapagado);
                    sem3.setImageResource(R.drawable.verdeprendido);
                    libres="verde";
                    new CreateUser().execute();

                //Si no existe conexion a internet
                }else{
                    Toast.makeText(getActivity(), "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }


            }
        });



        return v;

    }



    class CreateUser extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
        //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String username = libres;
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));


                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        REGISTER_URL, "POST", params);

                // full json response
                Log.d("Registering attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
            }



        }
    }


    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Antes de empezar el background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        protected String doInBackground(String... args) {
            List params = new ArrayList();
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    products = json.getJSONArray(TAG_PRODUCTS);
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        estado=c.getString(TAG_NOMBRE);
                        Log.d("estado registro", c.getString(TAG_NOMBRE));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            //Despues de ser ejecutada la accion

            //El dialogo de progreso desaparece
            pDialog.dismiss();

            //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            if(estado.equals("rojo")){
                sem1.setImageResource(R.drawable.rojoprendido);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeapagado);

            //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado.equals("amarillo")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloprendido);
                sem3.setImageResource(R.drawable.verdeapagado);

            //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado.equals("verde")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeprendido);

            }

        }
    }

    private boolean isNetworkAvailable() {
        //Obtiene verdadero o falso segun la verificaion de conexion a internet

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }








}
