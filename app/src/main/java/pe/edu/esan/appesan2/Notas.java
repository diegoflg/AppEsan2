package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Diegoflg on 7/13/2015.
 *
 *
 */
public class Notas extends Fragment {
    private TextView curso1,curso2,curso3,curso4,ep1,ep2,ep3,ep4,ta1,ta2,ta3,ta4,ef1,ef2,ef3,ef4,pg1,pg2,pg3,pg4;

    //SOLO PARA CAMBIAR FUENTE:
    private TextView Cursos, EP, TA, EF,PG;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_nootas, container, false);
        curso1=(TextView)rootView.findViewById(R.id.curso1);
        curso2=(TextView)rootView.findViewById(R.id.curso2);
        curso3=(TextView)rootView.findViewById(R.id.curso3);
        curso4=(TextView)rootView.findViewById(R.id.curso4);

        ep1=(TextView)rootView.findViewById(R.id.ep1);
        ep2=(TextView)rootView.findViewById(R.id.ep2);
        ep3=(TextView)rootView.findViewById(R.id.ep3);
        ep4=(TextView)rootView.findViewById(R.id.ep4);

        ta1=(TextView)rootView.findViewById(R.id.ta1);
        ta2=(TextView)rootView.findViewById(R.id.ta2);
        ta3=(TextView)rootView.findViewById(R.id.ta3);
        ta4=(TextView)rootView.findViewById(R.id.ta4);

        ef1=(TextView)rootView.findViewById(R.id.ef1);
        ef2=(TextView)rootView.findViewById(R.id.ef2);
        ef3=(TextView)rootView.findViewById(R.id.ef3);
        ef4=(TextView)rootView.findViewById(R.id.ef4);

        pg1=(TextView)rootView.findViewById(R.id.pg1);
        pg2=(TextView)rootView.findViewById(R.id.pg2);
        pg3=(TextView)rootView.findViewById(R.id.pg3);
        pg4=(TextView)rootView.findViewById(R.id.pg4);

        //SOLO PARA LA FUENTE:
        String font_path = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TF = Typeface.createFromAsset(getActivity().getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        Cursos = (TextView)rootView.findViewById(R.id.Cursos);
        EP = (TextView)rootView.findViewById(R.id.EP);
        TA = (TextView)rootView.findViewById(R.id.TA);
        EF = (TextView)rootView.findViewById(R.id.EF);
        PG = (TextView)rootView.findViewById(R.id.PG);

        Cursos.setTypeface(TF);
        EP.setTypeface(TF);
        TA.setTypeface(TF);
        EF.setTypeface(TF);
        PG.setTypeface(TF);

        //Fuente para texto no TÍTULOS
        String rutafuente = "font/HelveticaNeue-Lightttf"; //ruta de la fuente
        Typeface tipo = Typeface.createFromAsset(getActivity().getAssets(),rutafuente);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        curso1.setTypeface(tipo);
        curso1.setTextColor(Color.parseColor("#6B6C6E"));
        curso2.setTypeface(tipo);
        curso2.setTextColor(Color.parseColor("#6B6C6E"));
        curso3.setTypeface(tipo);
        curso3.setTextColor(Color.parseColor("#6B6C6E"));
        curso4.setTypeface(tipo);
        curso4.setTextColor(Color.parseColor("#6B6C6E"));

        ep1.setTypeface(tipo);
        ep1.setTextColor(Color.parseColor("#6B6C6E"));
        ep2.setTypeface(tipo);
        ep2.setTextColor(Color.parseColor("#6B6C6E"));
        ep3.setTypeface(tipo);
        ep3.setTextColor(Color.parseColor("#6B6C6E"));
        ep4.setTypeface(tipo);
        ep4.setTextColor(Color.parseColor("#6B6C6E"));

        ta1.setTypeface(tipo);
        ta1.setTextColor(Color.parseColor("#6B6C6E"));
        ta2.setTypeface(tipo);
        ta2.setTextColor(Color.parseColor("#6B6C6E"));
        ta3.setTypeface(tipo);
        ta3.setTextColor(Color.parseColor("#6B6C6E"));
        ta4.setTypeface(tipo);
        ta4.setTextColor(Color.parseColor("#6B6C6E"));

        ef1.setTypeface(tipo);
        ef1.setTextColor(Color.parseColor("#6B6C6E"));
        ef2.setTypeface(tipo);
        ef2.setTextColor(Color.parseColor("#6B6C6E"));
        ef3.setTypeface(tipo);
        ef3.setTextColor(Color.parseColor("#6B6C6E"));
        ef4.setTypeface(tipo);
        ef4.setTextColor(Color.parseColor("#6B6C6E"));

        pg1.setTypeface(tipo);
        pg1.setTextColor(Color.parseColor("#6B6C6E"));
        pg2.setTypeface(tipo);
        pg2.setTextColor(Color.parseColor("#6B6C6E"));
        pg3.setTypeface(tipo);
        pg3.setTextColor(Color.parseColor("#6B6C6E"));
        pg4.setTypeface(tipo);
        pg4.setTextColor(Color.parseColor("#6B6C6E"));

        consultarnota(rootView);

        return rootView;
    }


    public void consultarnota (View v){

        NotasBD notas= new NotasBD(v.getContext(), "BDNOTAS2",null, 1);
        SQLiteDatabase bdn=notas.getWritableDatabase();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);


        Cursor filanotaas = bdn.rawQuery("select curso,ep,ta,ef,pg from Notas" , null);

        if (filanotaas.moveToFirst())
        {


                curso1.setText(filanotaas.getString(0));
                ep1.setText(filanotaas.getString(1));
                ta1.setText(filanotaas.getString(2));
                ef1.setText(filanotaas.getString(3));
                pg1.setText(filanotaas.getString(4));


            filanotaas.moveToNext();
            curso2.setText(filanotaas.getString(0));
            ep2.setText(filanotaas.getString(1));
            ta2.setText(filanotaas.getString(2));
            ef2.setText(filanotaas.getString(3));
            pg2.setText(filanotaas.getString(4));


            filanotaas.moveToNext();
            curso3.setText(filanotaas.getString(0));
            ep3.setText(filanotaas.getString(1));
            ta3.setText(filanotaas.getString(2));
            ef3.setText(filanotaas.getString(3));
            pg3.setText(filanotaas.getString(4));


            filanotaas.moveToNext();
            curso4.setText(filanotaas.getString(0));
            ep4.setText(filanotaas.getString(1));
            ta4.setText(filanotaas.getString(2));
            ef4.setText(filanotaas.getString(3));
            pg4.setText(filanotaas.getString(4));






        }

        bdn.close();
    }
}
