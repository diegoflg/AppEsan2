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
 * Created by educacionadistancia on 20/07/2015.
 */
public class Biblioteca extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_biblioteca, container, false);
        WebView myWebView = (WebView) rootView.findViewById(R.id.webviewB);
        myWebView.loadUrl("http://catalogo.esan.edu.pe/F/F2C85HLVEM91LSU341VF4NX97QRGV2MDYTLXVNA3561H174KYX-20583?func=find-b-0");

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);


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
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           return false;
                                       }}
        );
        return rootView;
    }
}
