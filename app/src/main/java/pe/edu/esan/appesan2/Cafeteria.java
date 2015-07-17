package pe.edu.esan.appesan2;

import android.app.ActionBar;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
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
    TabHost mTabHost;
    RadioButton rb1,rb2,rb3;
    int num=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.lay_cafeteria, container, false);
        listview = (ListView) v.findViewById(R.id.listview);


        rb1 = (RadioButton) v.findViewById(R.id.rb1);
        rb2 = (RadioButton) v.findViewById(R.id.rb2);
        rb3 = (RadioButton) v.findViewById(R.id.rb3);

        mTabHost = (TabHost) v.findViewById(R.id.tabHost2);
        mTabHost.setup();

        TabHost.TabSpec spec = mTabHost.newTabSpec("Tab 1");
        spec.setContent(R.id.LaRuta);
        spec.setIndicator("La Ruta");
        mTabHost.addTab(spec);

        spec=mTabHost.newTabSpec("Tab 2");
        spec.setContent(R.id.DeliSabores);
        spec.setIndicator("Deli Sabores");
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);



        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        go(v);


        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();

                go(v);

                Log.d("Test", "onClickListener ist gestartet");

            }
        });

        rb2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearData();


                Log.d("Test", "onClickListener ist gestartet");
                go(v);

            }
        });



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

            if (mTabHost.getCurrentTab()==1){

                for (int r = 1; r < 8; ++r) {
                    JSONObject column = columns.getJSONObject(r);
                    JSONArray rows = column.getJSONArray("c");
                    String dia = rows.getJSONObject(2).getString("v");
                    String entrada = rows.getJSONObject(3).getString("v");
                    String plato = rows.getJSONObject(4).getString("v");
                    String postre = rows.getJSONObject(5).getString("v");
                    String refresco = rows.getJSONObject(6).getString("v");
                    String sabado = rows.getJSONObject(7).getString("v");

                    Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                    teams.add(team);
                }

            }

            if (mTabHost.getCurrentTab()==0){

                if(rb1.isChecked()){


                        for (int r = 10; r < 15; ++r) {
                            JSONObject column = columns.getJSONObject(r);
                            JSONArray rows = column.getJSONArray("c");
                            String dia = rows.getJSONObject(2).getString("v");
                            String entrada = rows.getJSONObject(3).getString("v");
                            String plato = rows.getJSONObject(4).getString("v");
                            String postre = rows.getJSONObject(5).getString("v");
                            String refresco = rows.getJSONObject(6).getString("v");
                            String sabado = rows.getJSONObject(7).getString("v");

                            Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                            teams.add(team);
                        }


                }

                if(rb2.isChecked()){


                    for (int r = 1; r < 8; ++r) {
                        JSONObject column = columns.getJSONObject(r);
                        JSONArray rows = column.getJSONArray("c");
                        String dia = rows.getJSONObject(2).getString("v");
                        String entrada = rows.getJSONObject(3).getString("v");
                        String plato = rows.getJSONObject(4).getString("v");
                        String postre = rows.getJSONObject(5).getString("v");
                        String refresco = rows.getJSONObject(6).getString("v");
                        String sabado = rows.getJSONObject(7).getString("v");

                        Team team = new Team(dia, entrada, plato, postre, refresco,sabado);
                        teams.add(team);
                    }


                }

            }




            final TeamsAdapter adapter = new TeamsAdapter(getActivity(), R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    public void clearData(){

        teams.clear();
    }








}
