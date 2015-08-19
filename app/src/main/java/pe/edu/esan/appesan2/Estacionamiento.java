package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.android.gms.internal.zzhl.runOnUiThread;


/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Estacionamiento extends Fragment {

    private ProgressDialog pDialog;






    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> empresaList;


    // url to get all products list
    private static String url_all_empresas = "http://basededatosestacionamiento.16mb.com/esconnect/get_all_empresas.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_ID = "id";
    private static final String TAG_NOMBRE = "username";

    private ImageView sema1e,sema2e,sema3e;
    private String estado="waa";

    // products JSONArray
    JSONArray products = null;

    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estacionamiento, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        sema1e=(ImageView)v.findViewById(R.id.sema1e);
        sema2e=(ImageView)v.findViewById(R.id.sema2e);
        sema3e=(ImageView)v.findViewById(R.id.sema3e);


        final Handler h = new Handler();
        final int delay = 10000; //milliseconds


            // Hashmap para el ListView
            empresaList = new ArrayList<HashMap<String, String>>();

            // Cargar los productos en el Background Thread

            lista = (ListView) v.findViewById(R.id.listAllProducts);


        h.postDelayed(new Runnable() {
            public void run() {
                Log.v("tipo", "timer");

                if(estado.equals("rojo")){
                    sema1e.setImageResource(R.drawable.rojoprendido);
                    sema2e.setImageResource(R.drawable.amarilloapagado);
                    sema3e.setImageResource(R.drawable.verdeapagado);

                }
                if(estado.equals("amarillo")){
                    sema1e.setImageResource(R.drawable.rojoapagado);
                    sema2e.setImageResource(R.drawable.amarilloprendido);
                    sema3e.setImageResource(R.drawable.verdeapagado);

                }
                if(estado.equals("verde")){
                    sema1e.setImageResource(R.drawable.rojoapagado);
                    sema2e.setImageResource(R.drawable.amarilloapagado);
                    sema3e.setImageResource(R.drawable.verdeprendido);

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

        /**
         * obteniendo todos los productos
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List params = new ArrayList();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    //Log.i("ramiro", "produtos.length" + products.length());
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NOMBRE);
                        estado=c.getString(TAG_NOMBRE);

                        // creating new HashMap
                        HashMap map = new HashMap();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_NOMBRE, name);

                        empresaList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {


        }
    }

}




