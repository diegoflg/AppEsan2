package pe.edu.esan.appesan2;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Esta clase ya no se muestra en la aplicacion. Sin embargo; puede ser utilizada con el mismo fin.
 * Permite ver una pagina web de un juego de negocios online
 */
public class Gamificacion extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se infla el layout correspondiente
        View rootView = inflater.inflate(R.layout.lay_gamificacion, container, false);

        //Se retiene el fragmento
        setRetainInstance(true);


        //Se crea y da valor a una vista web con el elemento del layout del mismo tipo
        WebView myWebView = (WebView) rootView.findViewById(R.id.webviewGames);

        //Se carga la pagina con el URL de la web a usar
        myWebView.loadUrl("https://forio.com/simulate/mit/fishbanks-spanish/simulation/");

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);

        //Se dispone los controles para el zoom de la pagina
        myWebView.getSettings().setBuiltInZoomControls(true);

        //Se da soporte de zoom a la pagina web
        myWebView.getSettings().setSupportZoom(true);


        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        //Si la pagina web puede regresar a una anterior regresara
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

        myWebView.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           //Metodo que determina donde se abrira la pagina web
                                           //Si el metodo retorna falso entonces la web se abrira dentro de la aplicacion
                                           //Si el metodo retorna verdadero entonces la pagina se abrira en un navegador externo
                                           //que contenga el movil del usuario
                                           return false;
                                       }}
        );
        return rootView;
    }
}
