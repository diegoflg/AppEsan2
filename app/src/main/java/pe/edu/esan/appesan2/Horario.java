package pe.edu.esan.appesan2;

import android.app.Activity;
import android.content.Context;
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
 * Clase que permitia la vista del horario del alumno registrado en la base de datos interna
 * Esta clase no se muestra actualmente en la aplicacion pero puede ser utilizada
 */
public class Horario extends Fragment {
    //Se declaran variables

    //Se crea un cuadro de texto
    private TextView tvHorario;

    //Se crea un elemento del tipo tabla
    private GridView gridView;

    //Se declara una variable del tipo texto
    String TAG="estado";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se crea una vista y se infla con el layout correspondiente
        View rootView = inflater.inflate(R.layout.lay_horario, container, false);

        //Se retiene el fragmento
        setRetainInstance(true);

        //Se declara una variable del tipo Zoom
        ZoomView zoomView;

        //Se crea un nuevo zoom
        zoomView = new ZoomView(getActivity());

        //Se añade el zoom a la vista
        zoomView.addView(rootView);

        //Se crea una cadena de texto en forma de matriz
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

        //Se da valor al gridview con el elemento definido en el layout
        gridView=(GridView)rootView.findViewById(R.id.gridView1);

        //Se crea un nuevo adaptador
        MyAdapter adapter = new MyAdapter(getActivity().getBaseContext(), R.layout.item, numbers);

        //Se le asigna un adaptador al elemento gridview
        gridView.setAdapter(adapter);


        String font_path = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TF = Typeface.createFromAsset(getActivity().getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //Se da valor al cuadro de texto segun el elemento en el layout
        tvHorario = (TextView)rootView.findViewById(R.id.textView2);

        //Se da color al texto dentro del cuadro de texto
        tvHorario.setTextColor(Color.parseColor("#C90039"));

        //Se le da una fuente al texto del cuadro de texto
        tvHorario.setTypeface(TF);

        //Retorna la vista del zoom
        return zoomView;
    }

    @Override
    public void onAttach(final Activity activity)
    {
        //Metodo adjutar
        super.onAttach(activity);
        Log.d(TAG, this + ": onAttach(" + activity + ")");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        //Metodo que restaura el estado del fragmento
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, this + ": onActivityCreated()");
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        //Metodo que crea el fragmento y lo guarda
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, this + ": onViewCreated()");
    }

    @Override
    public void onDestroyView()
    {
        //Metodo que destruye la vista
        super.onDestroyView();
        Log.d(TAG, this + ": onDestroyView()");
    }

    @Override
    public void onDetach()
    {
        //Metodo que desconecta el fragmento
        super.onDetach();
        Log.d(TAG, this + ": onDetach()");
    }

    @Override
    public void onStart()
    {
        //Metodo que inicia el fragmento
        super.onStart();
        Log.d(TAG, this + ": onStart()");
    }

    @Override
    public void onResume()
    {
        //Metodo que resume el fragmento
        super.onResume();
        Log.d(TAG, this + ": onResume()");
    }

    @Override
    public void onPause()
    {
        //Metodo que pausa el fragmento
        super.onPause();
        Log.d(TAG, this + ": onPause()");
    }

    @Override
    public void onStop()
    {
        //Metodo que detiene el fragmento
        super.onStop();
        Log.d(TAG, this + ": onStop()");
    }

    @Override
    public void onDestroy()
    {
        //Metodo que destruye el fragmento
        super.onDestroy();
        Log.d(TAG, this + ": onDestroy()");
    }

    public class MyAdapter extends ArrayAdapter<String> {
        //Declaracion de variables

        //Creacion de variable matriz de texto
        String[] objects;

        //Creacion de contexto
        Context context;

        //Metodo propio de la clase
        public MyAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
            //Creacion de variable del tipo de cuadro de texto
            TextView tv;

            String font_path = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
            Typeface fuenteGV = Typeface.createFromAsset(getActivity().getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

            //Verificaion de la vista del layout
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                tv = (TextView)inflater.inflate(R.layout.item,parent,false);
            } else {
                tv = (TextView) convertView;
            }

            //Se da valor al texto dentro del cuadro
            tv.setText(objects[position]);

            //Se da el tamaño del texto del cuadro
            tv.setTextSize(10);

            //Se da la posicion general del cuadro
            tv.setGravity(Gravity.CENTER);

            //Se da una altura al cuadro de texto
            tv.setHeight(50);

            //Se da un ancho al cuadro de texto
            tv.setWidth(70);

            //Se verifica las posiciones
            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                //Se da color de fondo al cuadro
                tv.setBackgroundColor(Color.parseColor("#C90039"));

                //Se da color al texto de cuadro
                tv.setTextColor(Color.WHITE);

                //Se le asigna la fuente al cuadro
                tv.setTypeface(fuenteGV);

            }else{
                //Se le da color al fondo del cuadro
                tv.setBackgroundColor(Color.WHITE);

                //Se le da color al texto del cuadro
                tv.setTextColor(Color.parseColor("#6B6C6E"));

                //Se le asigna la fuente al cuadro
                tv.setTypeface(fuenteGV);
            }

            if(position>16 && position<24){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            if(position>32 && position<40){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            if(position>48 && position<56){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            if(position>64 && position<72){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            if(position>80 && position<88){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            if(position>96 && position<104){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }
            if(position>112 && position<120){
                //Se le da un color de fondo al cuadro de texto
                tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
            }

            //Se crea la base de datos segun la clase HorarioBD
            HorarioBD adminH = new HorarioBD(getContext(), "BDHORARIO1", null, 1);

            //Se permite la escritura y lectura de la base de datos
            SQLiteDatabase bdH = adminH.getWritableDatabase();


            //Se crea el cursor y se le da valor
            Cursor filahorarios = bdH.rawQuery("select curso,dia,hora,salon from Horario" , null);
            Log.v("cursor", "cursor");

            //Se declaran variables de tipo texto
            String curs,di,hor,sa;

            if (filahorarios.moveToFirst())
            {
                //Se le asigna valores a los textos
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                //se crea una variable del tipo entero
                int pos;

                //Se le da valor al entero
                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));

                //Se verifica la posicion
                if(position==pos){
                    tv.setText(curs+sa);
                }


                filahorarios.moveToNext();

                //Se da valores a los textos
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);
                pos=encontrarposicion(di, hor);

                //Se verifica la posicion
                if(position==pos){
                    tv.setText(curs+sa);
                }

                filahorarios.moveToNext();
                //Se da valores a los textos
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));

                Log.v("dia", (filahorarios.getString(1)));
                Log.v("hor", (filahorarios.getString(2)));

                //Se verifica la posicion
                if(position==pos){
                    tv.setText(curs+sa);
                }

                filahorarios.moveToNext();

                //Se da valores a los textos
                curs=filahorarios.getString(0);
                di=filahorarios.getString(1);
                hor=filahorarios.getString(2);
                sa=filahorarios.getString(3);

                pos=encontrarposicion(di, hor);
                Log.v("pos", String.valueOf(pos));
                Log.v("dia", (filahorarios.getString(1)));
                Log.v("hor", (filahorarios.getString(2)));

                //Se verifica la posicion
                if(position==pos){
                    tv.setText(curs+sa);
                }
            }

            //Se cierra la base de datos
            bdH.close();

            //Retorna la vista del cuadro de texto
            return tv;
        }
    }

    //Encuentra la posicion del cuadro segun el dia y la hora
    public int encontrarposicion(String finddia,String findhora){

        //Si el dia encontrado es LUNES
        if(finddia.equals("Lunes")){
            //Dependiendo a la hora encontrada se devuelve el numero de posicion
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

        //Si el dia encontrado es MARTES
        if(finddia.equals("Martes")){
            //Dependiendo a la hora encontrada se devuelve el numero de posicion
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

        //Si el dia encontrado es MIERCOLES
        if(finddia.equals("Miercoles")){
            //Dependiendo a la hora encontrada se devuelve el numero de posicion
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





