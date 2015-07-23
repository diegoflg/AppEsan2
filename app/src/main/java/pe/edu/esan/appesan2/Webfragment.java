package pe.edu.esan.appesan2;

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

    private WebView wb;
    private int num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.webfragment, container, false);
        Bundle bundle = this.getArguments();
        String link = bundle.getString("url");

        WebView wb = (WebView) rootView.findViewById(R.id.webfragment);
        wb.loadUrl(link);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setSupportZoom(true);



        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    CursosMooc fragment;
                    fragment = new CursosMooc();


                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();

                    return true;
                }
                return false;
            }
        });

        wb.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           return false;
                                       }
                                   }
        );






        return rootView;
    }




}
