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
 * Created by Diegoflg on 8/17/2015.
 */
public class Registroesta extends Fragment {

    private ImageView sem1,sem2,sem3;
    private String libres;
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
        sem1=(ImageView)v.findViewById(R.id.sema1);
        sem2=(ImageView)v.findViewById(R.id.sema2);
        sem3=(ImageView)v.findViewById(R.id.sema3);

        new LoadAllProducts().execute();



        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoprendido);
                    sem2.setImageResource(R.drawable.amarilloapagado);
                    sem3.setImageResource(R.drawable.verdeapagado);
                    libres="rojo";
                    new CreateUser().execute();

                }else{
                    Toast.makeText(getActivity(), "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }



            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoapagado);
                    sem2.setImageResource(R.drawable.amarilloprendido);
                    sem3.setImageResource(R.drawable.verdeapagado);
                    libres="amarillo";
                    new CreateUser().execute();
                }else{
                    Toast.makeText(getActivity(), "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }


            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()==true){
                    sem1.setImageResource(R.drawable.rojoapagado);
                    sem2.setImageResource(R.drawable.amarilloapagado);
                    sem3.setImageResource(R.drawable.verdeprendido);
                    libres="verde";
                    new CreateUser().execute();
                }else{
                    Toast.makeText(getActivity(), "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                    Log.d("internet", "no hay");
                }


            }
        });



        return v;

    }



    class CreateUser extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {



            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
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
            pDialog.dismiss();

            if(estado.equals("rojo")){
                sem1.setImageResource(R.drawable.rojoprendido);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeapagado);

            }
            if(estado.equals("amarillo")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloprendido);
                sem3.setImageResource(R.drawable.verdeapagado);

            }
            if(estado.equals("verde")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeprendido);

            }

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }








}
