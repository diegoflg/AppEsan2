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
    WebView wbEE;
    ListView lvEE;
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.lay_educacionejecutiva, container, false);
        setRetainInstance(true);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //FUENTE Y COLOR PARA TEXTOS:
        String font_pathEE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(getActivity().getAssets(), font_pathEE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/8;

        sliding3 =(com.sothree.slidinguppanel.SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout3);
        //sliding2.setAnchorPoint(aa);
        sliding3.setPanelHeight(aa);

        wbEE = (WebView)rootView.findViewById(R.id.wbEE);
        wbEE.loadUrl("http://www.esan.edu.pe/pade/");
        wbEE.getSettings().setUseWideViewPort(true);
        wbEE.getSettings().setLoadWithOverviewMode(true);
        wbEE.getSettings().setBuiltInZoomControls(true);
        wbEE.getSettings().setSupportZoom(true);
        wbEE.getSettings().setDisplayZoomControls(false);
        wbEE.getSettings().setJavaScriptEnabled(true);
        wbEE.getSettings().setLoadsImagesAutomatically(true);
        wbEE.getSettings().setBlockNetworkImage(false);

        lvEE = (ListView)rootView.findViewById(R.id.lvEE);

        String pade = "PADE Programa Avanzado de Dirección de Empresas";
        String pae = "Programa de Alta Especialización";
        String pee = "PEE Programa de Especialización para Ejecutivos";
        String peed = "PEE en Derecho Corporativo";
        String dip = "Diplomas";
        String idi = "Programas de idiomas";
        String gp = "Programas de Gestión Pública";
        String[] values = new String[]{pade, pae, pee, peed, dip, idi, gp};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        lvEE.setAdapter(adapter);


        lvEE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
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


        wbEE.setWebViewClient(new WebViewClient() {
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



}
