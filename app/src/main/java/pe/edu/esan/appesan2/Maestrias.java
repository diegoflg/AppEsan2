package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *Modulo para mostrar en una lista las maestrias de esan
 * junto a un webview que muestra la pagina de la maestria escogida
 */
public class Maestrias extends Fragment {
    //Declaracion de variables

    //Elemento que permite ver una pagina web
    WebView wbM;

    //Adaptador del Expandable List View
    ExpandableListAdapter listAdapter;

    //Vista de Lista expandible
    ExpandableListView expListView;

    //Datos padres de la lista
    List<String> listDataHeader;

    //Datos hijos de la lista
    HashMap<String, List<String>> listDataChild;

    //SlidingUpPanel
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding2;

    //Cuadro de texto para el titulo del SllindUpPanel
    TextView titulo;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Guarda es estado actual de fragmento
        super.onSaveInstanceState(outState);

        //Guarda el estado actual de la vista web
        wbM.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Restaura el estado del fragmento
        super.onActivityCreated(savedInstanceState);

        //Restaura el estado de la vista web
        wbM.restoreState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Infla la vista con el layout correspondiente
        View rootView   = inflater.inflate(R.layout.lay_maestrias, container, false);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Se declara y da valor a un dialogo de progreso
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //FUENTE Y COLOR PARA TEXTOS:
        String font_pathEE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(getActivity().getAssets(), font_pathEE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //Se le da valor al cuadro de texto llamando al elemento correspondiente del layout
        titulo = (TextView)rootView.findViewById(R.id.titulo);

        //Se le asigna la fuente al texto dentro del cuadro de texto
        titulo.setTypeface(TFEE);


        //Se da el valor a la vista web segun el elemento dentro del layout
        wbM = (WebView)rootView.findViewById(R.id.wbM);

        //Carga la pagina web segun la cadena de texto dado
        wbM.loadUrl("http://www.esan.edu.pe/mba/tiempo-completo/");

        wbM.getSettings().setUseWideViewPort(true);
        wbM.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        wbM.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        wbM.getSettings().setSupportZoom(true);

        //Opcion desactivada: no muestran los botones de zoom en el webview. No afecta el funcionamiento del zoom
        wbM.getSettings().setDisplayZoomControls(false);

        //Activa el uso de JavaScript
        wbM.getSettings().setJavaScriptEnabled(true);

        //Carga las imagenes de la pagina web automaticamente
        wbM.getSettings().setLoadsImagesAutomatically(true);

        //Bloquea la carga de imagenes de la pagina web
        wbM.getSettings().setBlockNetworkImage(true);

        //Se obtiene el tamaño de la pantalla del celular
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/8;

        //Se da valor al sliding con el elemento del layout
        sliding2 =(com.sothree.slidinguppanel.SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout2);

        //sliding2.setAnchorPoint(aa);

        sliding2.setPanelHeight(aa);


        //CÓDIGO FUENTE PARA EL EXANDABLE LIST VIEW DE: http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        //Crea la lista expandible en el fragmento actual con los datos padre e hijos
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //la lista expandible es clickeada
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();

                //segun la posicion del padre
                switch (groupPosition) {
                    case 0:
                        //MBA
                        //segun la posicion del hijo
                        switch (childPosition) {
                            case 0:
                                //MBA Tiempo Completo
                                wbM.loadUrl("http://www.esan.edu.pe/mba/tiempo-completo/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 1:
                                //MBA Tiempo Parcial
                                wbM.loadUrl("http://www.esan.edu.pe/mba/tiempo-parcial/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 2:
                                //MBA TP Weekends
                                wbM.loadUrl("http://www.esan.edu.pe/mba/weekends/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 3:
                                //International MBA
                                wbM.loadUrl("http://www.esan.edu.pe/mba/international/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                        }
                        break;

                    case 1:
                        //MSc
                        //segun la posicion del hijo
                        switch (childPosition) {
                            //MAESTRÍAS ESPECIALIZADAS CON CONCENTRACIÓN EN NEGOCIOS
                            case 0:
                                //Dirección de Tecnologías de Información
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/direccion-de-tecnologias-de-informacion/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 1:
                                //Finanzas
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/finanzas/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 2:
                                //Finanzas y Derecho Corporativo
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/finanzas-y-derecho-corporativo/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 3:
                                //Marketing
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/marketing/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 4:
                                //Organizacion y Dirección de Personas
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/organizacion-y-direccion-de-personas/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 5:
                                //Supply Chain Management
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/supply-chain-management/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 6:
                                //Gestión Empresarial
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/gestion-empresarial/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;

                            //MAESTRIÁS DE ALTA ESPECIALIZACIÓN
                            case 7:
                                //Project Management
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/project-management/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 8:
                                //Gestion de la Energía
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/gestion-de-la-energia/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 9:
                                //Gerencia de Servicios de Salud
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/gerencia-de-servicios-de-salud/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 10:
                                //Gestión y Desarrollo Inmobiliario
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/gestion-y-desarrollo-inmobiliario/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 11:
                                //Administración de Agronegocios
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/administracion-de-agronegocios/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                            case 12:
                                //Gestión Pública
                                wbM.loadUrl("http://www.esan.edu.pe/maestrias/gestion-publica/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;

                            //MAESTRÍAS EN INVESTIGACIÓN - 1ER PASO AL PROGRAMA DOCTORAL
                            case 13:
                                //Investigación en Ciencias de la Administración
                                wbM.loadUrl("http://www.esan.edu.pe/doctorado/structure/");
                                sliding2.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                break;
                        }
                        break;
                }
                return false;
            }
        });



        wbM.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            //si el webview puede regresar de pagina regresara
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        wbM.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //cuando la pagina comienza a cargar

                //muestra el dialogo de progreso
                dialog.show();

                //declara y crea una variable de tiempo
                final Timer t = new Timer();

                //actividad que se realiza con la variable del tiempo
                t.schedule(new TimerTask() {
                    public void run() {

                        dialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //Metodo que ocurre cuando termina de cargar la pagina web

                //el dialogo de progreso desaparece
                dialog.dismiss();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Este metodo sirve para abrir la pagina web dentro o fuera de la aplicacion
                //Si retorna verdadero la pagina se abrira fuera de la aplicacion en un navegador
                //Si retorna falso la pagina se abrira dentro de la aplicacion
                return false;
            }
        });
        return rootView;
    }

    private void prepareListData(){
        //Perpara los datos de la lista

        //Crea una nueva cadena de padres
        listDataHeader = new ArrayList<String>();

        //Crea una nueva cadena de hijos
        listDataChild = new HashMap<String, List<String>>();

        // Adding parent data
        //Añade los datos padres
        listDataHeader.add("MBA Maestría en Administración");
        listDataHeader.add("MSc Maestrías Especializadas");

        // Adding child data
        //Añade los datos hijos
        List<String> MBA = new ArrayList<String>();
        MBA.add("MBA Tiempo Completo");
        MBA.add("MBA Tiempo Parcial");
        MBA.add("MBA TP Weekends");
        MBA.add("International MBA");

        List<String> MSc = new ArrayList<String>();
        //Maestrías especializadas con concentración en negocios
        MSc.add("Dirección de Tecnologías de Información");
        MSc.add("Finanzas");
        MSc.add("Finanzas y Derecho Corporativo");
        MSc.add("Marketing");
        MSc.add("Organizacion y Dirección de Personas");
        MSc.add("Supply Chain Management");
        MSc.add("Gestión Empresarial");
        //Maestrías de Alta Especialización
        MSc.add("Project Management");
        MSc.add("Gestion de la Energía");
        MSc.add("Gerencia de Servicios de Salud");
        MSc.add("Gestión y Desarrollo Inmobiliario");
        MSc.add("Administración de Agronegocios");
        MSc.add("Gestión Pública");
        //Maestrías en ingestigación - 1er paso al programa doctoral
        MSc.add("Investigación en Ciencias de la Administración");

        //Da los datos de padre con su lista de respectivos hijos a la lista expandible
        listDataChild.put(listDataHeader.get(0), MBA); // Header, Child data
        listDataChild.put(listDataHeader.get(1), MSc);
    }


}
