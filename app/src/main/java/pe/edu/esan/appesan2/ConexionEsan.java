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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Modulo que muestra la pagina web de Conexion ESAN mediante un webView
 */
public class ConexionEsan extends Fragment {
    WebView ceWB;//Se crea el webView ceWB


    @Override
    public void onSaveInstanceState(Bundle outState) {//Metodo override que se ejecuta cuando se guardan datos en cambio de configuracion
        super.onSaveInstanceState(outState);
        ceWB.saveState(outState);//se guarda el estado de myWebView
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {//metodo override que se ejecuta cuando se crea la actividad
        super.onActivityCreated(savedInstanceState);
        ceWB.restoreState(savedInstanceState);//Se restaura el estado del webView al crearse la actividad
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View CE = inflater.inflate(R.layout.lay_conexionesan, container, false);//Se relaciona el View con su respectivo XML

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);//Se crea el progressDialog
        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion


        ceWB = (WebView) CE.findViewById(R.id.webviewCE);//Se relaciona ceWB con webviewCE del XML
        ceWB.loadUrl("http://www.esan.edu.pe/conexion/");//Se carga el respectivo url
        ceWB.getSettings().setUseWideViewPort(true);//Sets whether the WebView should enable support for the "viewport" HTML meta tag or should use a wide viewport.
        ceWB.getSettings().setLoadWithOverviewMode(true);//Sets whether the WebView loads pages in overview mode, that is, zooms out the content to fit on screen by width. This setting is taken into account when the content width is greater than the width of the WebView control, for example, when getUseWideViewPort() is enabled. The default is false
        ceWB.getSettings().setBuiltInZoomControls(true);//Sets whether the WebView should use its built-in zoom mechanisms. The built-in zoom mechanisms comprise on-screen zoom controls, which are displayed over the WebView's content, and the use of a pinch gesture to control zooming
        ceWB.getSettings().setSupportZoom(true);//Sets whether the WebView should support zooming using its on-screen zoom controls and gestures.
        ceWB.getSettings().setDisplayZoomControls(false);//Se quitan los controles de zoom
        final Timer t = new Timer();//Se crea el Timer



        ceWB.setOnKeyListener(new View.OnKeyListener() {//Se retrocede dentro del webView al presionar atras
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView ceWB = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (ceWB.canGoBack()) {
                                ceWB.goBack();
                                return true;
                            }
                            break;
                    }}
                return false;
            }
        });
        ceWB.setWebViewClient(new WebViewClient() {//Sets the WebViewClient that will receive various notifications and requests.
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){//Override que se ejecuta cuando el webview empieza a cargar

            dialog.show();//Se muestra el ProgressDialog


            t.schedule(new TimerTask() {//comienza el timer
                public void run() {

                    dialog.dismiss(); // when the task active then close the dialog
                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                }
            }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
        }

        @Override
        public void onPageFinished(WebView view, String url){
            dialog.dismiss();//Se cancela el progress dialog
            t.cancel();//se cancela el timer


        }

         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             return false;
         }}//Give the host application a chance to take over the control when a new url is about to be loaded in the current WebView.

        );
        return CE;//Se retorna el view
    }
}
