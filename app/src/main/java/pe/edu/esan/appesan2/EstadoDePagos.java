package pe.edu.esan.appesan2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Diegoflg on 7/24/2015.
 */
public class EstadoDePagos extends Fragment {
    //Para fuente
    TextView ttvv, concepto, monto, vencimiento;
    TextView tvpag1,tvpag2,tvpag3,tvpag4,tvpag5,tvpag6,tvpag7,tvpag8,tvpag9,tvpag10,tvpag11,tvpag12;

    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_estado, container, false);


        setRetainInstance(true);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/3;

        sliding=(com.sothree.slidinguppanel.SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout);
        sliding.setAnchorPoint(0.3f);


        //FUENTE PARA TEXVIEWS :
        String font_pathTTV = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TTV = Typeface.createFromAsset(getActivity().getAssets(), font_pathTTV);
        ttvv= (TextView)rootView.findViewById(R.id.ttvv);
        ttvv.setTypeface(TTV);
        concepto= (TextView)rootView.findViewById(R.id.concepto);
        concepto.setTypeface(TTV);
        monto= (TextView) rootView.findViewById(R.id.monto);
        monto.setTypeface(TTV);
        vencimiento= (TextView) rootView.findViewById(R.id.vencimiento);
        vencimiento.setTypeface(TTV);

        String font_pathConcepto = "font/HelveticaNeue-Bold.ttf"; //ruta de la fuente
        Typeface TFC = Typeface.createFromAsset(getActivity().getAssets(), font_pathConcepto);

        String font_pathNumeros = "font/HelveticaNeue-CondensedBold.ttf"; //ruta de la fuente
        Typeface TFN = Typeface.createFromAsset(getActivity().getAssets(), font_pathNumeros);

        tvpag1=(TextView)rootView.findViewById(R.id.concepto1);
        tvpag1.setTypeface(TFC);
        tvpag2=(TextView)rootView.findViewById(R.id.monto1);
        tvpag2.setTypeface(TFN);
        tvpag3=(TextView)rootView.findViewById(R.id.vencimiento1);
        tvpag3.setTypeface(TFN);
        tvpag4=(TextView)rootView.findViewById(R.id.concepto2);
        tvpag4.setTypeface(TFC);
        tvpag5=(TextView)rootView.findViewById(R.id.monto2);
        tvpag5.setTypeface(TFN);
        tvpag6=(TextView)rootView.findViewById(R.id.vencimiento2);
        tvpag6.setTypeface(TFN);
        tvpag7=(TextView)rootView.findViewById(R.id.concepto3);
        tvpag7.setTypeface(TFC);
        tvpag8=(TextView)rootView.findViewById(R.id.monto3);
        tvpag8.setTypeface(TFN);
        tvpag9=(TextView)rootView.findViewById(R.id.vencimiento3);
        tvpag9.setTypeface(TFN);
        tvpag10=(TextView)rootView.findViewById(R.id.concepto4);
        tvpag10.setTypeface(TFC);
        tvpag11=(TextView)rootView.findViewById(R.id.monto4);
        tvpag11.setTypeface(TFN);
        tvpag12=(TextView)rootView.findViewById(R.id.vencimiento4);
        tvpag12.setTypeface(TFN);

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