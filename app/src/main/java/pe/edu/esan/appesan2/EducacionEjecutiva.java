package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by educacionadistancia on 24/07/2015.
 */
public class EducacionEjecutiva extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.lay_educacionejecutiva, container, false);
        Button pade     = (Button)rootView.findViewById(R.id.pade);
        Button pae      = (Button)rootView.findViewById(R.id.pae);
        Button pee      = (Button)rootView.findViewById(R.id.pee);
        Button peed     = (Button)rootView.findViewById(R.id.peed);
        Button diplomas = (Button)rootView.findViewById(R.id.diplomas);
        Button idiomas  = (Button)rootView.findViewById(R.id.idiomas);
        Button gestion  = (Button)rootView.findViewById(R.id.gestion);

        final WebView wbEE = (WebView)rootView.findViewById(R.id.wbEE);
        wbEE.loadUrl("http://www.esan.edu.pe/pade/");
        wbEE.getSettings().setUseWideViewPort(true);
        wbEE.getSettings().setLoadWithOverviewMode(true);
        wbEE.getSettings().setBuiltInZoomControls(true);
        wbEE.getSettings().setSupportZoom(true);

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
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        return rootView;
    }
}
