package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * En esta clase esta el modulo de bilbioteca, modulo que utiliza un webview declarado como myWebView para motrar la pagina web de cendoc, que es :
 * http://esancendoc.esan.edu.pe/
 * Tenemos tambien un ProgressDialog declarado como pb, que sirve para indicar al usuario que el usuario esta cagando
 *
 * El ProgressDialog inicia on el metodo show(), y se cancela con el metodo dismiss()
 */
public class Biblioteca extends Fragment {

    WebView myWebView;
    ProgressDialog pb;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWebView.restoreState(savedInstanceState);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pb.isShowing()) {
            pb.dismiss();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.lay_biblioteca, container, false);

        setRetainInstance(true);
        /**
         * setRetainInstance(true) sirve para preserar el fragmente ante cambios de configuracion
         */




        myWebView = (WebView) rootView.findViewById(R.id.webviewB);
        myWebView.loadUrl("http://esancendoc.esan.edu.pe/");
        myWebView.getSettings().setJavaScriptEnabled(true);//se permite el uso de java script en el webview
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);//se activan las opciones de zoom en el webview
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setDisplayZoomControls(false);//desaparecen los botones grandes para hacer zoom




        myWebView.setOnKeyListener(new View.OnKeyListener() {

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

                /**
                 * Si usando el webView cambiaste de url, puedes regresar al anterior presionando atras
                 */
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            if(myWebView.getUrl() != myWebView.getOriginalUrl()){
                myWebView.getSettings().setSupportZoom(true);
                myWebView.setInitialScale(50);
            }

            pb = new ProgressDialog(rootView.getContext()); //creacion del ProgressDialog
            pb.setTitle("Cargando");
            pb.setMessage("Please Wait....");
            pb.setCancelable(false);//el progresDialog no se cancela pulsando atras o dando click en la pantalla del dispositivo
            pb.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
        public void onPageFinished(WebView view, String url) {
            if (pb.isShowing()) {
                pb.dismiss();//desaparece el ProgressDialog
            }


        }
                                   }
        );
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
