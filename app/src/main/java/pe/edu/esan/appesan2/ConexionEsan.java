package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by educacionadistancia on 24/07/2015.
 */
public class ConexionEsan extends Fragment {
    WebView ceWB;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ceWB.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ceWB.restoreState(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View CE = inflater.inflate(R.layout.lay_conexionesan, container, false);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);
        setRetainInstance(true);


        ceWB = (WebView) CE.findViewById(R.id.webviewCE);
        ceWB.loadUrl("http://www.esan.edu.pe/conexion/");
        ceWB.getSettings().setUseWideViewPort(true);
        ceWB.getSettings().setLoadWithOverviewMode(true);
        ceWB.getSettings().setBuiltInZoomControls(true);
        ceWB.getSettings().setSupportZoom(true);
        ceWB.getSettings().setDisplayZoomControls(false);
        final Timer t = new Timer();



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
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){

            dialog.show();


            t.schedule(new TimerTask() {
                public void run() {

                    dialog.dismiss(); // when the task active then close the dialog
                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                }
            }, 5000); // after 2 second (or 2000 miliseconds), the task will be active.
        }

        @Override
        public void onPageFinished(WebView view, String url){
            dialog.dismiss();
            t.cancel();


        }

         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             return false;
         }}
        );
        return CE;
    }
}
