package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by educacionadistancia on 24/07/2015.
 */
public class ConexionEsan extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View CE = inflater.inflate(R.layout.lay_conexionesan, container, false);

        WebView ceWB = (WebView) CE.findViewById(R.id.webviewCE);
        ceWB.loadUrl("http://www.esan.edu.pe/conexion/");
        ceWB.getSettings().setUseWideViewPort(true);
        ceWB.getSettings().setLoadWithOverviewMode(true);
        ceWB.getSettings().setBuiltInZoomControls(true);
        ceWB.getSettings().setSupportZoom(true);

        ceWB.setOnKeyListener(new View.OnKeyListener() {
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
        ceWB.setWebViewClient(new WebViewClient() {
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             return false;
         }}
        );
        return CE;
    }
}
