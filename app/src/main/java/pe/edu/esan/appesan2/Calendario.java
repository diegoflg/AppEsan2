package pe.edu.esan.appesan2;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.logging.Handler;

/**
 * Created by educacionadistancia on 13/07/2015.
 */
public class Calendario extends Fragment{
    public WebView myWebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View c = inflater.inflate(R.layout.lay_talleres, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

            myWebView = (WebView) c.findViewById(R.id.webview);
            myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");

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

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }}
        );
        return c;
        }
}
