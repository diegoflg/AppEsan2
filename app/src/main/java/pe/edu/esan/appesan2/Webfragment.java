package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragmento en el que se muestran los enlaces de los cursos MOOC con un WebView
 */
public class Webfragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se determina la vista del fragmento, es decir se da el valor de su layout
        View rootView = inflater.inflate(R.layout.webfragment, container, false);

        Bundle bundle = this.getArguments();//Recibe un paquete del fragmento CursosMOOC
        String link = bundle.getString("url");//De ese paquete obtiene el link que se abrira en el webView

        //Se crea una variable y se le da su valor respectivo segun su layout
        final WebView wb = (WebView) rootView.findViewById(R.id.webfragment);

        //Se crea un dialogo de progreso y se le da ciertos valores como donde aparecera y su contenido
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //Se crea una variable de tiempo
        final Timer t = new Timer();

        //Se carga el link
        wb.loadUrl(link);

        wb.getSettings().setUseWideViewPort(true);

        wb.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        wb.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        wb.getSettings().setSupportZoom(true);

        //Opcion desactivada: no muestran los botones de zoom en el webview. No afecta el funcionamiento del zoom
        wb.getSettings().setDisplayZoomControls(false);

        //Se permite el uso de java script
        wb.getSettings().setJavaScriptEnabled(true);

        //Se da la opcion de que las imagenes que contiene la pagina se carguen automaticamente
        wb.getSettings().setLoadsImagesAutomatically(true);

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();

        wb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                                //Si el webView puede retroceder al presionar atras retrocedera a la pagina anterior
                            } else {
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                CursosMooc fragment;
                                fragment = new CursosMooc();
                                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                                //Si el webView no puede retroceder se volvera al fragmento de CursosMooc
                            }
                            break;
                    }
                }
                return false;
            }
        });

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    String webUrl = wb.getUrl();
                    Log.v("link", "webUrl");

                    if (webUrl.equals("https://www.edx.org/")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

                        //Si presionas atras estando en la direccion https://www.edx.org/, volveras a el fragmento Cursos Mooc
                    }

                    if (webUrl.equals("https://es.coursera.org/")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

                        //Si presionas atras estando en la direccion https://es.coursera.org/, volveras a el fragmento Cursos Mooc
                    }

                    if (webUrl.equals("https://miriadax.net/web/publicidad-en-linea.-campanas-en-facebook-y-adwords")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

                        //Si presionas atras estando en la direccion https://miriadax.net/web/publicidad-en-linea.-campanas-en-facebook-y-adwords, volveras a el fragmento Cursos Mooc
                    }

                    if (webUrl.equals("http://harvardx.harvard.edu/")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

                        //Si presionas atras estando en la direccion http://harvardx.harvard.edu/, volveras a el fragmento Cursos Mooc
                    }


                    return true;
                }
                return false;
            }
        });

        wb.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //Metodo cuando la pagina web comienza a cargar

                //El dialogo o ventana de progreso de la pagina aparece
                dialog.show();

                //El dialogo o ventena de progreso desparece depues de 4 segundos
                t.schedule(new TimerTask() {
                    public void run() {

                        dialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 4000); // after 4 second (or 4000 miliseconds), the task will be active.

            }

            @Override
            public void onPageFinished(WebView view, String url) {
            //Metodo usado cuando la pagina termina de cargar

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Metodo para configurar donde se abrira la pagina web
                //Si es falso se abre en la misma aplicacion
                //Si es verdadero se abre afuera de la aplicacion, en un Chrome o cualquier navegador disponible del celular
                return false;
        }});
        return rootView;
    }
}





