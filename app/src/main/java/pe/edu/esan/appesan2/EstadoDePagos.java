package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Diegoflg on 7/24/2015.
 */
public class EstadoDePagos extends Fragment {

    TextView tvpag1,tvpag2,tvpag3,tvpag4,tvpag5,tvpag6,tvpag7,tvpag8,tvpag9,tvpag10,tvpag11,tvpag12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_estado, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        tvpag1=(TextView)rootView.findViewById(R.id.concepto1);
        tvpag2=(TextView)rootView.findViewById(R.id.monto1);
        tvpag3=(TextView)rootView.findViewById(R.id.vencimiento1);
        tvpag4=(TextView)rootView.findViewById(R.id.concepto2);
        tvpag5=(TextView)rootView.findViewById(R.id.monto2);
        tvpag6=(TextView)rootView.findViewById(R.id.vencimiento2);
        tvpag7=(TextView)rootView.findViewById(R.id.concepto3);
        tvpag8=(TextView)rootView.findViewById(R.id.monto3);
        tvpag9=(TextView)rootView.findViewById(R.id.vencimiento3);
        tvpag10=(TextView)rootView.findViewById(R.id.concepto4);
        tvpag11=(TextView)rootView.findViewById(R.id.monto4);
        tvpag12=(TextView)rootView.findViewById(R.id.vencimiento4);

        consultarpagos(rootView);
        return rootView;
    }

    public void consultarpagos (View v){

        PagosBD adminP = new PagosBD(v.getContext(), "BDPAGOS2", null, 1);
        SQLiteDatabase bdP = adminP.getWritableDatabase();

        Cursor filapago = bdP.rawQuery("select concepto,monto,vencimiento from Pagos", null);

        if (filapago.moveToFirst())
        {
            Log.v("fila", "entro");
            Log.v("pos", String.valueOf(filapago.getString(0)));
            Log.v("pos", String.valueOf(filapago.getString(1)));
            Log.v("pos", String.valueOf(filapago.getString(2)));

            tvpag1.setText(filapago.getString(0));
            tvpag2.setText(filapago.getString(1));
            tvpag3.setText(filapago.getString(2));

            filapago.moveToNext();
            tvpag4.setText(filapago.getString(0));
            tvpag5.setText(filapago.getString(1));
            tvpag6.setText(filapago.getString(2));

            filapago.moveToNext();
            tvpag7.setText(filapago.getString(0));
            tvpag8.setText(filapago.getString(1));
            tvpag9.setText(filapago.getString(2));

            filapago.moveToNext();
            tvpag10.setText(filapago.getString(0));
            tvpag11.setText(filapago.getString(1));
            tvpag12.setText(filapago.getString(2));
        }
        bdP.close();
    }
}