package pe.edu.esan.appesan2;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



public class Estacionamiento extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    //Se declaran variables
    private static String url_all_empresas = "http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private String estado="waa";
    JSONArray products = null;
    JSONParser jParser = new JSONParser();
    TextView tvlibres, titulo, tit1, tit2;
    String estado2;
    private String tt;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    double longitude;
    double latitude;

    Button btEsan,btPolo,btAlonso;

    //PARA FUENTE:
    TextView textViewestareg;
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding;

    //ELEMENTOS PERTENECIENTES AL SLIDING UP PANEL

    protected synchronized void buildGoogleApiClient() {//Se sincroniza la aplicacion con el API de google
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)//Registers a listener to receive connection events from this GoogleApiClient. Applications should balance calls to this method with calls to unregisterConnectionCallbacks(ConnectionCallbacks) to avoid leaking resources.
                .addOnConnectionFailedListener(this)//Adds a listener to register to receive connection failed events from this GoogleApiClient. Applications should balance calls to this method with calls to unregisterConnectionFailedListener(OnConnectionFailedListener) to avoid leaking resources.
                .addApi(LocationServices.API)//Specify which Apis are requested by your app.
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {//overrie que se ejecuta cuando hay conexion con google api

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        //la variable mLastLocation obtendra la ultima locacion registrada por google api client

        if (mLastLocation != null) {//Si mLastLocation tiene algun dato
            tt="Latitude: "+ String.valueOf(mLastLocation.getLatitude())+"Longitude: "+
                    String.valueOf(mLastLocation.getLongitude());//la variable tt obtendra el valor de las latitudes y longitudes en un string para comprobar e imprimirlas
            Log.v("location",tt);//se imprimiran en un log el string almacenado en tt para verificar si se eesta obteniendo las coordenaadas correctamente

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+ String.valueOf(mLastLocation.getLatitude()) +","+ String.valueOf(mLastLocation.getLongitude()) +"&daddr="+ String.valueOf(latitude) +","+ String.valueOf(longitude)));
            //Se crea el intent que abrira un url especial con las cooordenadas obtenidas, el intent automaticamente obtendra las formas de abrir la locacion con tu celular, como con waze , google maps y chrome
            startActivity(intent);//Inicia el Intent
        }

    }

    @Override
    public void onConnectionSuspended(int i) {//Override que se ejecuta cuando la conexion es suspendida
        Toast.makeText(getActivity(), "Connection suspended...", Toast.LENGTH_SHORT).show();//Se muestra el mensaje cconxion suspedida en un TOAST

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {//Override que se ejecuta cuando la conexion falla
        Toast.makeText(getActivity(), "Failed to connect...", Toast.LENGTH_SHORT).show();//Se muestra el mensaje Failed to connect

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estacionamiento, container, false);//Se relaciona el View con su respectivo XML, lay_estacionamiento
        final MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);

        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion

        btEsan=(Button)v.findViewById(R.id.btEsan);
        btAlonso=(Button)v.findViewById(R.id.btAlonso);
        btPolo=(Button)v.findViewById(R.id.btPolo);





        final Handler h = new Handler();
        final int delay = 5000; //milliseconds


        new LoadAllProducts().execute();
        h.postDelayed(new Runnable() {
            public void run() {
                Log.v("tipo", "timer");
                Log.v("es", estado);
                //Log.v("es2", estado2);

                if (estado.equals("rojo")) {
                        btEsan.setBackgroundColor(Color.parseColor("#F0152B"));





                }
                if (estado.equals("amarillo")) {
                    btEsan.setBackgroundColor(Color.parseColor("#F7F020"));




                }
                if (estado.equals("verde")) {
                    btEsan.setBackgroundColor(Color.parseColor("#15AE0D"));



                }

                //if (isNetworkAvailable() == false) {
                 //   sema1e.setImageResource(R.drawable.rojoapagado);
                //    sema2e.setImageResource(R.drawable.amarilloapagado);
                //    sema3e.setImageResource(R.drawable.verdeapagado);
                //    tvlibres.setText("No hay coneccion a internet");
                //}
                new LoadAllProducts().execute();
                h.postDelayed(this, delay);
            }
        }, delay);









        return v;//Se retorna el view
    }


    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Antes de empezar el background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
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
                        estado2=estado;
                        estado=c.getString(TAG_NOMBRE);

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {


        }
    }








}




