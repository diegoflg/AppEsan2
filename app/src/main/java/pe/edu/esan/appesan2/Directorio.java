package pe.edu.esan.appesan2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;


/**
 * Modulo que muestra la pagina web de Conexion ESAN mediante un webView
 */
public class Directorio extends Fragment {
    ListView listViewSearch;
    SearchView searchView;
    private String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.lay_directorio, container, false);
        setRetainInstance(true);


        listViewSearch = (ListView) rootView.findViewById(R.id.listViewD);
        searchView = (SearchView) rootView.findViewById(R.id.searchViewD);


        //FUENTE PARA TÍTULO EN TABHOST:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //FUENTE PARA TEXTO EN CUADRO DE BÚSQUEDA:
        String font_pathB = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TB = Typeface.createFromAsset(getActivity().getAssets(),font_pathB);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        String[] values = new String[]{getString(R.string.d1),getString(R.string.d2),getString(R.string.d3),getString(R.string.d4),getString(R.string.d5),getString(R.string.d6),getString(R.string.d7),getString(R.string.d10),getString(R.string.d11),getString(R.string.d12),getString(R.string.d13),getString(R.string.d14),getString(R.string.d15),getString(R.string.d16),getString(R.string.d17),getString(R.string.d18)};
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
                String data=(String)parent.getItemAtPosition(position);
                Log.v("pos", String.valueOf(data));
                showPopup(getActivity(), data);
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

    private void showPopup(final Activity context,String opc) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        double width = displaymetrics.widthPixels;

        Log.v("tamano",String.valueOf(height));
        Log.v("tamano",String.valueOf(width));

        double popupHeight = height*0.52;
        double popupWidth = width*0.625;


        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth((int)Math.round(popupHeight));
        popup.setHeight((int)Math.round(popupWidth));
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
            case "Central telefonica":
                tv1.setText(getString(R.string.d1));
                tv2.setText(getString(R.string.d1)+": 712 7200 \n" +
                        "Fax: 345 1328");
                break;

            case "Admisión y Registro de Pregrado":
                tv1.setText(getString(R.string.d2));
                tv2.setText("Linea directa: 712 7205 \n" +
                        "Anexo central: 4500");
                break;

            case "Finanzas":
                tv1.setText(getString(R.string.d3));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "Anexos.: 4331/4329");
                break;

            case "Dimensión Internacional":
                tv1.setText(getString(R.string.d4));
                tv2.setText(getString(R.string.d1)+":712-7200\n" +
                        "Mylene Sandoval\n" +
                        "Anx.: 2257\n" +
                        "Sonia Ponte\n" +
                        "Anx.: 4159\n" );
                break;

            case "Coordinación Académica de PAC":
                tv1.setText(getString(R.string.d5));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "William Rojas\n" +
                        "Anx.: 4188");
                break;

            case "Servicios y Registros Académicos de Pregrado":
                tv1.setText(getString(R.string.d6));
                tv2.setText("Anexo central.: 4545");
                break;

            case "Bienestar Estudiantil":
                tv1.setText(getString(R.string.d7));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "Paola Pacheco\n" +
                        "Anx.: 2425");
                break;

            case "Seguridad":
                tv1.setText(getString(R.string.d10));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                        "Anx.: 4580");
                break;


            case "Cobranzas":
                tv1.setText(getString(R.string.d11));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +
                         "Directo.: 3177222");
                break;

            case "Cafeteria - Central":
                tv1.setText(getString(R.string.d12));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4515");
                break;

            case "Cafeteria - Comedor D":
                tv1.setText(getString(R.string.d13));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4686");
                break;

            case "Cafeteria - Monaditas":
                tv1.setText(getString(R.string.d14));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4857");
                break;

            case "Cafeteria - Hijos de Fruta":
                tv1.setText(getString(R.string.d15));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4858");
                break;

            case "Cafeteria - Deliska":
                tv1.setText(getString(R.string.d16));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4859");
                break;

            case "Cafeteria - 338":
                tv1.setText(getString(R.string.d17));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4623");
                break;

            case "Helpdesk":
                tv1.setText(getString(R.string.d18));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4000");
                break;

            case "DPA":
                tv1.setText(getString(R.string.d18));
                tv2.setText(getString(R.string.d1)+": 712-7200\n" +

                        "Anx.: 4800");
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





