package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Diegoflg on 7/22/2015.
 */
public class Webfragment extends Fragment {

    private WebView wb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.webfragment, container, false);

        WebView wb = (WebView) rootView.findViewById(R.id.webfragment);
        wb.loadUrl("https://es.coursera.org/");

        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setSupportZoom(true);


        return rootView;
    }


}
