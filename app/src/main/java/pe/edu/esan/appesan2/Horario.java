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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
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
;


        ZoomView zoomView;
        zoomView = new ZoomView(getActivity());
        zoomView.addView(rootView);


        final String[] numbers = new String[] { "-", "Lunes", "Martes",
                "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "07:00-08:00", "1",
                "2", "3", "4", "5", "6","7", "08:00-09:00", "1",
                "2", "3", "4", "5", "6","7", "09:00-10:00", "1",
                "2", "3", "4", "5", "6","7", "10:00-11:00", "1",
                "2", "3", "4", "5", "6","7", "11:00-12:00", "1",
                "2", "3", "4", "5", "6","7", "12:00-13:00", "1",
                "2", "3", "4", "5", "6","7", "13:00-14:00", "1",
                "2", "3", "4", "5", "6","7", "14:00-15:00", "1",
                "2", "3", "4", "5", "6","7", "15:00-16:00", "1",
                "2", "3", "4", "5", "6","7", "16:00-17:00", "1",
                "2", "3", "4", "5", "6","7", "17:00-18:00", "1",
                "2", "3", "4", "5", "6","7", "18:00-19:00", "1",
                "2", "3", "4", "5", "6","7", "19:00-20:00", "1",
                "2", "3", "4", "5", "6","7", "20:00-21:00", "1",
                "2", "3", "4", "5", "6","7", "21:00-22:00", "1",
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
            tv.setTextSize(10);

            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                tv.setBackgroundColor(Color.parseColor("#FFCF1313"));
                tv.setTextColor(Color.WHITE);
            }else{
                tv.setBackgroundColor(Color.WHITE);

            }

            if(position>16 && position<24){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }

            if(position>32 && position<40){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }

            if(position>48 && position<56){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }

            if(position>64 && position<72){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }

            if(position>80 && position<88){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }

            if(position>96 && position<104){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }
            if(position>112 && position<120){
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));


            }



            return tv;
        }
    }
}