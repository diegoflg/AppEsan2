package pe.edu.esan.appesan2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Noticia extends Fragment {
    public ArrayList<Noticias> array_Noticias = new ArrayList<Noticias>();
    private Noticias_Adapter adapter;
    private String URL = "http://feeds.feedburner.com/mbaEsan";
    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_noticias, container, false);
        lista = (ListView) v.findViewById(R.id.noticiaslistview);
        setRetainInstance(true);
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


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Activity_Articulo fragment;
                fragment = new Activity_Articulo();

               Bundle bundle = new Bundle();
                Noticias not1=(Noticias)array_Noticias.get(arg2);
                bundle.putSerializable("parametro", not1);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "ActArt").commit();
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