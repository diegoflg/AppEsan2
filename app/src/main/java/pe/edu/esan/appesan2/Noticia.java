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
 * Modulo de noticias en donde se muestra noticias del blog del mba de esan que es:
 * http://www.esan.edu.pe/mba/
 */
public class Noticia extends Fragment {
    //Declaracion de variables

    //Lista o cadena de datos
    public ArrayList<Noticias> array_Noticias = new ArrayList<Noticias>();

    //Adaptador de la lista con la configuracion dada en la clase Noticias_Adapter
    private Noticias_Adapter adapter;

    //Cadena de texto cuyo valor es el URL de la pagina del feed
    private String URL = "http://feeds.feedburner.com/mbaEsan";// Usamos feedburner para que transforme el formato de http://www.esan.edu.pe/mba/blog/ a uno mas facil de leer

    //Elemento de tipo Lista
    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Obtiene el layout con sus elementos a utlizar
        final View v = inflater.inflate(R.layout.activity_noticias, container, false);

        //Obtiene el elemento del layout segun su tipo
        lista = (ListView) v.findViewById(R.id.noticiaslistview);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Llama al metodo del mismo nombre
        rellenarNoticias();

        return v;
    }

    private void inicializarListView() {
        //Se crea y da valor al adaptador con sus parametros siendo estos la actividad en la que se encuentra y la cadena de datos
        adapter = new Noticias_Adapter(getActivity().getApplicationContext(), array_Noticias);

        //Se le asigna al elemento Lista su adaptador
        lista.setAdapter(adapter);

        //Metodo que se da al dar clic a un dato de la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                //Se crea un adaptador para un fragmento nuevo
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                //Se crea un fragmento para visualizar el articulo
                Activity_Articulo fragment;
                fragment = new Activity_Articulo();

                //Se crea un paquete de datos
               Bundle bundle = new Bundle();

                //Se obtiene un dato de la cadena
                Noticias not1=(Noticias)array_Noticias.get(arg2);

                //Se guarda el dato anterior en el paquete de datos
                bundle.putSerializable("parametro", not1);

                //Se envia el paquete de datos al fragmento
                fragment.setArguments(bundle);

                //Se inicia un nuevo fragmento que reemplaza al actual, siendo el articulo escogido de la lista
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "ActArt").commit();
            }
        });
    }

    private void rellenarNoticias() {
        //Si es verdadero el metodo entonces entra
        if (isOnline()) {
            //Llama a la clase enviando los datos requeridos por esta
            //que son el fragemnto o contexto actual y el URL de la pagina
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
            //Metodo hecho en segundo plano

            //Crea una variable con la configuracion de la clase XMLParser
            XMLParser parser = new XMLParser(feedUrl, getActivity().getBaseContext());
            array_Noticias = parser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            //Metodo que ocurre despues de ser ejecutada la accion
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
        //Obtiene verdadero o falso segun sea la conexion a internet
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

}