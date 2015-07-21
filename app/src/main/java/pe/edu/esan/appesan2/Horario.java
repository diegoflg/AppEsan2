package pe.edu.esan.appesan2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Horario extends Fragment {

    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_horario, container, false);

        gv=(GridView)rootView.findViewById(R.id.gridView1);

        final String[] items = new String[] { "-", "Lunes", "Martes",
                "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "7 a 8", "1",
                "2", "3", "4", "5", "6","7", "8 a 9", "1",
                "2", "3", "4", "5", "6","7", "9 a 10", "1",
                "2", "3", "4", "5", "6","7", "10 a 11", "1",
                "2", "3", "4", "5", "6","7", "12 a 1", "1",
                "2", "3", "4", "5", "6","7", "1 a 2", "1",
                "2", "3", "4", "5", "6","7", "2 a 3", "1",
                "2", "3", "4", "5", "6","7", "3 a 4", "1",
                "2", "3", "4", "5", "6","7", "4 a 5", "1",
                "2", "3", "4", "5", "6","7", "5 a 6", "1",
                "2", "3", "4", "5", "6","7" };

        ArrayAdapter<String> ad = new ArrayAdapter<String>(
                getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,
                items);

        gv.setBackgroundColor(Color.GRAY);
        gv.setNumColumns(8);
        gv.setGravity(Gravity.CENTER);
        gv.setAdapter(ad);








        return rootView;
    }
}