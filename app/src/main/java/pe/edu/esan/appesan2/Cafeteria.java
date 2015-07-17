package pe.edu.esan.appesan2;

import android.app.ActionBar;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Diegoflg on 7/14/2015.
 */
public class Cafeteria extends Fragment {

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<Team> teams = new ArrayList<Team>();
    ListView listview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.lay_cafeteria, container, false);
        listview = (ListView) v.findViewById(R.id.listview);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();



        go(v);



        return v;
    }

    public void go(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1_tClHi6uM5g3vxv_0JkBW5Hzrt6T_Gii5Df973aX9ms");

    }

    private void processJson(JSONObject object) {

        try {
            JSONArray columns = object.getJSONArray("rows");

            for (int r = 0; r < columns.length(); ++r) {
                JSONObject column = columns.getJSONObject(r);
                JSONArray rows = column.getJSONArray("c");
                String dia = rows.getJSONObject(0).getString("v");
                String entrada = rows.getJSONObject(1).getString("v");
                String plato = rows.getJSONObject(2).getString("v");
                String postre = rows.getJSONObject(3).getString("v");
                String refresco = rows.getJSONObject(4).getString("v");

                Team team = new Team(dia, entrada, plato, postre, refresco);
                teams.add(team);
            }

            final TeamsAdapter adapter = new TeamsAdapter(getActivity(), R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
