package pe.edu.esan.appesan2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Noticia extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_noticia, container, false);

        WebView myWebView = (WebView) rootView.findViewById(R.id.webview);
        myWebView.loadUrl("http://blog.ue.edu.pe/");
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        return rootView;
    }
}
