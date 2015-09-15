package pe.edu.esan.appesan2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;

import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Estacionamiento extends Fragment {

    private static String url_all_empresas = "http://basededatosestacionamiento.16mb.com/esconnect/get_all_empresas.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private ImageView sema1e,sema2e,sema3e;
    private String estado="waa";
    JSONArray products = null;
    JSONParser jParser = new JSONParser();
    TextView tvlibres;
    String estado2;

    //PARA FUENTE:
    TextView textViewestareg;
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estacionamiento, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setRetainInstance(true);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/3;

        sliding=(com.sothree.slidinguppanel.SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout);
        sliding.setAnchorPoint(0.3f);

        final MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.hifi);

        //FUENTE Y COLOR PARA TEXTVIEWS
        String font_pathE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFE = Typeface.createFromAsset(getActivity().getAssets(), font_pathE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        textViewestareg = (TextView)v.findViewById(R.id.textViewestareg);
        textViewestareg.setTypeface(TFE);

        String font_pathL = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFL = Typeface.createFromAsset(getActivity().getAssets(), font_pathL);
        tvlibres=(TextView)v.findViewById(R.id.textlibres);
        tvlibres.setTypeface(TFL);

        sema1e=(ImageView)v.findViewById(R.id.sema1e);
        sema2e=(ImageView)v.findViewById(R.id.sema2e);
        sema3e=(ImageView)v.findViewById(R.id.sema3e);


        final Handler h = new Handler();
        final int delay = 5000; //milliseconds


        new LoadAllProducts().execute();
        h.postDelayed(new Runnable() {
            public void run() {
                Log.v("tipo", "timer");
                Log.v("es", estado);
                //Log.v("es2", estado2);

                if(estado.equals("rojo")){
                    sema1e.setImageResource(R.drawable.rojoprendido);
                    sema2e.setImageResource(R.drawable.amarilloapagado);
                    sema3e.setImageResource(R.drawable.verdeapagado);
                    tvlibres.setText("Playa llena");

                    new BottomSheet.Builder(getActivity()).title("title").sheet(R.menu.menu_bottom_sheet).listener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

                    if(estado.equals(estado2)){


                    }else{
                        mp.start();
                    }

                }
                if(estado.equals("amarillo")){
                    sema1e.setImageResource(R.drawable.rojoapagado);
                    sema2e.setImageResource(R.drawable.amarilloprendido);
                    sema3e.setImageResource(R.drawable.verdeapagado);
                    tvlibres.setText("De 4 a 20 estacionamientos disponibles");
                    if(estado.equals(estado2)){


                    }else{
                        mp.start();
                    }



                }
                if(estado.equals("verde")){
                    sema1e.setImageResource(R.drawable.rojoapagado);
                    sema2e.setImageResource(R.drawable.amarilloapagado);
                    sema3e.setImageResource(R.drawable.verdeprendido);
                    tvlibres.setText("Playa libre");
                    if(estado.equals(estado2)){


                    }else{
                        mp.start();
                    }



                }

                if(isNetworkAvailable()==false){
                    sema1e.setImageResource(R.drawable.rojoapagado);
                    sema2e.setImageResource(R.drawable.amarilloapagado);
                    sema3e.setImageResource(R.drawable.verdeapagado);
                    tvlibres.setText("No hay coneccion a internet");
                }
                new LoadAllProducts().execute();
                h.postDelayed(this, delay);
            }
        }, delay);


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

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}




