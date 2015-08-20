package pe.edu.esan.appesan2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import pl.polidea.view.ZoomView;

/**
 * Created by Diegoflg on 7/13/2015.
 */
public class Horario extends Fragment {
    private TextView tvHorario;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_horario, container, false);


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
        MyAdapter adapter = new MyAdapter(getActivity().getBaseContext(), R.layout.item, numbers);
        gridView.setAdapter(adapter);

        String font_path = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TF = Typeface.createFromAsset(getActivity().getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        tvHorario = (TextView)rootView.findViewById(R.id.textView2);
        tvHorario.setTextColor(Color.parseColor("#C90039"));
        tvHorario.setTypeface(TF);

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

            String font_path = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
            Typeface fuenteGV = Typeface.createFromAsset(getActivity().getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                tv = (TextView)inflater.inflate(R.layout.item,parent,false);
            } else {
                tv = (TextView) convertView;
            }
            tv.setText(objects[position]);
            tv.setTextSize(10);
            tv.setGravity(Gravity.CENTER);
            tv.setHeight(50);
            tv.setWidth(70);
            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                tv.setBackgroundColor(Color.parseColor("#C90039"));
                tv.setTextColor(Color.WHITE);
                tv.setTypeface(fuenteGV);
            }else{
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.parseColor("#6B6C6E"));
                tv.setTypeface(fuenteGV);
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

            HorarioBD adminH = new HorarioBD(getContext(), "BDHORARIO1", null, 1);
            SQLiteDatabase bdH = adminH.getWritableDatabase();

            Cursor filahorarios = bdH.rawQuery("select curso,dia,hora,salon from Horario" , null);
            Log.v("cursor", "cursor");

            String curs,di,hor,sa;

            if (filahorarios.moveToFirst())
            {
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                int pos;
                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));

                if(position==pos){
                    tv.setText(curs+sa);
                }

                filahorarios.moveToNext();
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);
                pos=encontrarposicion(di, hor);

                if(position==pos){
                    tv.setText(curs+sa);
                }

                filahorarios.moveToNext();
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));

                Log.v("dia", (filahorarios.getString(1)));
                Log.v("hor", (filahorarios.getString(2)));

                if(position==pos){
                    tv.setText(curs+sa);
                }

                filahorarios.moveToNext();
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));
                Log.v("dia", (filahorarios.getString(1)));
                Log.v("hor", (filahorarios.getString(2)));

                if(position==pos){
                    tv.setText(curs+sa);
                }
            }

            bdH.close();
            return tv;
        }
    }

    public int encontrarposicion(String finddia,String findhora){

        if(finddia.equals("Lunes")){

            if(findhora.equals("7:00-8:00")){
                Log.v("entro", "lunes de 7 a 8");
                return 9;

            }if(findhora.equals("8:00-9:00")){
                return 17;

            }if(findhora.equals("9:00-10:00")){
                return 25;

            }if(findhora.equals("10:00-11:00")){
                return 33;

            }if(findhora.equals("11:00-12:00")){
                return 41;

            }if(findhora.equals("12:00-13:00")){
                return 49;

            }if(findhora.equals("13:00-14:00")){
                return 57;

            }if(findhora.equals("14:00-15:00")){
                return 65;

            }
            if(findhora.equals("15:00-16:00")){
                return 73;

            }if(findhora.equals("16:00-17:00")){
                return 81;

            }if(findhora.equals("17:00-18:00")){
                return 89;

            }if(findhora.equals("18:00-19:00")){
                return 97;

            }if(findhora.equals("19:00-20:00")){
                return 105;

            }if(findhora.equals("20:00-21:00")){
                return 113;

            }if(findhora.equals("21:00-22:00")){
                return 121;
            }
        }

        if(finddia.equals("Martes")){

            if(findhora.equals("7:00-8:00")){
                Log.v("entro", "lunes de 7 a 8");
                return 10;

            }if(findhora.equals("8:00-9:00")){
                return 18;

            }if(findhora.equals("9:00-10:00")){
                return 26;

            }if(findhora.equals("10:00-11:00")){
                return 34;

            }if(findhora.equals("11:00-12:00")){
                return 42;

            }if(findhora.equals("12:00-13:00")){
                return 50;

            }if(findhora.equals("13:00-14:00")){
                return 58;

            }if(findhora.equals("14:00-15:00")){
                return 66;

            }
            if(findhora.equals("15:00-16:00")){
                return 74;

            }if(findhora.equals("16:00-17:00")){
                return 82;

            }if(findhora.equals("17:00-18:00")){
                return 90;

            }if(findhora.equals("18:00-19:00")){
                return 98;

            }if(findhora.equals("19:00-20:00")){
                return 106;

            }if(findhora.equals("20:00-21:00")){
                return 114;

            }if(findhora.equals("21:00-22:00")){
                return 122;
            }
        }

        if(finddia.equals("Miercoles")){

            if(findhora.equals("7:00-8:00")){
                Log.v("entro", "lunes de 7 a 8");
                return 11;

            }if(findhora.equals("8:00-9:00")){
                return 19;

            }if(findhora.equals("9:00-10:00")){
                return 27;

            }if(findhora.equals("10:00-11:00")){
                return 35;

            }if(findhora.equals("11:00-12:00")){
                return 43;

            }if(findhora.equals("12:00-13:00")){
                return 51;

            }if(findhora.equals("13:00-14:00")){
                return 59;

            }if(findhora.equals("14:00-15:00")){
                return 67;

            }
            if(findhora.equals("15:00-16:00")){
                return 75;

            }if(findhora.equals("16:00-17:00")){
                return 83;

            }if(findhora.equals("17:00-18:00")){
                return 91;

            }if(findhora.equals("18:00-19:00")){
                return 99;

            }if(findhora.equals("19:00-20:00")){
                return 107;

            }if(findhora.equals("20:00-21:00")){
                return 115;

            }if(findhora.equals("21:00-22:00")){
                return 123;
            }
        }
        return 0;
    }
}





