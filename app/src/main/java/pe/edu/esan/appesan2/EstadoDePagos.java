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
 * Created by Diegoflg on 7/24/2015.
 */
public class EstadoDePagos extends Fragment {

    TextView tvpag1,tvpag2,tvpag3,tvpag4,tvpag5,tvpag6;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_estado, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);





        return rootView;


    }


    public void consultarpagos (View v){

        PagosBD adminP = new PagosBD(v.getContext(), "BDPAGOS", null, 1);
        SQLiteDatabase bdP = adminP.getWritableDatabase();


        Cursor filapago = bdP.rawQuery("select concepto,monto,vencimiento from Pagos", null);

        if (filapago.moveToFirst())
        {









        }

        bdP.close();
    }
}
