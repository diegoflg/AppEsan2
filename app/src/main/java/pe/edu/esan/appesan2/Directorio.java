package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class Directorio extends Fragment {
    ListView listViewSearch;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_directorio, container, false);

        listViewSearch = (ListView) rootView.findViewById(R.id.listview);
        searchView = (SearchView) rootView.findViewById(R.id.searchView);

        String[] values = new String[]{"Profesor X", "Oficina Y", "Uevirtual", "Universidad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listViewSearch.setAdapter(adapter);
        listViewSearch.setTextFilterEnabled(true);
        setHasOptionsMenu(true);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setHint("Buscar...");

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
                if (TextUtils.isEmpty(newText)) {
                    listViewSearch.clearTextFilter();
                } else {
                    listViewSearch.setFilterText(newText.toString());
                }
                return true;
            }
        });
        return rootView;
    }

    private void doSearch (String s){

    }
}
