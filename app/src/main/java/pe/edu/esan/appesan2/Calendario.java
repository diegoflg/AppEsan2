package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Created by educacionadistancia on 13/07/2015.
 */
public class Calendario extends Fragment{
    private static String TAG = "MUNDO CRUEL";
    public WebView myWebView;



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View c = inflater.inflate(R.layout.lay_talleres, container, false);
        setRetainInstance(true);


        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        final Timer t = new Timer();







        myWebView = (WebView) c.findViewById(R.id.webview);
        myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setAppCachePath("/data/data/pe.edu.esan.appesan2/cache");
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setLoadsImagesAutomatically(true);


        if ( !isNetworkAvailable()) { // loading offline
            myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            //myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Log.i(TAG, "SIN INTERNET");
        }else{
            Log.i(TAG, "CON INTERNET");
            myWebView.loadUrl("https://www.google.com/calendar/htmlembed?src=ndo8qlb1snenh41blag9slaav8%40group.calendar.google.com");

            myWebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        }







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
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                dialog.show();

                if(myWebView.getUrl() != myWebView.getOriginalUrl()){
                    myWebView.getSettings().setSupportZoom(true);
                    myWebView.setInitialScale(100);



                }



            }

            @Override
            public void onPageFinished(WebView view, String url) {

                dialog.dismiss();


            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
        });

        return c;

        }
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
