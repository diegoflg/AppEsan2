package pe.edu.esan.appesan2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;

public class Directorio extends Fragment {
    TabHost tbDM;

    ListView listViewSearch;
    SearchView searchView;

    private String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.lay_directorio, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setRetainInstance(true);


        tbDM = (TabHost) rootView.findViewById(R.id.tbDM);
        tbDM.setup();

        listViewSearch = (ListView) rootView.findViewById(R.id.listViewD);
        searchView = (SearchView) rootView.findViewById(R.id.searchViewD);

        WebView wbD = (WebView)rootView.findViewById(R.id.wbD);
        wbD.loadUrl("http://uemoodle.ue.edu.pe/message/index.php");
        wbD.getSettings().setUseWideViewPort(true);
        wbD.getSettings().setJavaScriptEnabled(true);
        wbD.getSettings().setLoadWithOverviewMode(true);
        wbD.getSettings().setBuiltInZoomControls(true);
        wbD.getSettings().setSupportZoom(true);
        wbD.getSettings().setDisplayZoomControls(false);
        wbD.setInitialScale(50);

        wbD.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView wbDi = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (wbDi.canGoBack()) {
                                wbDi.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

        wbD.setWebViewClient(new WebViewClient() {
                                 @Override
                                 public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                     return false;
                                 }

                                 public void onPageFinished(WebView view, String url) {


                                     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                         view.evaluateJavascript("javascript:document.getElementsByName('username')[0].value = '" + "14100015" + "';" +
                                                 "document.getElementsByName('password')[0].value='" + "N7N2U2F7" + "';",null);
                                         view.evaluateJavascript("javascript:document.forms[0].login();",null);
                                     } else {
                                         view.loadUrl("javascript:document.getElementsByName('username')[0].value = '" + "14100015" + "';" +
                                                 "document.getElementsByName('password')[0].value='" + "N7N2U2F7" + "';");
                                         view.loadUrl("javascript:document.forms[0].login();");
                                     }
                                     //http://stackoverflow.com/questions/30790341/android-webview-fill-in-form-and-submit-without-javascript-ids

                                 }
                             }
        );

        TabHost.TabSpec spec = tbDM.newTabSpec("Tab 1");
        spec.setContent(R.id.Directorio);
        spec.setIndicator(getString(R.string.d8));
        tbDM.addTab(spec);

        spec = tbDM.newTabSpec("Tab 2");
        spec.setContent(R.id.Mensajería);
        spec.setIndicator(getString(R.string.d9));
        tbDM.addTab(spec);

        tbDM.setCurrentTab(0);

        //FUENTE PARA TÍTULO EN TABHOST:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        for(int i=0;i<tbDM.getTabWidget().getChildCount();i++)
        {tbDM.getTabWidget().setStripEnabled(true);
        tbDM.getTabWidget().setRightStripDrawable(R.drawable.greyline);
        tbDM.getTabWidget().setLeftStripDrawable(R.drawable.greyline);

        TextView tv = (TextView) tbDM.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTypeface(TFT);
        }
        tbDM.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffc90039")); //unselected
        tbDM.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffc90039")); // selected

        //FUENTE PARA TEXTO EN CUADRO DE BÚSQUEDA:
        String font_pathB = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TB = Typeface.createFromAsset(getActivity().getAssets(),font_pathB);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        String[] values = new String[]{getString(R.string.d1),getString(R.string.d2),getString(R.string.d3),getString(R.string.d4),getString(R.string.d5),getString(R.string.d6),getString(R.string.d7)};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listViewSearch.setAdapter(adapter);
        listViewSearch.setTextFilterEnabled(false);
        setHasOptionsMenu(true);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        final TextView textView = (TextView) searchView.findViewById(id);
        textView.setHint("Buscar...");
        textView.setTypeface(TB);

        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("pos", String.valueOf(position));
                showPopup(getActivity(), position);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() < 3) {
                    return true;
                } else {
                    doSearch(s);
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                android.widget.Filter filter = adapter.getFilter();
                filter.filter(newText);
                return true;
            }
        });
        return rootView;
    }

    private void doSearch (String s){}

    private void showPopup(final Activity context,int opc) {
        int popupWidth = 250;
        int popupHeight = 200;

        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // FUENTE PARA TEXTO EN POPUP Y BOTONES:
        String font_pathPP = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TPP = Typeface.createFromAsset(getActivity().getAssets(),font_pathPP);//llamanos a la CLASS TYPEFACE y la definimos
        // con un CREATE desde ASSETS con la ruta STRING

        // Getting a reference to Close button, and close the popup when clicked.
        Button call = (Button) layout.findViewById(R.id.call);
        Button close = (Button) layout.findViewById(R.id.close);
        call.setTypeface(TPP);
        close.setTypeface(TPP);


        TextView tv1 = (TextView) layout.findViewById(R.id.poptv1);
        tv1.setTypeface(TPP);
        TextView tv2 = (TextView) layout.findViewById(R.id.poptv2);
        tv2.setTypeface(TPP);

        switch(opc){
            case 0:
                tv1.setText(getString(R.string.d1));
                tv2.setText(getString(R.string.d1)+": 712 7200 \n" +
                        "Fax: 345 1328");
                break;

            case 1:
                tv1.setText(getString(R.string.d2));
                tv2.setText("Linea directa: 712 7205 \n" +
                        "Anexo central: 4500");
                break;

            case 2:
                tv1.setText(getString(R.string.d3));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "Anexos.: 4331/4329");
                break;

            case 3:
                tv1.setText(getString(R.string.d4));
                tv2.setText(getString(R.string.d1)+":712-7200\n" +
                        "Mylene Sandoval\n" +
                        "Anx.: 2257\n" +
                        "Sonia Ponte\n" +
                        "Anx.: 4159\n" );
                break;

            case 4:
                tv1.setText(getString(R.string.d5));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "William Rojas\n" +
                        "Anx.: 4188");
                break;

            case 5:
                tv1.setText(getString(R.string.d6));
                tv2.setText("Anexo central.: 4545");
                break;

            case 6:
                tv1.setText(getString(R.string.d7));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "Paola Pacheco\n" +
                        "Anx.: 2425");
                break;
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:017127200"));
                    startActivity(callIntent);
                } catch (ActivityNotFoundException e) {
                    Log.e("dialing example", "Call failed", e);
                }
            }
        });
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}





