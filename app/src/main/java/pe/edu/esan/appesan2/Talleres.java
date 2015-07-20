package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Talleres extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_talleres, container, false);

        WebView myWebView = (WebView) v.findViewById(R.id.webview);

        myWebView.loadUrl("https://docs.google.com/document/d/1N0PMPNwLdR6DxpwRbhNrIznCPV_CGInBsM4s4Vg5L18/pub");

        //myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        //myWebView.setInitialScale((int)(100*myWebView.getScale()));
        myWebView.setInitialScale(70);

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }
        });
        return v;
    }
   }
