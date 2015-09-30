package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Modulo que abre la pagina web del DPA en un webview
 */
public class Dpa extends Fragment {
    //Declara una variable para la vista de una web
    WebView myWebView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Se guarda el estado actual del fragmento
        super.onSaveInstanceState(outState);

        //Se guarda el estado actual del webview
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Se restaura el estado del fragmento
        super.onActivityCreated(savedInstanceState);

        //Se restaura el estado del webview
        myWebView.restoreState(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se infla la vista del fragmento con el layout correspondiente
        View rootView = inflater.inflate(R.layout.lay_dpa, container, false);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Se crea y se da valor a un dialogo de progreso
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);


        //Se da valor o infla la variable declarada anteriormente con con el elemento dentro del layout
        myWebView = (WebView) rootView.findViewById(R.id.webviewdpa);

        //Se carga la pagina web dando como valor el URL de la misma
        myWebView.loadUrl("http://dpa.ue.edu.pe/carreras/noticias");

        //Se desactivan los botones de zoom sin afectar el uso de este en la pagina web
        myWebView.getSettings().setDisplayZoomControls(false);

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);

        //Permite el uso de los controles de zoom
        myWebView.getSettings().setBuiltInZoomControls(true);

        //Da el soporte para el uso del zoom en la pagina web
        myWebView.getSettings().setSupportZoom(true);





        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;
                    switch (keyCode) {
                        //Si se da clic a regresar
                        case KeyEvent.KEYCODE_BACK:
                            //Si la pagina actual del webview puede retornar a una anterior entonces
                            if (webView.canGoBack()) {
                                //La pagina regresara a ser la anterior
                                webView.goBack();
                                return true;
                            }
                            break;
                    }}
                return false;
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
                                       public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

                                       @Override
                                       public void onPageStarted(WebView view, String url, Bitmap favicon){
                                           //Metodo usado cuando la pagina empieza a cargar

                                           //El dialogo de progreso aparece cuando la pagina inicia a cargar
                                           dialog.show();

                                       }

                                       @Override
                                       public void onPageFinished(WebView view, String url){
                                           //Metodo usado cuando la pagina termina de cargar

                                           //El dialogo de progreso desaparece
                                           dialog.dismiss();

                                       }

                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           //Metodo que da como dato verdadero o falso segun sea el caso
                                           //Si la pagina se desea abrir en la misma aplicacion debera retornar falso
                                           //Si la pagina se desea abrir afuera de la aplicacion, en un navegador externo
                                           //del movil el dato a retornar sera verdadero(true)
                                           return false;
                                       }}



        );
        return rootView;
    }

}




