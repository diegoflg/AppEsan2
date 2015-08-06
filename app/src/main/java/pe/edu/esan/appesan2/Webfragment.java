package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Diegoflg on 7/22/2015.
 */
public class Webfragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webfragment, container, false);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        Bundle bundle = this.getArguments();
        String link = bundle.getString("url");

        final WebView wb = (WebView) rootView.findViewById(R.id.webfragment);
        wb.loadUrl(link);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setSupportZoom(true);

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();

        wb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            } else {
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                CursosMooc fragment;
                                fragment = new CursosMooc();
                                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                            }
                            break;
                    }}
                return false;
            }});

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    String webUrl = wb.getUrl();
                    Log.v("link", "webUrl");

                    if (webUrl.equals("https://www.edx.org/" )) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    }

                    if (webUrl.equals("https://es.coursera.org/")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    }

                    if (webUrl.equals("https://www.miriadax.net/" )) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    }

                    if (webUrl.equals("http://harvardx.harvard.edu/")) {
                        Log.v("tipo", "es");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        CursosMooc fragment;
                        fragment = new CursosMooc();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    }
                    return true;
                }
                return false;
            }});

        wb.setWebViewClient(new WebViewClient() {
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
        }});
        return rootView;
    }
}





