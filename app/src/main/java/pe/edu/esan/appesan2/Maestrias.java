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
 * Created by educacionadistancia on 24/07/2015.
 */
public class Maestrias extends Fragment {
    WebView wbM;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding2;
    TextView titulo;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        wbM.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wbM.restoreState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.lay_maestrias, container, false);
        setRetainInstance(true);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //FUENTE Y COLOR PARA TEXTOS:
        String font_pathEE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(getActivity().getAssets(), font_pathEE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        titulo = (TextView)rootView.findViewById(R.id.titulo);
        titulo.setTypeface(TFEE);



        wbM = (WebView)rootView.findViewById(R.id.wbM);
        wbM.loadUrl("http://www.esan.edu.pe/mba/tiempo-completo/");
        wbM.getSettings().setUseWideViewPort(true);
        wbM.getSettings().setLoadWithOverviewMode(true);
        wbM.getSettings().setBuiltInZoomControls(true);
        wbM.getSettings().setSupportZoom(true);
        wbM.getSettings().setDisplayZoomControls(false);
        wbM.getSettings().setJavaScriptEnabled(true);
        wbM.getSettings().setLoadsImagesAutomatically(true);
        wbM.getSettings().setBlockNetworkImage(true);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/8;

        sliding2 =(com.sothree.slidinguppanel.SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout2);
        //sliding2.setAnchorPoint(aa);
        sliding2.setPanelHeight(aa);


        //CÓDIGO FUENTE PARA EL EXANDABLE LIST VIEW DE: http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                switch (groupPosition) {
                    case 0:
                        //MBA
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
                dialog.show();

                final Timer t = new Timer();

                t.schedule(new TimerTask() {
                    public void run() {

                        dialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        return rootView;
    }

    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("MBA Maestría en Administración");
        listDataHeader.add("MSc Maestrías Especializadas");

        // Adding child data
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

        listDataChild.put(listDataHeader.get(0), MBA); // Header, Child data
        listDataChild.put(listDataHeader.get(1), MSc);
    }


}
