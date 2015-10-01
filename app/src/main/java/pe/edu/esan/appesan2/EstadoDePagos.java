package pe.edu.esan.appesan2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Modulo de estado de pagos que tiene una base de datos interna
 * Ya no esta siendo utilizado
 */
public class EstadoDePagos extends Fragment {
    //Declaracion de variables
    //Para fuente
    TextView ttvv, concepto, monto, vencimiento;
    TextView tvpag1,tvpag2,tvpag3,tvpag4,tvpag5,tvpag6,tvpag7,tvpag8,tvpag9,tvpag10,tvpag11,tvpag12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Se crea y se infla o da valor a la vista del fragmento con el layout correspondiente
        View rootView = inflater.inflate(R.layout.lay_estado, container, false);

        //Se retiene el estado del fragmento
        setRetainInstance(true);

        //FUENTE PARA TEXVIEWS :
        String font_pathTTV = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TTV = Typeface.createFromAsset(getActivity().getAssets(), font_pathTTV);

        //Se da valor al cuadro de texto segun el layout
        ttvv= (TextView)rootView.findViewById(R.id.ttvv);

        //Se asigna el tipo de fuente al cuadro de texto
        ttvv.setTypeface(TTV);

        //Se da valor al cuadro de texto segun el layout
        concepto= (TextView)rootView.findViewById(R.id.concepto);

        //Se asigna el tipo de fuente al cuadro de texto
        concepto.setTypeface(TTV);

        //Se da valor al cuadro de texto segun el layout
        monto= (TextView) rootView.findViewById(R.id.monto);

        //Se asigna el tipo de fuente al cuadro de texto
        monto.setTypeface(TTV);

        //Se da valor al cuadro de texto segun el layout
        vencimiento= (TextView) rootView.findViewById(R.id.vencimiento);

        //Se asigna el tipo de fuente al cuadro de texto
        vencimiento.setTypeface(TTV);

        String font_pathConcepto = "font/HelveticaNeue-Bold.ttf"; //ruta de la fuente
        Typeface TFC = Typeface.createFromAsset(getActivity().getAssets(), font_pathConcepto);

        String font_pathNumeros = "font/HelveticaNeue-CondensedBold.ttf"; //ruta de la fuente
        Typeface TFN = Typeface.createFromAsset(getActivity().getAssets(), font_pathNumeros);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag1=(TextView)rootView.findViewById(R.id.concepto1);

        //Se le asigna una fuente al cuadro de texto
        tvpag1.setTypeface(TFC);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag2=(TextView)rootView.findViewById(R.id.monto1);

        //Se le asigna una fuente al cuadro de texto
        tvpag2.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag3=(TextView)rootView.findViewById(R.id.vencimiento1);

        //Se le asigna una fuente al cuadro de texto
        tvpag3.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag4=(TextView)rootView.findViewById(R.id.concepto2);

        //Se le asigna una fuente al cuadro de texto
        tvpag4.setTypeface(TFC);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag5=(TextView)rootView.findViewById(R.id.monto2);

        //Se le asigna una fuente al cuadro de texto
        tvpag5.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag6=(TextView)rootView.findViewById(R.id.vencimiento2);

        //Se le asigna una fuente al cuadro de texto
        tvpag6.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag7=(TextView)rootView.findViewById(R.id.concepto3);

        //Se le asigna una fuente al cuadro de texto
        tvpag7.setTypeface(TFC);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag8=(TextView)rootView.findViewById(R.id.monto3);

        //Se le asigna una fuente al cuadro de texto
        tvpag8.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag9=(TextView)rootView.findViewById(R.id.vencimiento3);

        //Se le asigna una fuente al cuadro de texto
        tvpag9.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag10=(TextView)rootView.findViewById(R.id.concepto4);

        //Se le asigna una fuente al cuadro de texto
        tvpag10.setTypeface(TFC);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag11=(TextView)rootView.findViewById(R.id.monto4);

        //Se le asigna una fuente al cuadro de texto
        tvpag11.setTypeface(TFN);

        //Se da valor al cuadro de texto con el respectivo elemento en el layout
        tvpag12=(TextView)rootView.findViewById(R.id.vencimiento4);

        //Se le asigna una fuente al cuadro de texto
        tvpag12.setTypeface(TFN);

        //Se realiza el metodo en la vista dada en su parametro
        consultarpagos(rootView);

        //Retorna la vista del layout inflado en el fragmento
        return rootView;
    }

    public void consultarpagos (View v){
        //Metodo que sirve para consultar los datos de la base de datos

        //Se crea la base de datos a traves de la clase PagosBD
        PagosBD adminP = new PagosBD(v.getContext(), "BDPAGOS2", null, 1);

        //Permite obtener la forma de escritura de la base de datos asignada
        SQLiteDatabase bdP = adminP.getWritableDatabase();

        //Crea y da valor al cursor
        Cursor filapago = bdP.rawQuery("select concepto,monto,vencimiento from Pagos", null);

        if (filapago.moveToFirst())
        {
            Log.v("fila", "entro");
            Log.v("pos", String.valueOf(filapago.getString(0)));
            Log.v("pos", String.valueOf(filapago.getString(1)));
            Log.v("pos", String.valueOf(filapago.getString(2)));

            //Se da el valor del texto al cuadro de texto
            tvpag1.setText(filapago.getString(0));

            //Se da el valor del texto al cuadro de texto
            tvpag2.setText(filapago.getString(1));

            //Se da el valor del texto al cuadro de texto
            tvpag3.setText(filapago.getString(2));

            filapago.moveToNext();
            //Se da el valor del texto al cuadro de texto
            tvpag4.setText(filapago.getString(0));

            //Se da el valor del texto al cuadro de texto
            tvpag5.setText(filapago.getString(1));

            //Se da el valor del texto al cuadro de texto
            tvpag6.setText(filapago.getString(2));

            filapago.moveToNext();
            //Se da el valor del texto al cuadro de texto
            tvpag7.setText(filapago.getString(0));

            //Se da el valor del texto al cuadro de texto
            tvpag8.setText(filapago.getString(1));

            //Se da el valor del texto al cuadro de texto
            tvpag9.setText(filapago.getString(2));

            filapago.moveToNext();
            //Se da el valor del texto al cuadro de texto
            tvpag10.setText(filapago.getString(0));

            //Se da el valor del texto al cuadro de texto
            tvpag11.setText(filapago.getString(1));

            //Se da el valor del texto al cuadro de texto
            tvpag12.setText(filapago.getString(2));
        }
        //Se cierra la base de datos
        bdP.close();
    }
}