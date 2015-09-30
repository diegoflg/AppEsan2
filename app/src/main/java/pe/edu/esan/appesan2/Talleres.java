package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Este modulo no esta en uso, inicialmente fue desarrollado para pregrado, muestra un word no editable mediante un webView
 *  con los respectivos talleres que tiene pregrado.
 */

public class Talleres extends Fragment {
    //Declaracion de variable que muestra paginas web
    WebView myWebView;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Metodo que guarda el estado actual del fragmento
        super.onSaveInstanceState(outState);
        //Guarda el estado de la pagina web
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Metodo que restaura el estado del fragmento
        super.onActivityCreated(savedInstanceState);
        //Restaura el estado de la pagina web
        myWebView.restoreState(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_talleres, container, false);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Declaracion del dialogo de progreso para la pagina web
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);


        //Se busca el webview segun el id dado en el layout correspondiente
        myWebView = (WebView) v.findViewById(R.id.webview);
        //Se carga el URL a la pagina
        myWebView.loadUrl("https://docs.google.com/document/d/1N0PMPNwLdR6DxpwRbhNrIznCPV_CGInBsM4s4Vg5L18/pub");

        //myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        myWebView.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        myWebView.getSettings().setSupportZoom(true);

        //Opcion desactivada: no muestran los botones de zoom en el webview. No afecta el funcionamiento del zoom
        myWebView.getSettings().setDisplayZoomControls(false);

        //Se da una escala inicial de 70% a la pagina web dentro del webview
        myWebView.setInitialScale(70);

        //Se crea una variable de tiempo
        final Timer t = new Timer();




        myWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                //Metodo que se da cuando la pagina comienza a cargar en el webview

                //El dialogo de progreso se muestra
                dialog.show();

                //El dialogo desaparece cuando termina de cargar segun el tiempo dado que son 5 segundos en este caso
                t.schedule(new TimerTask() {
                    public void run() {

                        dialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 5000); // after 5 second (or 5000 miliseconds), the task will be active.
            }

            @Override
            public void onPageFinished(WebView view, String url){
                //Metodo cuando la pagina termina de cargar

                //El dialogo de progreso desaparece cuando la pagina termina de cargar
                dialog.dismiss();

                //El timer se cancela pues ya no habria un dialogo despues de terminada de cargar la pagina web
                //Con esto el timer y el dialogo no chocan actividades entre ellos y no genera un crasheo a la app
                t.cancel();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
                    //Metodo que abre la pagina o dentro o fuera de la aplicacion
                    //Si es falso la pagina se abre dentro de la aplicacion
                    //Si es verdadero la pagina se abre afuera de la aplicacion en un Chrome u otro explorador del celular
            {
                return false;
            }});

        return v;
    }
   }



