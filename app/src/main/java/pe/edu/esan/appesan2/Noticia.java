package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Noticia extends Fragment {
    private static String TAG = "MUNDO CRUEL";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_noticia, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        WebView myWebView = (WebView) rootView.findViewById(R.id.webview);


        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
      //myWebView.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setAppCachePath("/data/data/pe.edu.esan.appesan2/cache");
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadsImagesAutomatically(true);


        if ( !isNetworkAvailable()) { // loading offline
            myWebView.loadUrl("http://blog.ue.edu.pe/");
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            //myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Log.i(TAG, "SIN INTERNET");
        }else{
            Log.i(TAG, "CON INTERNET");
            myWebView.loadUrl("http://blog.ue.edu.pe/");

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

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}




