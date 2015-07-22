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
public class Impresiones extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_impresiones, container, false);
        WebView myWebView = (WebView) rootView.findViewById(R.id.webviewI);
        myWebView.loadUrl("http://impresiones.esan.edu.pe:7290/login.cfm");
        myWebView.getSettings().setJavaScriptEnabled(true);
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
        };
        //http://stackoverflow.com/questions/30790341/android-webview-fill-in-form-and-submit-without-javascript-ids
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:document.getElementsByName('Username')[0].value = '"+"14100015"+"';document.getElementsByName('Password')[0].value='"+"N7N2U2F7"+"';");
            //view.loadUrl("javascript:document.forms[0].submit();");
        }
        }
        );

        return rootView;
    }
}
