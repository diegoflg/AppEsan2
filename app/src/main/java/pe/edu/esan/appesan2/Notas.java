package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
