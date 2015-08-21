package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Fablab extends Fragment {


    public ArrayList<Noticias> array_Noticias = new ArrayList<Noticias>();
    private Noticias_Adapter adapter;

    //private String URL = "https://geekytheory.com/feed/";
    //private String URL = "http://feeds.feedburner.com/ElMbaQueTeDiferencia";
    private String URL = "http://feeds.feedburner.com/mbaEsan";
    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_noticias, container, false);
        lista = (ListView) v.findViewById(R.id.noticiaslistview);


        rellenarNoticias();
        return v;

    }

    private void inicializarListView() {

        adapter = new Noticias_Adapter(getActivity().getApplicationContext(), array_Noticias);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(getActivity(), Activity_Articulo.class);
                intent.putExtra("parametro", array_Noticias.get(arg2));
                //intent.putExtra("parametro", "Artículo número "+(arg2+1));
                startActivity(intent);
            }
        });
    }

    private void rellenarNoticias() {
        if (isOnline()) {
            new DescargarNoticias(getActivity().getBaseContext(), URL).execute();
        }

    }

    private class DescargarNoticias extends AsyncTask<String, Void, Boolean> {

        private String feedUrl;
        private Context ctx;

        public DescargarNoticias(Context c, String url) {
            this.feedUrl = url;
            this.ctx = c;
        }

        @Override
        protected Boolean doInBackground(final String... args) {
            XMLParser parser = new XMLParser(feedUrl, getActivity().getBaseContext());
            array_Noticias = parser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                try {
                    inicializarListView();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(ctx, "Error en la lectura", Toast.LENGTH_LONG)
                        .show();
            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

}