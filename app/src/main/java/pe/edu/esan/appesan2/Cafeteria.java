package pe.edu.esan.appesan2;

/**
 * Se importan las siguientes clases:
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Modulo de Cafeteria , que lee la informacion almacenada en el sigiente google drive:
 * https://docs.google.com/a/ue.edu.pe/spreadsheets/d/1_tClHi6uM5g3vxv_0JkBW5Hzrt6T_Gii5Df973aX9ms
 *
 */
public class Cafeteria extends Fragment {

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<Team> teams1 = new ArrayList<Team>();//Diferentes arrays para cada tipo de menus, economico de Delisabores,
    ArrayList<Team> teams2 = new ArrayList<Team>();//economico de la Ruta
    ArrayList<Team> teams3 = new ArrayList<Team>();//ejecutivo
    ArrayList<Team> teams4 = new ArrayList<Team>();//dieta
    ArrayList<Team> teams5 = new ArrayList<Team>(); //Especial de DeliSabores
    ProgressDialog pb;//Se instancia el progress dialog

    static ListView listview;//Se instancia un listview
    static ExpandableListView expListV; //Se instancia una lista expandible
    static ImageView cafet;
    int diadelasemana=0;//Se crea un entero llamado diadelasemana con el valor de 0

    TeamsAdapter adapter1,adapter2,adapter3,adapter4, adapter5; //Se instancian los TeamsAdapter adapter1,adapter2,adapter3,adapter4

    Team team1,team2,team3,team4, team5;//Se instancian los Team team1,team2,team3,team4 y team5


   ListViewAdapter ladapter;


    List<String> titulo;
    HashMap<String, List<String>> subtitulos;


    int[] imagenes = {
            R.drawable.ruta,
            R.drawable.deli,
            R.drawable.cafeteria,
            R.drawable.cafeteria,
            R.drawable.cafeteria,
            R.drawable.cafeteria,
    };


    @Override
    public void onDestroy() {//metodo que se ejecuta cuando el fragmento se destruye
        super.onDestroy();
        pb.dismiss();//Se cancela el progress dialog
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//metodo que se ejecuta cuando el fragmento se crea
        final View v= inflater.inflate(R.layout.lay_cafeteria, container, false);//Se relaciona el View con su respectivo XML
        listview = (ListView) v.findViewById(R.id.listview);//Se relaciona listview con el listview en el xml
        cafet = (ImageView)v.findViewById(R.id.imca);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);//Se crea un windows manager llamado wm, que obtiene el valor obtenido usando los metodos getActivity().getSystemService(Context.WINDOW_SERVICE)
        Display display = wm.getDefaultDisplay();//Se crea un Display llamado display, que tendra el valor de wm.getDefaultDisplay()
        Point size = new Point();//Se crea un punto
        display.getSize(size);//Se obtiene el tamano de la pantalla del dispositivo

        expListV = (ExpandableListView) v.findViewById(R.id.exlistv2);
        prepareListData();

        ladapter = new ListViewAdapter(getActivity(), titulo, subtitulos, imagenes);
        expListV.setAdapter(ladapter);
        expListV.expandGroup(0);
        expListV.expandGroup(1);
        expListV.expandGroup(2);
        expListV.expandGroup(3);

        expListV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                listview.setVisibility(View.VISIBLE);
                                expListV.setVisibility(View.GONE);
                                cafet.setVisibility(View.GONE);
                                listar(2);

                                break;

                            case 1:
                                listview.setVisibility(View.VISIBLE);
                                expListV.setVisibility(View.GONE);
                                cafet.setVisibility(View.GONE);
                                listar(3);
                                break;

                            case 2:
                                listview.setVisibility(View.VISIBLE);
                                expListV.setVisibility(View.GONE);
                                cafet.setVisibility(View.GONE);
                                listar(4);
                                break;
                        }
                        break;

                    case 1:
                        switch (childPosition) {
                            case 0:
                                listview.setVisibility(View.VISIBLE);
                                expListV.setVisibility(View.GONE);
                                cafet.setVisibility(View.GONE);
                                listar(1);
                                break;
                            case 1:
                                Toast.makeText(getActivity(), "MENU ESPECIAL", Toast.LENGTH_SHORT).show();
                                listview.setVisibility(View.VISIBLE);
                                expListV.setVisibility(View.GONE);
                                cafet.setVisibility(View.GONE);
                                listar(5);
                                break;
                        }
                        break;

                }
                return false;
            }
        });


        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion

        DisplayMetrics displaymetrics = new DisplayMetrics();//Se instancia una clase DisplayMetrics
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);//Se ejecutan los metodos mencionados para obtener las medidas de la pantalla
        double width = displaymetrics.widthPixels;//se obtiene el ancho de la pantalla del dispositivo
        width=width*0.13;//se obtiene el 13 porciento el ancho
        int y = (int)Math.round(width);//se redondea el 13 porciento del ancho

        listview.setPadding(y, 0, 0, 0);//a el listview se le pone de pading izquierdo el 13 porciento del ancho para que el list view cuadre dentro del fondo de cafeteria(hoja.jpg)
        listview.setVisibility(View.INVISIBLE);//el list view se pone invisible, se pondra visible al apretar el boton de seleccion de menu
        cafet.setVisibility(View.VISIBLE); //La imagen inicia como visible

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);//Se  verifica la conexion a internet
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Calendar calendar = Calendar.getInstance();//Se instancia una clase Calendar

        int day = calendar.get(Calendar.DAY_OF_WEEK);//Se obtiene el dia actual para mostrar en cafeteria solo el menu del dia actual
        String dia=String.valueOf(day); //Se crea un string llamado dia

        diadelasemana=day;

        pb = new ProgressDialog(v.getContext());
        pb.setTitle("Cargando");
                        pb.setMessage("Please Wait....");
                       pb.setCancelable(false);//Se pone en false set Cancelable para que el Progress dialog no se pueda cancelar manualmente
                       pb.show();//se inicia el progressDialog






        if(isNetworkAvailable()==true){//Se verifica la conexion a internet
                        go(v);
        }else{
            Toast t=Toast.makeText(getActivity(),"No hay coneccion a internet", Toast.LENGTH_SHORT);//Se crea un Toast que muestra el  "No hay coneccion a internet"
            t.show(); //Se muestra el Tast


            pb.dismiss();//Se cancela el progres dialog


        }

        return v;
    }

    private void prepareListData(){
        //Perpara los datos de la lista

        //Crea una nueva cadena de padres
        titulo = new ArrayList<String>();

        //Crea una nueva cadena de hijos
        subtitulos = new HashMap<String, List<String>>();

        // Adding parent data
        //Añade los datos padres
        titulo.add("La Ruta");
        titulo.add("DeliSabores");
        titulo.add("Comedor detrás de finanzas");
        titulo.add("Cafetería 338");

        // Adding child data
        //Añade los datos hijos
        List<String> laruta = new ArrayList<String>();
        laruta.add("Menú Económico");
        laruta.add("Menú Ejecutivo");
        laruta.add("Menú Dieta");

        List<String> DS = new ArrayList<String>();
        DS.add("Menú Económico");
        DS.add("Menú Especial");

        List<String> Cafe26 = new ArrayList<String>();
        Cafe26.add("Cafetería #26 en mapa");


        List<String> Cafe338 = new ArrayList<String>();
        Cafe338.add("Cafetería 338");


        //Da los datos de padre con su lista de respectivos hijos a la lista expandible
        subtitulos.put(titulo.get(0), laruta); // Header, Child data
        subtitulos.put(titulo.get(1),DS);
        subtitulos.put(titulo.get(2), Cafe26);
        subtitulos.put(titulo.get(3), Cafe338);
    }

    public static void onBackPressed() {
        /**
         * Al dar click a el boton atras, el menu se esconde y vuelve a aparecer las opciones de eleccion de menu
         */
            listview.setVisibility(View.GONE);
            expListV.setVisibility(View.VISIBLE);
            cafet.setVisibility(View.VISIBLE);
    }


    public void listar(int num){//metodo que mostrara el menu en el listview

       int numero=num;

        switch (numero){

            case 1:
                listview.setAdapter(adapter1);//Si  el valor recibido es 1 se mostrara en el listview el adaptador adapter1

                break;
            case 2:
                listview.setAdapter(adapter2);//Si  el valor recibido es 2 se mostrara en el listview el adaptador adapter2

                break;
            case 3:
                listview.setAdapter(adapter3);//Si  el valor recibido es 3 se mostrara en el listview el adaptador adapter3

                break;
            case 4:
                listview.setAdapter(adapter4);//Si  el valor recibido es 4 se mostrara en el listview el adaptador adapter4

                break;

            case 5:
                listview.setAdapter(adapter5);//Si  el valor recibido es 5 se mostrara en el listview el adaptador adapter5
                break;
        }
    }

    public void go(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                processJsonME(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1_tClHi6uM5g3vxv_0JkBW5Hzrt6T_Gii5Df973aX9ms",
                    "https://spreadsheets.google.com/tq?key=1gveCHCpz9InAj0rxDpT0Z7PUdbRq4DOXKHkhs3BMWE4");
        //se descargaran los datos almacenados en el google drive, el link de arriba es un link especial con los datos del google drive listos para ser leidos con JSON
    }

    private void processJson(JSONObject object) {

        //En este metodo de leen los datos

        try {
            JSONArray columns = object.getJSONArray("rows");//Se crea un JSONArray llamado columns en donde se ingresaran las filas de el google drive


                for (int r = 1; r < 8; ++r) {
                    JSONObject column = columns.getJSONObject(r);//Se ingresan en colum las columnas del google drive de la columna 1 a la 7 que es en donde se encuentra el menu economico de delisabores
                    JSONArray rows = column.getJSONArray("c");
                    String dia = rows.getJSONObject(diadelasemana).getString("v");//Se obtienen en la variable dia las filas
                    String tipo = rows.getJSONObject(1).getString("v");//se obtiene en tipo la fila 1 del google drive

                    team1 = new Team(dia, tipo);//Se crea las variables team por cada tipo de menu
                    teams1.add(team1);//y se agregan al array teams
                }

                    for (int r = 10; r < 18; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(diadelasemana).getString("v");
                        String tipo = rows.getJSONObject(1).getString("v");

                        team2 = new Team(dia, tipo);
                        teams2.add(team2);
                    }

                    for (int r = 19; r < 25; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(diadelasemana).getString("v");
                        String tipo = rows.getJSONObject(1).getString("v");

                        team3 = new Team(dia, tipo);
                        teams3.add(team3);
                    }

                    for (int r = 26; r < 32; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(diadelasemana).getString("v");
                        String tipo = rows.getJSONObject(1).getString("v");

                        team4 = new Team(dia, tipo);
                        teams4.add(team4);
                    }



            adapter1 = new TeamsAdapter(getActivity(), R.layout.team, teams1);//Se crean los adaptadores que contienen la informacion del menu
            adapter2 = new TeamsAdapter(getActivity(), R.layout.team, teams2);
            adapter3 = new TeamsAdapter(getActivity(), R.layout.team, teams3);
            adapter4 = new TeamsAdapter(getActivity(), R.layout.team, teams4);

            pb.dismiss();//se cancela el progress Dialog porque los arrays estan listos con la informacion cargada


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void processJsonME(JSONObject object) {
        //En este metodo de leen los datos
        try {
            JSONArray columns = object.getJSONArray("rows");//Se crea un JSONArray llamado columns en donde se ingresaran las filas de el google drive
            for (int r = 1; r < 8; ++r) {
                JSONObject column = columns.getJSONObject(r);//Se ingresan en colum las columnas del google drive de la columna 1 a la 7 que es en donde se encuentra el menu economico de delisabores
                JSONArray rows = column.getJSONArray("c");
                String dia = rows.getJSONObject(diadelasemana).getString("v");//Se obtienen en la variable dia las filas
                String tipo = rows.getJSONObject(1).getString("v");//se obtiene en tipo la fila 1 del google drive

                team5 = new Team(dia, tipo);//Se crea las variables team por cada tipo de menu
                teams5.add(team5);//y se agregan al array teams
            }

            adapter5 = new TeamsAdapter(getActivity(), R.layout.team, teams5);//Se crean los adaptadores que contienen la informacion del menu
            pb.dismiss();//se cancela el progress Dialog porque los arrays estan listos con la informacion cargada

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


/**
    private void processJson(JSONObject object) {
        try {
            JSONArray columns = object.getJSONArray("rows");

            if (mTabHost.getCurrentTab()==1){

                for (int r = 1; r < 8; ++r) {
                    JSONObject column = columns.getJSONObject(r);
                    JSONArray rows = column.getJSONArray("c");
                    String dia = rows.getJSONObject(2).getString("v");
                    String entrada = rows.getJSONObject(3).getString("v");
                    String plato = rows.getJSONObject(4).getString("v");
                    String postre = rows.getJSONObject(5).getString("v");
                    String refresco = rows.getJSONObject(6).getString("v");
                    String sabado = rows.getJSONObject(7).getString("v");

                    Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                    teams.add(team);
                }
            }

            if (mTabHost.getCurrentTab()==0){

                if(rb1.isChecked()){
                        for (int r = 10; r < 18; ++r) {
                            JSONObject column = columns.getJSONObject(r);
                            JSONArray rows = column.getJSONArray("c");
                            String dia = rows.getJSONObject(2).getString("v");
                            String entrada = rows.getJSONObject(3).getString("v");
                            String plato = rows.getJSONObject(4).getString("v");
                            String postre = rows.getJSONObject(5).getString("v");
                            String refresco = rows.getJSONObject(6).getString("v");
                            String sabado = rows.getJSONObject(7).getString("v");

                            Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                            teams.add(team);
                        }
                }

                if(rb2.isChecked()){


                    for (int r = 19; r < 25; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(2).getString("v");
                        String entrada = rows.getJSONObject(3).getString("v");
                        String plato = rows.getJSONObject(4).getString("v");
                        String postre = rows.getJSONObject(5).getString("v");
                        String refresco = rows.getJSONObject(6).getString("v");
                        String sabado = rows.getJSONObject(7).getString("v");

                        Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                        teams.add(team);
                    }
                }

                if(rb3.isChecked()){
                    for (int r = 26; r < 32; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(2).getString("v");
                        String entrada = rows.getJSONObject(3).getString("v");
                        String plato = rows.getJSONObject(4).getString("v");
                        String postre = rows.getJSONObject(5).getString("v");
                        String refresco = rows.getJSONObject(6).getString("v");
                        String sabado = rows.getJSONObject(7).getString("v");

                        Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                        teams.add(team);
                    }
                }
            }
            final TeamsAdapter adapter = new TeamsAdapter(getActivity(), R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
 */

private boolean isNetworkAvailable() {
    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    /**
     * Se revisa si hay conexion a internet
     */
}

}
