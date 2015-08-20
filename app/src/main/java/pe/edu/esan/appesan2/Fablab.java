package pe.edu.esan.appesan2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Fablab extends Fragment {
    public ArrayList<Noticias> Array_Noticias = new ArrayList<Noticias>();
    private Noticias_Adapter adapter;

    private String URL = "http://www.geekytheory.com/feed/";

    private ListView lista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_noticias, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        lista = (ListView) getActivity().findViewById(R.id.noticias_listview);
        adapter = new Noticias_Adapter(getActivity(), Array_Noticias);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getActivity(), Activity_Articulo.class);
                intent.putExtra("parametro", Array_Noticias.get(arg2));
                //intent.putExtra("parametro", "Artículo número "+(arg2+1));
                startActivity(intent);
            }
        });

        rellenarNoticias();

        return rootView;
    }



    private void rellenarNoticias() {
        if (isOnline()) {
            new DescargarNoticias(getActivity().getBaseContext(), URL).execute();
        }

    }

    private class DescargarNoticias extends AsyncTask<String, Void, Boolean> {

        private String feedUrl;
        private Context ctx;

        public DescargarNoticias(Context c, String url) {
            this.feedUrl = url;
            this.ctx = c;
        }

        @Override
        protected Boolean doInBackground(final String... args) {
            XMLParser parser = new XMLParser(feedUrl, getActivity().getBaseContext());
            Array_Noticias = parser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(ctx, "Error en la lectura", Toast.LENGTH_LONG)
                        .show();
            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}


/*

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        WebView myWebView = (WebView) rootView.findViewById(R.id.webviewfab);
        myWebView.loadUrl("http://fablab.esan.edu.pe/");

        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);

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
 */





