package pe.edu.esan.appesan2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.File;

/**
 * Created by educacionadistancia on 20/07/2015.
 */
public class Impresiones extends Fragment {
    //Declaracion de variables
    private static final int REQUEST_CHOOSER = 1234;
    private ValueCallback<Uri> mUploadMessage;
    WebView myWebView;
    String usuario, clave;

    //Librería importada como proyecto en módulo
    //https://github.com/iPaulPro/aFileChooser

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Guarda el estado del fragmento
        super.onSaveInstanceState(outState);

        //Guarda el estado actual de la pagina web
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Restaura el estado del fragmento
        super.onActivityCreated(savedInstanceState);

        //Restaura el estado de la pagina web
        myWebView.restoreState(savedInstanceState);
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Metodo que sirve para acceder a un FileCHOOSER
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode==REQUEST_CHOOSER)
                if(data != null){
                    {
                        if (null == mUploadMessage) return;
                        Uri uri = data.getData();
                        // Get the File path from the Uri
                        String path = FileUtils.getPath(getActivity(), uri);
                        // Alternatively, use FileUtils.getFile(Context, Uri)
                        if (path != null && FileUtils.isLocal(path)) {
                            File file = new File(path);
                        }
                        mUploadMessage.onReceiveValue(uri);
                        mUploadMessage = null;
                    }
                }

        }else {

            Log.i("ENTRA", "RETORNA");
            Log.i("ENTRA", "RETORNA 2");
            Log.i("ENTRA", "RETORNA 3");
            //getActivity().getSupportFragmentManager().beginTransaction().hide(this).commit();
            if(getActivity().getFragmentManager().findFragmentByTag("Impresiones") != null){
               getActivity().getSupportFragmentManager().beginTransaction().show(getActivity().getSupportFragmentManager().findFragmentByTag("Impresiones")).commit();
            }else{
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new Impresiones(), "Impresiones").commit();
            }
            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Impresiones()).commit();

        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Infla la vista con el layout respectivo
        View rootView = inflater.inflate(R.layout.lay_impresiones, container, false);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Crea y da valor a un dialogo de progreso
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //Se da valor a la vista de la web con el elemento del layout
        myWebView = (WebView) rootView.findViewById(R.id.webviewI);

        //De carga la pagina de impresiones de esan
        myWebView.loadUrl("http://impresiones.esan.edu.pe:7290/login.cfm");

        //Se activa el JavaScript de la pagina web
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        myWebView.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        myWebView.getSettings().setSupportZoom(true);

        //Da estilo a la barra de desplazamiento de la web
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);
        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        //Permite el acceso a archivos del celular
        myWebView.getSettings().setAllowFileAccess(true);

        //Da valor de 50% a la escala de la vista de la pagina web
        myWebView.setInitialScale(50);

        //No se muestran los botones de zoom en el webview, sin embargo esto no afecta
        //al uso del zoom en la pagina web
        myWebView.getSettings().setDisplayZoomControls(false);

        //Obtiene el usuario dado en el login
        usuario = Datah.getInstance().getUser();

        //Obtiene la clave dada en el login
        clave = Datah.getInstance().getPass();

        myWebView.setWebChromeClient(new WebChromeClient() {
            //Metodos para abrir el Selecccionador de archivos segun su version de android
            //De 4.4 KitKat este Seleccionador de archivos no funciona debido a un problema
            //que Google Developers no puede solucionar desde hace un par de años
            //Por esta razon cuando se abra el modulo en versiones mayores o iguales a 4.4 KitKat
            //la pagina web de impresiones se abrira en el navegador con el fin de no obstruir
            //la seleccion del archivo a imprimir

            // openFileChooser for Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType){
                Log.i("TAG", " MAYOR A 3.0");
                Log.i("TAG", " MAYOR A 3.0");
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), REQUEST_CHOOSER);
            }


            // openFileChooser for Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg){
                Log.i("TAG", " MENOR A 3.0");
                Log.i("TAG", " MENOR A 3.0");
                Log.i("TAG", " MENOR A 3.0");
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), REQUEST_CHOOSER);
                //openFileChooser(uploadMsg, "");
            }

            //openFileChooser for other Android versions
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("TAG", "OTROS");
                Log.i("TAG", "OTROS");
                Log.i("TAG", "OTROS");
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), REQUEST_CHOOSER);

                //openFileChooser(uploadMsg, acceptType);
            }


            // The webPage has 2 filechoosers and will send a
            // console message informing what action to perform,
            // taking a photo or updating the file

            public boolean onConsoleMessage(ConsoleMessage cm) {
                onConsoleMessage(cm.message(), cm.lineNumber(), cm.sourceId());
                return true;
            }

            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                //Log.d("androidruntime", "Show console messages, Used for debugging: " + message);
            }
        });   // End setWebChromeClient


        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            //Si el webview puede retornar a la pagina anterior retornara
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

        myWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                //Cuando la pagina ha iniciado a cargar

                if(myWebView.getUrl() != myWebView.getOriginalUrl()){
                    //Obtiene el URL y si es diferente del original entonces dra zoom
                    // a una escala del 70% y ocultara el dialogo de progreso
                    myWebView.getSettings().setSupportZoom(true);
                    myWebView.setInitialScale(70);
                    dialog.hide();
                }
                dialog.show();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Boolean T;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    Log.i("TAG", "SHOULDOVERRIDEURLLOADING");
                    Log.i("TAG", "SHOULDOVERRIDEURLLOADING");
                    Log.i("TAG", "SHOULDOVERRIDEURLLOADING");
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                    T = false;
                } else {
                    Log.i("TAG","TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    Log.i("TAG","TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    Log.i("TAG","TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    T = false;
                }
                return T;
            }

            //http://stackoverflow.com/questions/16055800/how-to-enter-password-automatically-in-webview
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                //El dialogo de progreso desaparecera


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    //Si la version de android del celular es mayor o igual a 4.4 KitKat entonces
                    //mandara los datos del login

                    //Este metodo no funciona debido a que actualmente se abrira la pagina de impresiones afuera de la aplicacion
                    //es decir en un navegador del propio celular, sea Chrome u otro y por lo tanto los datos del login no seran
                    //enviados a la pagina web
                    view.evaluateJavascript("javascript:document.getElementsByName('Username')[0].value = '" + usuario + "'", null);
                    view.evaluateJavascript("javascript:document.getElementsByName('Password')[0].value = '" + clave + "'", null);
                    view.evaluateJavascript("javascript:document.forms['loginform'].submit()", null);
                } else {
                    //Si la version de android del celular es menor o igual a 4.4 KitKat entonces mandara los datos del login
                    // a la pagina de impresiones abierta en la aplicacion
                    Log.i("TAG","ENTRA A MENOR DE KITKAT");
                    Log.i("TAG","ENTRA A MENOR DE KITKAT");
                    view.loadUrl("javascript:document.getElementsByName('Username')[0].value = '" + usuario + "'");
                    view.loadUrl("javascript:document.getElementsByName('Password')[0].value = '" + clave + "'");
                    view.loadUrl("javascript:document.forms['loginform'].submit()");
                }


            }});
        return rootView;
    }
}
