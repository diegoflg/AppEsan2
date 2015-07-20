package pe.edu.esan.appesan2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Diegoflg on 7/16/2015.
 */public class TeamsAdapter extends ArrayAdapter<Team> {

    Context context;
    private ArrayList<Team> teams;

    public TeamsAdapter(Context context, int textViewResourceId, ArrayList<Team> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.teams = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team, null);
        }
        Team o = teams.get(position);
        if (o != null) {
            TextView lunes = (TextView) v.findViewById(R.id.position);
            /**  TextView martes = (TextView) v.findViewById(R.id.name);
            TextView miercoles = (TextView) v.findViewById(R.id.wins);
            TextView jueves = (TextView) v.findViewById(R.id.draws);
            TextView viernes = (TextView) v.findViewById(R.id.losses);
            TextView sabado = (TextView) v.findViewById(R.id.sabado);
             *
             */


            lunes.setText(String.valueOf(o.getdDia()));
            /**
            martes.setText(String.valueOf(o.getEntrada()));
            miercoles.setText(String.valueOf(o.getPlato()));
            jueves.setText(String.valueOf(o.getPostre()));
            viernes.setText(String.valueOf(o.getRefresco()));
            sabado.setText(String.valueOf(o.getSabado()));
             *
             */

        }
        return v;
    }
}
