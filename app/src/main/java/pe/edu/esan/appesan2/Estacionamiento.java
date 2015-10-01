package pe.edu.esan.appesan2;


import android.content.Context;
import android.content.Intent;
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


/**
 * Modulo de estacionamiento
 */
public class Estacionamiento extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    //Se declaran variables
    private static String url_all_empresas = "http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private ImageView sema1e,sema2e,sema3e;
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


        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion


        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);//Se crea un windows manager llamado wm, que obtiene el valor obtenido usando los metodos getActivity().getSystemService(Context.WINDOW_SERVICE)
        Display display = wm.getDefaultDisplay();//Se crea un Display llamado display, que tendra el valor de wm.getDefaultDisplay()
        Point size = new Point();//Se crea un punto
        display.getSize(size);//Se obtiene el tamano de la pantalla del dispositivo
        int height = size.y;//se obtiene el alto
        int aa=height/4;//se crea el entero aa con el valor de la cuarta parte de la altura del dispositivo

        sliding=(com.sothree.slidinguppanel.SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout);//Se crea el slidinguppanel
        sliding.setAnchorPoint(aa);// el maximo de expansion del sliding panel sera el valor de aa







        titulo=(TextView)v.findViewById(R.id.titulo);//Se relaciona titulo con el titulo de su respectivo xml
        tit1=(TextView)v.findViewById(R.id.tit1);//Se relaciona tit1 con el tit1 de su respectivo xml
        tit2=(TextView)v.findViewById(R.id.tit2);//Se relaciona tit2 con el tit2 de su respectivo xml


        final MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
        //Se crea un MediaPlayer llamado mp que se le asignara el sonido hifi guardado en la carpeta raw

        //FUENTE Y COLOR PARA TEXTVIEWS
        String font_pathE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFE = Typeface.createFromAsset(getActivity().getAssets(), font_pathE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING
        textViewestareg = (TextView)v.findViewById(R.id.textViewestareg);
        textViewestareg.setTypeface(TFE);

        String font_pathL = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFL = Typeface.createFromAsset(getActivity().getAssets(), font_pathL);
        titulo.setTypeface(TFL);//Se le agrega el tipo de fuente al text view
        tit1.setTypeface(TFL);//Se le agrega el tipo de fuente al text view
        tit2.setTypeface(TFL);//Se le agrega el tipo de fuente al text view

        tvlibres=(TextView)v.findViewById(R.id.textlibres);//Se relaciona tvlibres con el textlibres de su respectivo xml
        tvlibres.setTypeface(TFL);

        sema1e=(ImageView)v.findViewById(R.id.sema1e);//Se relaciona sema1e con el sema1e de su respectivo xml
        sema2e=(ImageView)v.findViewById(R.id.sema2e);//Se relaciona sema2e con el sema2e de su respectivo xml
        sema3e=(ImageView)v.findViewById(R.id.sema3e);//Se relaciona sema3e con el sema3e de su respectivo xml


        final Handler h = new Handler();//Se crea un handler llamado h
        final int delay = 5000; //milliseconds


        new LoadAllProducts().execute();/// Este metodo busca el estado actual del estacionamiento en la base de datos

        h.postDelayed(new Runnable() {//comeinza el handler
            public void run() {
                Log.v("tipo", "timer");
                Log.v("es", estado);
                //Log.v("es2", estado2);

                if(estado.equals("rojo")){//Si el estado del estacionamiento es igual a rojo
                    sema1e.setImageResource(R.drawable.rojoprendido);//Se activa la imagen del rojo predido
                    sema2e.setImageResource(R.drawable.amarilloapagado);//Se activa la imagen del amarillo apagado
                    sema3e.setImageResource(R.drawable.verdeapagado);//Se activa la imagen del verde apagado
                    tvlibres.setText("Playa llena");//El texto cambiara a playa llena


                    if(estado.equals(estado2)){//Si no se ha cambiado de estado no pasa nada


                    }else{//si se ha cambiado

                        if(Datah.getInstance().getMenu()==1){//Si se esta en el fragmento estacionamiento
                            mp.start();//sonara el sonido

                        }

                        sliding.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);//EL panel se expandera automaticamente porque esta en rojo el semaforo
                    }

                }
                if(estado.equals("amarillo")){//Si el estado es amarillo
                    sema1e.setImageResource(R.drawable.rojoapagado);//Se activa la imagen del rojo apagado
                    sema2e.setImageResource(R.drawable.amarilloprendido);//Se activa la imagen del amarillo predido
                    sema3e.setImageResource(R.drawable.verdeapagado);//Se activa la imagen del verde predido
                    tvlibres.setText("De 4 a 20 estacionamientos disponibles");//el textview cambia a De 4 a 20 estacionamientos disponibles

                    if(estado.equals(estado2)){    //Si no se ha cambiado de estado no pasa nada

                    }else{//si se ha cambiado




                        if(Datah.getInstance().getMenu()==1){
                            mp.start();//sonara el sonido

                        }
                        sliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);//EL panel colapsara automaticamente porque esta en amarillo el semaforo

                    }



                }
                if(estado.equals("verde")){//Si el estado es verde
                    sema1e.setImageResource(R.drawable.rojoapagado);//Se activa la imagen del rojo apagado
                    sema2e.setImageResource(R.drawable.amarilloapagado);//Se activa la imagen del amarillo apagado
                    sema3e.setImageResource(R.drawable.verdeprendido);//Se activa la imagen del verde prendido
                    tvlibres.setText("Playa libre");

                    if(estado.equals(estado2)){//Si no se ha cambiado de estado no pasa nada


                    }else{//sino

                        if(Datah.getInstance().getMenu()==1){///Si se esta en el fragmento estacionamiento
                            mp.start();//Sonara el sonido

                        }
                        sliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);//El sliding panel colapsara
                    }



                }

                if(isNetworkAvailable()==false){//Si no hay conexion a internet
                    sema1e.setImageResource(R.drawable.rojoapagado);//Se mostrara la imagen del semaforo rojo apagado
                    sema2e.setImageResource(R.drawable.amarilloapagado);//Se mostrara la imagen del semaforo amarillo apagado
                    sema3e.setImageResource(R.drawable.verdeapagado);//Se mostrara la imagen del semaforo verde apagado
                    tvlibres.setText("No hay conexion a internet");//El textview tvlibres cambiara a no hay conexion a internet
                }
                new LoadAllProducts().execute();//Se ejecutara el metodo LoadAllProducts
                h.postDelayed(this, delay);
            }
        }, delay);

        ImageView marker1= (ImageView)v.findViewById(R.id.marker1);//Se creara el ImageView marker1 y se relacionara con marker1 de su respectivo xml
        ImageView marker2= (ImageView)v.findViewById(R.id.marker2);//Se creara el ImageView marker2 y se relacionara con marker2 de su respectivo xml

        marker1.setOnClickListener(new View.OnClickListener() {//Si se presiona marker 1 pasara lo siguiente:
            @Override
            public void onClick(View v) {//Se detecta el click
               // FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               // EstaMapas fragment = new EstaMapas();

              //  Bundle bundle = new Bundle();
              //  bundle.putString("lugar","polo");
              //  fragment.setArguments(bundle);
               // fragmentManager.beginTransaction().add(R.id.container, fragment, "Map1").commit();
                Log.v("detect","sepudo");//Log de prueba para verificaar

               latitude = -12.098581;//se guarda -12.098581 en la variable latitude
                longitude = -76.970599;///se guarda -76.970599 en la variable longitude

                buildGoogleApiClient();//Se contruye el google api client

                if(mGoogleApiClient!= null){//Si es diferente de  nulo
                   mGoogleApiClient.connect();//se conecta
                }
                else {//sino
                    Toast.makeText(getActivity(), "Not connected...", Toast.LENGTH_SHORT).show();//Se muestra un toast para avisar que no esta conectado
               }



            }
        });

        marker2.setOnClickListener(new View.OnClickListener() {//Se detecta si se clickeo el marker2
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               // EstaMapas fragment = new EstaMapas();

              //  Bundle bundle = new Bundle();
              //  bundle.putString("lugar","alonso");
               // fragment.setArguments(bundle);
               // fragmentManager.beginTransaction().add(R.id.container, fragment, "Map2").commit();
                latitude = -12.105392;//Se guarda -12.105392 en la variable latitude
                longitude = -76.963559;// Se guarda -76.963559 en la variable longitude


                buildGoogleApiClient();//Se contruye el google api client

                if(mGoogleApiClient!= null){//Si el mgooogleapiclient es diferente de nulo se conecta
                    mGoogleApiClient.connect();
                }
                else {
                    Toast.makeText(getActivity(), "Not connected...", Toast.LENGTH_SHORT).show();//sinno muestra un toast que dice not connected
                }




            }
        });

        return v;//Se retorna el view
    }




    class LoadAllProducts extends AsyncTask<String, String, String> {//Asynctask que carga el estado actual del estacionamiento

        /**
         * Antes de empezar el background thread Show Progress Dialog
         * */
        @Override//override que se ejecuta antes de ejecutarse
        protected void onPreExecute() {
        }

        protected String doInBackground(String... args) {//metodo quee se ejecuta en segundo plano
            List params = new ArrayList();//se crea un array
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);//se guarda en un json los datos de http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php
            try {//se comprueba si obtuvo el JSON
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    products = json.getJSONArray(TAG_PRODUCTS);
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        estado2=estado;//se guarda en estado en estado2 para luego comparar si se ha cambiado de estado
                        estado=c.getString(TAG_NOMBRE);//se actualiza estado con el estado almacenado en la base de datos

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

    private boolean isNetworkAvailable() {//Se verifica la conexion a internet
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}




