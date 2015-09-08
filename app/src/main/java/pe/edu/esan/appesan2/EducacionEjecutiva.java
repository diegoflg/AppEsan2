package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by educacionadistancia on 24/07/2015.
 */
public class EducacionEjecutiva extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.lay_educacionejecutiva, container, false);
        setRetainInstance(true);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        //FUENTE Y COLOR PARA TEXTO DE BOTONES:
        String font_pathEE = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(getActivity().getAssets(), font_pathEE);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING



        Button pade     = (Button)rootView.findViewById(R.id.pade);
        pade.setTypeface(TFEE);
        Button pae      = (Button)rootView.findViewById(R.id.pae);
        pae.setTypeface(TFEE);
        Button pee      = (Button)rootView.findViewById(R.id.pee);
        pee.setTypeface(TFEE);
        Button peed     = (Button)rootView.findViewById(R.id.peed);
        peed.setTypeface(TFEE);
        Button diplomas = (Button)rootView.findViewById(R.id.diplomas);
        diplomas.setTypeface(TFEE);
        Button idiomas  = (Button)rootView.findViewById(R.id.idiomas);
        idiomas.setTypeface(TFEE);
        Button gestion  = (Button)rootView.findViewById(R.id.gestion);
        gestion.setTypeface(TFEE);

        final WebView wbEE = (WebView)rootView.findViewById(R.id.wbEE);
        wbEE.loadUrl("http://www.esan.edu.pe/pade/");
        wbEE.getSettings().setUseWideViewPort(true);
        wbEE.getSettings().setLoadWithOverviewMode(true);
        wbEE.getSettings().setBuiltInZoomControls(true);
        wbEE.getSettings().setSupportZoom(true);
        wbEE.getSettings().setDisplayZoomControls(false);



        wbEE.setOnKeyListener(new View.OnKeyListener() {
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
        pade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/pade/");
            }
        });

        pae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/pae/");
            }
        });


        pee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/pee/");
            }
        });

        peed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/pee/derecho-corporativo/");
            }
        });

        diplomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/diplomas/");
            }
        });

        idiomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/programas-academicos/#idiomas");
            }
        });

        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbEE.loadUrl("http://www.esan.edu.pe/gestion-publica/");
            }
        });

        wbEE.setWebViewClient(new WebViewClient() {
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        return rootView;
    }


}
