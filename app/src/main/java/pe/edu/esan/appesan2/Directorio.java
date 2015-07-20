package pe.edu.esan.appesan2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

public class Directorio extends Fragment {
    ListView listViewSearch;
    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.lay_directorio, container, false);

        listViewSearch = (ListView) rootView.findViewById(R.id.listview);
        searchView = (SearchView) rootView.findViewById(R.id.searchView);

        String[] values = new String[]{"Central telefonica","Admisión y Registro de Pregrado", "Finanzas", "Dimensión Internacional", "Coordinación Académica de PAC","Servicios y Registros Académicos de Pregrado","Bienestar Estudiantil"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listViewSearch.setAdapter(adapter);
        listViewSearch.setTextFilterEnabled(false);
        setHasOptionsMenu(true);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        final TextView textView = (TextView) searchView.findViewById(id);
        textView.setHint("Buscar...");

        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.v("pos", String.valueOf(position));

                showPopup(getActivity(),position);





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

    private void doSearch (String s){

    }




    private void showPopup(final Activity context,int opc) {
        int popupWidth = 350;
        int popupHeight = 300;

        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);


        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, 100, 200);

        // Getting a reference to Close button, and close the popup when clicked.
        Button call = (Button) layout.findViewById(R.id.call);
        Button close = (Button) layout.findViewById(R.id.close);
        TextView tv1 = (TextView) layout.findViewById(R.id.poptv1);
        TextView tv2 = (TextView) layout.findViewById(R.id.poptv2);


        switch(opc){
            case 0:
                tv1.setText("Central telefonica");
                tv2.setText("Central Telefónica: 712 7200 \n" +
                        "Fax: 345 1328");


                break;

            case 1:
                tv1.setText("Admisión y Registro de Pregrado");
                tv2.setText("Linea directa: 712 7205 \n" +
                        "Anexo central: 4500");

                break;

            case 2:
                tv1.setText("Finanzas");
                tv2.setText("Central telefónica 712-7200\n" +
                        "Anexos.: 4331/4329");

                break;

            case 3:
                tv1.setText("Dimensión Internacional");
                tv2.setText("Central telefónica 712-7200\n" +
                        "Mylene Sandoval\n" +
                        "Anx.: 2257\n" +
                        "Sonia Ponte\n" +
                        "Anx.: 4159\n" );

                break;

            case 4:
                tv1.setText("Coordinación Académica de PAC");
                tv2.setText("Central telefónica 712-7200\n" +
                        "William Rojas\n" +
                        "Anx.: 4188");

                break;

            case 5:
                tv1.setText("Servicios");
                tv2.setText("Anexo central.: 4545");

                break;

            case 6:
                tv1.setText("Bienestar Estudiantil ");
                tv2.setText("Central telefónica 712-7200\n" +
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


}
