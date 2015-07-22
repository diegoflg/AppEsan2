package pe.edu.esan.appesan2;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.polidea.view.ZoomView;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Horario extends Fragment {

    private GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_horario, container, false);

        ZoomView zoomView;
        zoomView = new ZoomView(getActivity());
        zoomView.addView(rootView);













        final String[] numbers = new String[] { "-", "Lunes", "Martes",
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

        gridView=(GridView)rootView.findViewById(R.id.gridView1);
        MyAdapter adapter = new MyAdapter(getActivity().getBaseContext(),
                R.layout.item, numbers);



        gridView.setAdapter(adapter);





        return zoomView;
    }

    public class MyAdapter extends ArrayAdapter<String> {

        String[] objects;
        Context context;

        public MyAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.objects = objects;
        }


        @Override
        public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                tv = (TextView)inflater.inflate(R.layout.item,parent,false);
            } else {
                tv = (TextView) convertView;
            }
            tv.setText(objects[position]);
            tv.setTextSize(5);
            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                tv.setBackgroundColor(Color.parseColor("#FFCF1313"));
                tv.setTextColor(Color.WHITE);
            }else{
                tv.setBackgroundColor(Color.WHITE);

            }

            return tv;
        }
    }
}