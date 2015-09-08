package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

public class Talleres extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_talleres, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setRetainInstance(true);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);



        WebView myWebView = (WebView) v.findViewById(R.id.webview);
        myWebView.loadUrl("https://docs.google.com/document/d/1N0PMPNwLdR6DxpwRbhNrIznCPV_CGInBsM4s4Vg5L18/pub");
        //myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.setInitialScale(70);


        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {

                dialog.dismiss(); // when the task active then close the dialog
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.

        myWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                dialog.show();
                int currentOrientation = getResources().getConfiguration().orientation;
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                }
                else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url){
                dialog.dismiss();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }});
        return v;
    }
   }



