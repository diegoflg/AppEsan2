package pe.edu.esan.appesan2;

/**
 * Se importan las siguientes clases:
 *
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;

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
    ProgressDialog pb;//Se instancia el progress dialog


    RelativeLayout lay1;//Se instancia un Relative layout

    static ListView listview;//Se instancia un listview
    TabHost mTabHost;//Se instancia el mTabHost
    static Button bc1;//Se instancia el boton bc1
    static Button bc2;//Se instancia el boton bc2
    static Button bc3;//Se instancia el boton bc3
    static Button bc4;//Se instancia el boton bc4
    static Button bc5;//Se instancia el boton bc5
    int num=0;//Se crea un entero llamado num con el valor de 0
    int diadelasemana=0;//Se crea un entero llamado diadelasemana con el valor de 0
    static TextView tv1;//Se instancia un TextView llamado tv1
    static TextView tv2;//Se instancia un TextView llamado tv2
    static TextView tv3;//Se instancia un TextView llamado tv3
    static TextView tv4;//Se instancia un TextView llamado tv4
    static TextView tv5;//Se instancia un TextView llamado tv5
    static TextView tv6;//Se instancia un TextView llamado tv6
    static TextView tv7;//Se instancia un TextView llamado tv7
    static TextView tv8;//Se instancia un TextView llamado tv8
    static TextView tv9;//Se instancia un TextView llamado tv9

    static ImageView cafeteria11;//Se instancia un ImageView llamado cafeteria11

    TeamsAdapter adapter1,adapter2,adapter3,adapter4;//Se instancian los TeamsAdapter adapter1,adapter2,adapter3,adapter4

    Team team1,team2,team3,team4;//Se instancian los Team team1,team2,team3,team4


    @Override
    public void onDestroy() {//metodo que se ejecuta cuando el fragmento se destruye
        super.onDestroy();
        pb.dismiss();//Se cancela el progress dialog


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//metodo que se ejecuta cuando el fragmento se crea
        final View v= inflater.inflate(R.layout.lay_cafeteria, container, false);//Se relaciona el View con su respectivo XML
        listview = (ListView) v.findViewById(R.id.listview);//Se relaciona listview con el listview en el xml


        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion

        lay1=(RelativeLayout)v.findViewById(R.id.LaRuta);//Se relaciona el RelativeLayout con el Layout del xml

        //FUENTE Y COLOR PARA CUALQUIER OBJETO DENTRO DE CUALQUIER TABHOST:
        String font_pathL = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFL = Typeface.createFromAsset(getActivity().getAssets(),font_pathL);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        bc1=(Button)v.findViewById(R.id.ec);//Se relaciona el boton bc1 con el boton ec del respectivo XML
        bc1.setTypeface(TFL);
        bc1.setTextColor(Color.parseColor("#FFFFFF"));//Se cambia el color del texto del bc1 a #FFFFFF
        bc2=(Button)v.findViewById(R.id.ej);//Se relaciona el boton bc2 con el boton ej del respectivo XML
        bc2.setTypeface(TFL);//El bc2 sera de tipo HelveticaNeue-Light
        bc2.setTextColor(Color.parseColor("#FFFFFF")); //Se cambia el color del texto del bc2 a #FFFFFF
        bc3=(Button)v.findViewById(R.id.di);//Se relaciona el boton bc3 con el boton di del respectivo XML
        bc3.setTypeface(TFL);
        bc3.setTextColor(Color.parseColor("#FFFFFF"));//Se cambia el color del texto del bc3 a #FFFFFF

        bc4=(Button)v.findViewById(R.id.ec2);
        bc4.setTypeface(TFL);//El bc4 sera de tipo HelveticaNeue-Light
        bc4.setTextColor(Color.parseColor("#FFFFFF"));//Se cambia el color del texto del bc4 a #FFFFFF
        bc5=(Button)v.findViewById(R.id.es);
        bc5.setTypeface(TFL);//El bc5 sera de tipo HelveticaNeue-Light
        bc5.setTextColor(Color.parseColor("#FFFFFF"));//Se cambia el color del texto del bc5 a #FFFFFF

        tv1=(TextView)v.findViewById(R.id.tvcaf1);
        tv1.setTypeface(TFL);//El tv1 sera de tipo HelveticaNeue-Light
        tv1.setTextColor(Color.parseColor("#1A171B"));//Se cambia el color del texto del tv1 a #1A171B
        tv2=(TextView)v.findViewById(R.id.precio);
        tv2.setTypeface(TFL);
        tv2.setTextColor(Color.parseColor("#1A171B"));//Se cambia el color del texto del tv2 a #1A171B
        tv3=(TextView)v.findViewById(R.id.precio1);
        tv3.setTypeface(TFL);
        tv3.setTextColor(Color.parseColor("#1A171B"));
        tv4=(TextView)v.findViewById(R.id.precio2);
        tv4.setTypeface(TFL);
        tv4.setTextColor(Color.parseColor("#1A171B"));
        tv5=(TextView)v.findViewById(R.id.precio3);
        tv5.setTypeface(TFL);
        tv5.setTextColor(Color.parseColor("#1A171B"));

        tv6=(TextView)v.findViewById(R.id.tvcaf2);
        tv6.setTypeface(TFL);
        tv6.setTextColor(Color.parseColor("#1A171B"));
        tv7=(TextView)v.findViewById(R.id.textViewd);
        tv7.setTypeface(TFL);
        tv7.setTextColor(Color.parseColor("#1A171B"));
        tv8=(TextView)v.findViewById(R.id.preciod1);
        tv8.setTypeface(TFL);
        tv8.setTextColor(Color.parseColor("#1A171B"));
        tv9=(TextView)v.findViewById(R.id.preciod2);
        tv9.setTypeface(TFL);
        tv9.setTextColor(Color.parseColor("#1A171B"));
        cafeteria11=(ImageView)v.findViewById(R.id.imca);



        DisplayMetrics displaymetrics = new DisplayMetrics();//Se instancia una clase DisplayMetrics
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);//Se ejecutan los metodos mencionados para obtener las medidas de la pantalla
        int height = displaymetrics.heightPixels;
        double width = displaymetrics.widthPixels;//se obtiene el ancho de la pantalla del dispositivo
        width=width*0.13;//se obtiene el 13 porciento el ancho
        int y = (int)Math.round(width);//se redondea el 13 porciento del ancho

        listview.setPadding(y, 0, 0, 0);//a el listview se le pone de pading izquierdo el 13 porciento del ancho para que el list view cuadre dentro del fondo de cafeteria(hoja.jpg)
        listview.setVisibility(View.INVISIBLE);//el list view se pone invisible, se pondra visible al apretar el boton de seleccion de menu




        mTabHost = (TabHost) v.findViewById(R.id.tabHost2);//Se relaciona mTabHost con tabHost2 de su respectivo XML
        mTabHost.setup();//Se ejecuta el metodo setup() del mTabHost

        TabHost.TabSpec spec = mTabHost.newTabSpec("Tab 1");//Se definine el TabSpect
        spec.setContent(R.id.LaRuta);//Se relaciona el spect con el Content del xml con id LaRuta
        spec.setIndicator("La ruta");//Se pone el nombre la ruta en uno de los tabHost
        mTabHost.addTab(spec);//Se insterta el spec al tabHost

        spec=mTabHost.newTabSpec("Tab 2");//Se define otro spec para Delisabores
        spec.setContent(R.id.DeliSabores);
        spec.setIndicator("Delisabores");
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);//El tabHost inicial sera el de la Ruta

        //FUENTE PARA TÍTULO EN TABHOST:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)//For que sirve para cambiaar el color de los tabs y fuentes de las letras
        {
            mTabHost.getTabWidget().setStripEnabled(true);

            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));//Se cambia el texto tv a el color #FFFFFF
            tv.setTypeface(TFT);//El tv sera de tipo HelveticaNeue-Light
        }
        mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#C90039")); //Se cambia de color de fondo a el hijo 0  del tab a #C90039
        mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#C90039")); //Se cambia de color de fondo a el hijo 1  del tab a #C90039

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






        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override//metodo override que detecta cuando hay algun cambio de tabhost
            public void onTabChanged(String tabId) {

                /**
                 * Al cambiar de tab el menu se esconde y vuelve a aparecer las opciones de eleccion de menu
                 *
                 */

                bc1.setVisibility(View.VISIBLE);//aparece bc1
                bc2.setVisibility(View.VISIBLE);//aparece bc2
                bc3.setVisibility(View.VISIBLE);//aparece bc3
                bc5.setVisibility(View.VISIBLE);//aparece bc4
                bc4.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);//desaparece listview
                tv1.setVisibility(View.VISIBLE);//aparece tv1
                tv2.setVisibility(View.VISIBLE);//aparece tv2
                tv3.setVisibility(View.VISIBLE);//aparece tv3
                tv4.setVisibility(View.VISIBLE);
                tv5.setVisibility(View.VISIBLE);
                tv6.setVisibility(View.VISIBLE);
                tv7.setVisibility(View.VISIBLE);
                tv8.setVisibility(View.VISIBLE);
                tv9.setVisibility(View.VISIBLE);
                cafeteria11.setVisibility(View.VISIBLE);



            }
        });

//economico
        bc1.setOnClickListener(new View.OnClickListener() {
            @Override//metodo override que detecta si se ha dado click a bc1
            public void onClick(View v) {

                bc1.setVisibility(View.GONE);//desaparece bc1
                bc2.setVisibility(View.GONE);
                bc3.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);//aparece listview
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                cafeteria11.setVisibility(View.GONE);

                listar(2);//Al clickear el bc1 se ejecua el metodo listar con el numero 2 que representa el menu qe se mostrara



            }
        });
//ejeecutio
        bc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bc1.setVisibility(View.GONE);
                bc2.setVisibility(View.GONE);
                bc3.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                cafeteria11.setVisibility(View.GONE);
                listar(3);


            }
        });

//dieta
        bc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bc1.setVisibility(View.GONE);
                bc2.setVisibility(View.GONE);
                bc3.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                cafeteria11.setVisibility(View.GONE);

                listar(4);



            }
        });

        bc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bc4.setVisibility(View.GONE);
                bc5.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                tv6.setVisibility(View.GONE);
                tv7.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv9.setVisibility(View.GONE);
                cafeteria11.setVisibility(View.GONE);

                listar(1);


            }
        });


        bc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });


        return v;
    }


    public static void onBackPressed() {

        /**
         * Al dar click a el boton atras, el menu se esconde y vuelve a aparecer las opciones de eleccion de menu
         *
         */



        bc1.setVisibility(View.VISIBLE);
        bc2.setVisibility(View.VISIBLE);
        bc3.setVisibility(View.VISIBLE);
        bc5.setVisibility(View.VISIBLE);
        bc4.setVisibility(View.VISIBLE);
        listview.setVisibility(View.GONE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);
        tv5.setVisibility(View.VISIBLE);
        tv6.setVisibility(View.VISIBLE);
        tv7.setVisibility(View.VISIBLE);
        tv8.setVisibility(View.VISIBLE);
        tv9.setVisibility(View.VISIBLE);
        cafeteria11.setVisibility(View.VISIBLE);



    }


    public void listar(int num){//metodo que mostrara el menu en el listview

       int numero=num;

        switch (numero){

            case 1:
                listview.setAdapter(adapter1);

                break;
            case 2:
                listview.setAdapter(adapter2);

                break;
            case 3:
                listview.setAdapter(adapter3);

                break;
            case 4:
                listview.setAdapter(adapter4);

                break;


        }
    }






    public void go(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1_tClHi6uM5g3vxv_0JkBW5Hzrt6T_Gii5Df973aX9ms");
        //se descargaran los datos almacenados en el google drive, el link de arriba es un link especial con los datos del google drive listos para ser leidos con JSON
    }


    private void processJson(JSONObject object) {

        //En este metodo de leen los datos

        try {
            JSONArray columns = object.getJSONArray("rows");


                for (int r = 1; r < 8; ++r) {
                    JSONObject column = columns.getJSONObject(r);
                    JSONArray rows = column.getJSONArray("c");
                    String dia = rows.getJSONObject(diadelasemana).getString("v");
                    String tipo = rows.getJSONObject(1).getString("v");

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



            adapter1 = new TeamsAdapter(getActivity(), R.layout.team, teams1);//Se crean los adaptadores
            adapter2 = new TeamsAdapter(getActivity(), R.layout.team, teams2);
            adapter3 = new TeamsAdapter(getActivity(), R.layout.team, teams3);
            adapter4 = new TeamsAdapter(getActivity(), R.layout.team, teams4);

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
     *
     */
}











}
