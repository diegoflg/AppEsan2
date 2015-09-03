package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
 * Created by Diegoflg on 7/13/2015.
 */
public class Dpa extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_dpa, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        WebView myWebView = (WebView) rootView.findViewById(R.id.webviewdpa);
        myWebView.loadUrl("http://dpa.ue.edu.pe/carreras/administracion-con-mencion-en-direccion-de-empresas");

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);


        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {

                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.


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
                    }}
                return false;
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
                                       public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

                                       @Override
                                       public void onPageStarted(WebView view, String url, Bitmap favicon){
                                           dialog.show();
                                       }

                                       @Override
                                       public void onPageFinished(WebView view, String url){
                                           dialog.dismiss();
                                       }

                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           return false;
                                       }}
        );
        return rootView;
    }
}




