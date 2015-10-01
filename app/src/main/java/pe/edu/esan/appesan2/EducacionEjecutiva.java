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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by educacionadistancia on 22/09/2015.
 */
public class EducacionEjecutiva extends Fragment {
    //Declaracion de variables a usar

    //Elemento de tipo vista de web
    WebView wbEE;

    //Elemento de tipo Lista
    ListView lvEE;

    //Elemento llamado desde libreria SlideUpPanel
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se infla la vista del fragmento con el layout correspondiente
        View rootView   = inflater.inflate(R.layout.lay_educacionejecutiva, container, false);

        //Se retiene la instancia o estado del fragmento
        setRetainInstance(true);

        //Se crea y da valor al Dialogo de progreso
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //FUENTE Y COLOR PARA TEXTOS:
        String font_pathEE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(getActivity().getAssets(), font_pathEE);
        //llamamos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //Con esto se crea y obtiene los datos con referente a la pantalla del movil es decir su ancho y altura
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/8;

        //Se da valor al panel deslizable hacia arriba con el elemento que le corresponde en el layout
        sliding3 =(com.sothree.slidinguppanel.SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout3);

        //sliding2.setAnchorPoint(aa);
        sliding3.setPanelHeight(aa);

        //Se le da valor a la variable con el elemento del mismo tipo en el layout
        wbEE = (WebView)rootView.findViewById(R.id.wbEE);

        //Se le da a cargar una pagina determinada al WebView
        wbEE.loadUrl("http://www.esan.edu.pe/pade/");

        wbEE.getSettings().setUseWideViewPort(true);
        wbEE.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        wbEE.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        wbEE.getSettings().setSupportZoom(true);

        //Opcion desactivada: no muestran los botones de zoom en el webview.
        // No afecta el funcionamiento del zoom
        wbEE.getSettings().setDisplayZoomControls(false);

        //Se activa la funcionalidad de JavaScript
        wbEE.getSettings().setJavaScriptEnabled(true);

        //Carga las imagenes de la pagina automaticamente
        wbEE.getSettings().setLoadsImagesAutomatically(true);

        //Bloquea las imagenes de la pagina para que la misma cargue sin problemas por ciertas
        //paginas que no permiten una buena vista de la misma en el elemento
        wbEE.getSettings().setBlockNetworkImage(false);


        //Se da valor al elemento Lista con el elemento determinado en el layout
        lvEE = (ListView)rootView.findViewById(R.id.lvEE);

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String pade = "PADE Programa Avanzado de Dirección de Empresas";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String pae = "Programa de Alta Especialización";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String pee = "PEE Programa de Especialización para Ejecutivos";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String peed = "PEE en Derecho Corporativo";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String dip = "Diplomas";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String idi = "Programas de idiomas";

        //Se crea una cadena de texto y se le asigna su valor
        // (titulo de los elementos dentro de la lista)
        String gp = "Programas de Gestión Pública";

        //Se crea una matriz de valores de texto para la lista y se le dan los valores
        //creados anteriormente
        String[] values = new String[]{pade, pae, pee, peed, dip, idi, gp};

        //Se crea un adaptador para la lista y se le da su contexto y sus valores a usar
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        //Se asigna a la lista el adaptador creado
        lvEE.setAdapter(adapter);


        //Metodo que funciona cuando en la lista se da clic a uno de los elementos
        lvEE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    //Seguno la posicion del elemento, se comienza a contar desde 0
                    //En todos los casos cargara una pagina web determinada
                    //En todos los casos se colapsara el panel inferior para una mejor
                    //vista de la pagina con su informacion en la pantalla
                    case 0:
                        //PADE Programa Avanzado de Dirección de Empresas
                        wbEE.loadUrl("http://www.esan.edu.pe/pade/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 1:
                        //Programa de Alta Especialización
                        wbEE.loadUrl("http://www.esan.edu.pe/pae/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 2:
                        //PEE Programa de Especialización para Ejecutivos
                        wbEE.loadUrl("http://www.esan.edu.pe/pee/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 3:
                        //PEE en Derecho Corporativo
                        wbEE.loadUrl("http://www.esan.edu.pe/pee/derecho-corporativo/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 4:
                        //Diplomas
                        wbEE.loadUrl("http://www.esan.edu.pe/diplomas/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 5:
                        //Programas de idiomas
                        wbEE.loadUrl("http://www.esan.edu.pe/programas-academicos/#idiomas");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 6:
                        //Programas de Gestión Pública
                        wbEE.loadUrl("http://www.esan.edu.pe/gestion-publica/");
                        sliding3.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                }
            }
        });



        wbEE.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        //Si se puede regresar a una pagina anterior en el webview
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                //La pagina retornara a ser la anterior usada
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        wbEE.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //Metodo que funciona al comenzar la carga de la pagina web

                //El dialogo de progreso aparece
                dialog.show();

                //Se define y crea una variable de tiempo
                final Timer t = new Timer();

                //Metodo para que el dialogo de progreso tenga un tiempo determinado
                //previniendo que la pagina se pueda visualizar despues de 5 segundos
                //de ser abierta, esto con el fin de que no se demore en mostrarla puesto
                //que una conexion a internet lenta podria dejar cargando la pagina sin que
                //esta se pueda visualizar debido al dialogo de progreso mostrado
                t.schedule(new TimerTask() {
                    public void run() {

                        dialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //Metodo que funciona al terminar de cargar la pagina web


                //El dialogo de progreso desaparece
                dialog.dismiss();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Metodo que dice donde se abre la pagina web
                //Si se desea abrirla dentro de la aplicacion el metodo debera retornar falso
                //Caso contrario, si se espera que se abra en un navegador-por lo general Chrome-
                //Se debera retornar verdadero
                return false;
            }
        });

        //Retorna la vista del layout
        return rootView;
    }



}
