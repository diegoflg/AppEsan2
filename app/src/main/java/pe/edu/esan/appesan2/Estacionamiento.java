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

        import org.apache.http.HttpResponse;
        import org.apache.http.HttpStatus;
        import org.apache.http.StatusLine;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Estacionamiento extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // The minimum distance to change Updates in meters


    //Se declaran variables
    private static String url_all_empresas = "http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private static final String TAG_NOMBRE2 = "username2";
    private static final String TAG_NOMBRE3 = "username3";
    private ImageView sema1e,sema2e,sema3e;
    private String estado="waa";
    private String estadoalonso="waa";
    private String estadopolo="waa";
    JSONArray products = null;
    JSONParser jParser = new JSONParser();
    TextView tvlibres, titulo, tit1, tit2;
    String estado2;
    String estado22;
    String estado23;
    private String tt;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    double longitude;
    double latitude;

    Button btEsan,btPolo,btAlonso,btir;

    //PARA FUENTE:
    TextView textViewestareg;

    //ELEMENTOS PERTENECIENTES AL SLIDING UP PANEL

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            tt="Latitude: "+ String.valueOf(mLastLocation.getLatitude())+"Longitude: "+
                    String.valueOf(mLastLocation.getLongitude());
            Log.v("location",tt);

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+ String.valueOf(mLastLocation.getLatitude()) +","+ String.valueOf(mLastLocation.getLongitude()) +"&daddr="+ String.valueOf(latitude) +","+ String.valueOf(longitude)));
            startActivity(intent);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getActivity(), "Connection suspended...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "Failed to connect...", Toast.LENGTH_SHORT).show();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estacionamiento, container, false);


        setRetainInstance(true);




        //FUENTE Y COLOR PARA TEXTVIEWS
        String font_pathE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFE = Typeface.createFromAsset(getActivity().getAssets(), font_pathE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING
        textViewestareg = (TextView)v.findViewById(R.id.textViewestareg);
        textViewestareg.setTypeface(TFE);

        String font_pathL = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFL = Typeface.createFromAsset(getActivity().getAssets(), font_pathL);
        btEsan=(Button)v.findViewById(R.id.btEsan);
        btAlonso=(Button)v.findViewById(R.id.btAlonso);
        btPolo=(Button)v.findViewById(R.id.btPolo);
        btir=(Button)v.findViewById(R.id.btir);




        final Handler h = new Handler();
        final int delay = 5000; //milliseconds


        new LoadAllProducts().execute();
        h.postDelayed(new Runnable() {
            public void run() {
                Log.v("tipo", "timer");
                Log.v("es", estado);
                Log.v("es2", estadoalonso);

                if (estado.equals("rojo")) {
                    btEsan.setBackgroundResource(R.drawable.brojo);


                    if (estado.equals(estado2)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();


                        }

                    }

                }
                if (estadoalonso.equals("rojo")) {
                    btAlonso.setBackgroundResource(R.drawable.brojo);


                    if (estadoalonso.equals(estado22)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();


                        }

                    }

                }

                if (estadopolo.equals("rojo")) {
                    btPolo.setBackgroundResource(R.drawable.brojo);


                    if (estadopolo.equals(estado23)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();


                        }

                    }

                }





                if (estado.equals("amarillo")) {
                    btEsan.setBackgroundResource(R.drawable.bamarillo);
                    if (estado.equals(estado2)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();

                        }

                    }


                }

                if (estadoalonso.equals("amarillo")) {
                    btAlonso.setBackgroundResource(R.drawable.bamarillo);
                    if (estado.equals(estado22)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();

                        }

                    }


                }
                if (estadopolo.equals("amarillo")) {
                    btPolo.setBackgroundResource(R.drawable.bamarillo);
                    if (estadopolo.equals(estado23)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();

                        }

                    }


                }

                if (estado.equals("verde")) {
                    btEsan.setBackgroundResource(R.drawable.bverde);

                    if (estado.equals(estado2)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();


                        }

                    }


                }

                if (estadoalonso.equals("verde")) {
                    btAlonso.setBackgroundResource(R.drawable.bverde);

                    if (estadoalonso.equals(estado22)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();

                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();

                        }

                    }


                }

                if (estadopolo.equals("verde")) {
                    btPolo.setBackgroundResource(R.drawable.bverde);

                    if (estadopolo.equals(estado23)) {


                    } else {

                        if (Datah.getInstance().getMenu() == 1) {
                            Toast t=Toast.makeText(getActivity(), "cambio", Toast.LENGTH_SHORT);
                            t.show();


                            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);
                            mp.start();
                        }

                    }


                }

                if (isNetworkAvailable() == false) {

                }
                new LoadAllProducts().execute();
                h.postDelayed(this, delay);

            }
        }, delay);


        btir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                // EstaMapas fragment = new EstaMapas();

                //  Bundle bundle = new Bundle();
                //  bundle.putString("lugar","polo");
                //  fragment.setArguments(bundle);
                // fragmentManager.beginTransaction().add(R.id.container, fragment, "Map1").commit();
                Log.v("detect","sepudo");

                latitude = -12.098581;
                longitude = -76.970599;

                buildGoogleApiClient();

                if(mGoogleApiClient!= null){
                    mGoogleApiClient.connect();
                }
                else {
                    Toast.makeText(getActivity(), "Not connected...", Toast.LENGTH_SHORT).show();
                }



            }
        });



        return v;
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
                        estado22=estadoalonso;
                        estado23=estadopolo;
                        estado=c.getString(TAG_NOMBRE);
                        estadoalonso=c.getString(TAG_NOMBRE2);
                        estadopolo=c.getString(TAG_NOMBRE3);

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

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}
