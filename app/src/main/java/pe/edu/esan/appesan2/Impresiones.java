package pe.edu.esan.appesan2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.File;

/**
 * Created by educacionadistancia on 20/07/2015.
 */
public class Impresiones extends Fragment {
    private static final int REQUEST_CHOOSER = 1234;
    private ValueCallback<Uri> mUploadMessage;
    WebView myWebView;
    String usuario, clave;

    //Librería importada como proyecto en módulo
    //https://github.com/iPaulPro/aFileChooser




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CHOOSER)
        {
            if (null == mUploadMessage) return;
            Uri uri = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
            // Get the File path from the Uri
            String path = FileUtils.getPath(getActivity(), uri);
            // Alternatively, use FileUtils.getFile(Context, Uri)
            if (path != null && FileUtils.isLocal(path)) {
                File file = new File(path);
            }
            mUploadMessage.onReceiveValue(uri);
            mUploadMessage = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        View rootView = inflater.inflate(R.layout.lay_impresiones, container, false);
        setRetainInstance(true);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);







        myWebView = (WebView) rootView.findViewById(R.id.webviewI);
        myWebView.loadUrl("http://impresiones.esan.edu.pe:7290/login.cfm");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);

        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);
        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.setInitialScale(50);
        myWebView.getSettings().setDisplayZoomControls(false);

        usuario = Datah.getInstance().getUser();
        clave = Datah.getInstance().getPass();

        myWebView.setWebChromeClient(new WebChromeClient() {
            // openFileChooser for Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType){
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), REQUEST_CHOOSER);
            }

            // openFileChooser for Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg){openFileChooser(uploadMsg, "");
            }

            //openFileChooser for other Android versions
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooser(uploadMsg, acceptType);
            }
            // The webPage has 2 filechoosers and will send a
            // console message informing what action to perform,
            // taking a photo or updating the file

            public boolean onConsoleMessage(ConsoleMessage cm) {
                onConsoleMessage(cm.message(), cm.lineNumber(), cm.sourceId());
                return true;
            }

            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                //Log.d("androidruntime", "Show console messages, Used for debugging: " + message);
            }
        });   // End setWebChromeClient


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
                    }
                }
                return false;
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                if(myWebView.getUrl() != myWebView.getOriginalUrl()){
                    myWebView.getSettings().setSupportZoom(true);
                    myWebView.setInitialScale(70);
                }
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            };

            //http://stackoverflow.com/questions/16055800/how-to-enter-password-automatically-in-webview
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    view.evaluateJavascript("javascript:document.getElementsByName('Username')[0].value = '" + usuario + "'", null);
                    view.evaluateJavascript("javascript:document.getElementsByName('Password')[0].value = '" + clave + "'", null);
                    view.evaluateJavascript("javascript:document.forms['loginform'].submit()", null);
                } else {
                    view.loadUrl("javascript:document.getElementsByName('Username')[0].value = '" + usuario + "'");
                    view.loadUrl("javascript:document.getElementsByName('Password')[0].value = '" + clave + "'");
                    view.loadUrl("javascript:document.forms['loginform'].submit()");
                }


            }});
        return rootView;
    }
}
