package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Timer;


/**
 * Modulo que muestra un google calendar que se encuentra en el siguiente link:
 * https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com
 */
public class Calendario extends Fragment{
    private static String TAG = "TAG";//Tag que se creo para diferenciar los LOGS
    public WebView myWebView;//Se crea un WebView llamado myWebView



    @Override//Metodo override que se ejecuta cuando se guardan datos en cambio de configuracion
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);//se guarda el estado de myWebView
    }

    @Override//metodo override que se ejecuta cuando se crea la actividad
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWebView.restoreState(savedInstanceState);//Se restaura el estado del webView al crearse la actividad
    }


    @Override//metodo que se ejecuta cuando se destruye el fragmento
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View c = inflater.inflate(R.layout.lay_talleres, container, false);//Se relaciona el View con su respectivo XML
        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);//Se crea el ProgressDialog

        final Timer t = new Timer();//Se crea el timer

        myWebView = (WebView) c.findViewById(R.id.webview);//Se relaciona myWebView con webview del XML
        myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");//url que carga el myWebView
        myWebView.getSettings().setUseWideViewPort(true);//Sets whether the WebView should enable support for the "viewport" HTML meta tag or should use a wide viewport.
        myWebView.getSettings().setLoadWithOverviewMode(true);//Sets whether the WebView loads pages in overview mode, that is, zooms out the content to fit on screen by width. This setting is taken into account when the content width is greater than the width of the WebView control, for example, when getUseWideViewPort() is enabled. The default is false
        myWebView.getSettings().setBuiltInZoomControls(true);//Sets whether the WebView should use its built-in zoom mechanisms. The built-in zoom mechanisms comprise on-screen zoom controls, which are displayed over the WebView's content, and the use of a pinch gesture to control zooming
        myWebView.getSettings().setSupportZoom(true);//Sets whether the WebView should support zooming using its on-screen zoom controls and gestures.
        myWebView.getSettings().setJavaScriptEnabled(true);//Activa java script en el webView
        myWebView.getSettings().setDisplayZoomControls(false);//Sets whether the WebView should display on-screen zoom controls when using the built-in zoom mechanisms.
        myWebView.getSettings().setDomStorageEnabled(true);//Sets whether the DOM storage API is enabled.
        myWebView.getSettings().setAppCachePath("/data/data/pe.edu.esan.appesan2/cache");//Sets the path to the Application Caches files.
        myWebView.getSettings().setAllowFileAccess(true);//Enables or disables file access within WebView. File access is enabled by default. Note that this enables or disables file system access only. Assets and resources are still accessible using file:///android_asset and file:///android_res.
        myWebView.getSettings().setAppCacheEnabled(true);//Sets whether the Application Caches API should be enabled.
        myWebView.getSettings().setLoadsImagesAutomatically(true);//Sets whether the WebView should load image resources.


        if ( !isNetworkAvailable()) { // loading offline
            myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");
            //Solo cargará el cache si es que no hay internet disponible
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);//carga el cache si no hay internet
            Log.i(TAG, "SIN INTERNET");
        }else{
            Log.i(TAG, "CON INTERNET");
            myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");
            //Cargará con internet por defecto
            myWebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        }







        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//Metodo override que se ejecutaa si se presiona alguna tecla
                if (event.getAction() == KeyEvent.ACTION_DOWN) {//si se presiona atras
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {// y el webview puede retroceder
                                webView.goBack();//retrocedera
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {//Sets the WebViewClient that will receive various notifications and requests.
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//override que se ejecuta cando la pagina empieza a cargara
                /**
                 * Se muestra el ProgressDialog al iniciar la carga de la web
                 *
                 */

                dialog.show();//se muestra el progress dialog

                if(myWebView.getUrl() != myWebView.getOriginalUrl()){//Si el url es diferente al original se hara zoom
                    myWebView.getSettings().setSupportZoom(true);//Sets whether the WebView should support zooming using its on-screen zoom controls and gestures.
                    myWebView.setInitialScale(100);//Zoom a la escala de 100



                }



            }

            @Override
            public void onPageFinished(WebView view, String url) {//override que se ejecuta cuando la pagina acaba de cargar
                /**
                 * Se quita el progresDialog al terminar de cargar la web
                 *
                 */

                dialog.dismiss();// Se cancela el progress dialog


            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }//Give the host application a chance to take over the control when a new url is about to be loaded in the current WebView.
        });

        return c;

        }
    private boolean isNetworkAvailable() {

        /**
         * Se revisa si hay conexion a internet
         *
         */
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
