package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by educacionadistancia on 20/07/2015.
 */
public class Biblioteca extends Fragment {

    WebView myWebView;
    ProgressDialog pb;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWebView.restoreState(savedInstanceState);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pb.isShowing()) {
            pb.dismiss();
        }
        Log.v("destru", "saddd");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.lay_biblioteca, container, false);

        setRetainInstance(true);




        myWebView = (WebView) rootView.findViewById(R.id.webviewB);
        myWebView.loadUrl("http://esancendoc.esan.edu.pe/");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setDisplayZoomControls(false);


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
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            if(myWebView.getUrl() != myWebView.getOriginalUrl()){
                myWebView.getSettings().setSupportZoom(true);
                myWebView.setInitialScale(50);
            }

            pb = new ProgressDialog(rootView.getContext());
            pb.setTitle("Cargando");
            pb.setMessage("Please Wait....");
            pb.setCancelable(false);
            pb.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
        public void onPageFinished(WebView view, String url) {
            if (pb.isShowing()) {
                pb.dismiss();
            }


        }
                                   }
        );
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
