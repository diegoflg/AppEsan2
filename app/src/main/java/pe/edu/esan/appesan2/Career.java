package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Modulo que muestra la pagina web de ESAN Career mediante un webView, la url es:
 * http://careercenter.esan.edu.pe/
 */
public class Career extends Fragment {
    private static String TAG = "TAG";//Tag para deferenciar los logs

    WebView myWebView;//Se crea un WebView llamado myWebView

    @Override
    public void onSaveInstanceState(Bundle outState) {//Metodo override que se ejecuta cuando se guardan datos en cambio de configuracion
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);//se guarda el estado de myWebView
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {//metodo override que se ejecuta cuando se crea la actividad
        super.onActivityCreated(savedInstanceState);
        myWebView.restoreState(savedInstanceState);//Se restaura el estado del webView al crearse la actividad
    }





    @Override//Metodo override que se ejecutaa cuando se crea el fragmento
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override//Metodo override que se ejecuta cuando se restaura el estado de un view
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_noticia, container, false);//Se relaciona el View con su respectivo XML

        setRetainInstance(true);//Genera que no se afecte el fragmento en los cambios de configuracion
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);//Se crea el ProgressDialog

        myWebView = (WebView) rootView.findViewById(R.id.webview);//Se relaciona myWebView con webview del XML
        myWebView.getSettings().setUseWideViewPort(true);//Sets whether the WebView should enable support for the "viewport" HTML meta tag or should use a wide viewport.
        myWebView.getSettings().setLoadWithOverviewMode(true);//Sets whether the WebView loads pages in overview mode, that is, zooms out the content to fit on screen by width. This setting is taken into account when the content width is greater than the width of the WebView control, for example, when getUseWideViewPort() is enabled. The default is false
        myWebView.getSettings().setBuiltInZoomControls(true);//Sets whether the WebView should use its built-in zoom mechanisms. The built-in zoom mechanisms comprise on-screen zoom controls, which are displayed over the WebView's content, and the use of a pinch gesture to control zooming
        myWebView.getSettings().setSupportZoom(true);  //Sets whether the WebView should support zooming using its on-screen zoom controls and gestures.
        myWebView.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        myWebView.getSettings().setDomStorageEnabled(true);//Sets whether the DOM storage API is enabled.
        myWebView.getSettings().setAppCachePath("/data/data/pe.edu.esan.appesan2/cache");//Sets the path to the Application Caches files.
        myWebView.getSettings().setAllowFileAccess(true);//Enables or disables file access within WebView. File access is enabled by default. Note that this enables or disables file system access only. Assets and resources are still accessible using file:///android_asset and file:///android_res.
        myWebView.getSettings().setAppCacheEnabled(true);//Sets whether the Application Caches API should be enabled.
        myWebView.getSettings().setJavaScriptEnabled(true);//Se activa java script
        myWebView.getSettings().setLoadsImagesAutomatically(true);//Sets whether the WebView should load image resources.
        myWebView.getSettings().setDisplayZoomControls(false);//Se quitan los controles de zoom
        final Timer t = new Timer();//Se crea un Timer

        if ( !isNetworkAvailable()) { // loading offline
            myWebView.loadUrl("http://careercenter.esan.edu.pe/");//el webView carga este url
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);//carga el url con cache
            //myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Log.i(TAG, "SIN INTERNET");
        }else{
            Log.i(TAG, "CON INTERNET");
            myWebView.loadUrl("http://careercenter.esan.edu.pe/");//el webView carga este url
            myWebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );//carga el url de modo normal sin cache

        }



        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//Este override detcta si se presiona alguna tecla


/**
 * Si usando el webView cambiaste de url, puedes regresar al anterior presionando atras
 */
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }}
                return false;
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {//Sets the WebViewClient that will receive various notifications and requests.
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){//Override que se ejecuta cuando el webview empieza a cargar
            dialog.show();//Se muestra el ProgressDialog

            t.schedule(new TimerTask() {//comienza a ejecutarse el timer
                public void run() {

                    dialog.dismiss(); // when the task active then close the dialog
                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                }
            }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
        }

        @Override
        public void onPageFinished(WebView view, String url){//override que se ejecuta al finalizar de cargar el webview
            dialog.dismiss();//Se quita el ProgressDialog
            t.cancel();//Se cancela el Timer

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {//Give the host application a chance to take over the control when a new url is about to be loaded in the current WebView.
            return false;
        }}
        );
        return rootView;
    }

    private boolean isNetworkAvailable() {//Se verifia la conexion a internet
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}




