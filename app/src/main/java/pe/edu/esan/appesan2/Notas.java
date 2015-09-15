package pe.edu.esan.appesan2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOError;
import java.io.IOException;
import java.util.Map;


public class Notas extends Fragment {


    private TextView Cursos, EP, TA, EF, PG;
    String user,pass;
    ListView listacursos;
    String[] values;
    String[] clinks;
    int contador=0;
    int poss=0;
    TextView tvfg;

    String curss;
    String notss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_nootas, container, false);
        listacursos=(ListView)v.findViewById(R.id.listacursos);
        setRetainInstance(true);

        tvfg=(TextView)v.findViewById(R.id.fgh);
        tvfg.setVisibility(View.INVISIBLE);

        listacursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                poss=position;
                curss=listacursos.getItemAtPosition(position).toString();
                new open2().execute();






            }
        });




        user=Datah.getInstance().getUser();
        pass=Datah.getInstance().getPass();


        new open().execute();


        return v;
    }





    private class open extends AsyncTask<String, Void, String> {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();


        }

        @Override
        protected String doInBackground(String... params) {
            Log.v("v1", "paso2");

            try {

                Connection.Response res1 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php").method(Connection.Method.GET).timeout(10000).execute();
                Document doc = res1.parse();
                Map welcomCookies = res1.cookies();



                Connection.Response res2 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php")
                        .data("username", user)
                        .data("password", pass)
                        .cookies(welcomCookies)
                        .timeout(10000)
                        .method(Connection.Method.POST)
                        .execute();

                String MoodleSessionesanvirtual = res2.cookie("MoodleSessionesanvirtual");

                Document doc2 = res2.parse();
                Log.v("titulo", doc2.title());





                Document doc5 = Jsoup.connect("http://esanvirtual.edu.pe/")
                        .cookie("MoodleSessionesanvirtual", MoodleSessionesanvirtual)
                        .timeout(10000)
                        .get();
                Log.v("titulo5", doc5.title());

                String ytr=doc5.select("div[class=row subcategorias]").text();

                Log.v("APP","ytr: "+ytr);
                Log.v("APP","lenght: "+String.valueOf(ytr.length()));



                if(ytr.length()>141){

                    Element head=doc5.select("div[class=row subcategorias]").get(4);
                    Elements links = head.select("a[href]");

                    for (Element element2 : links){
                        contador=contador+1;
                    }

                    values = new String[contador];
                    clinks = new String[contador];
                    contador=0;


                    for (Element element : links){



                        Log.v("links", element.ownText()+"    link: "+element.attr("abs:href").substring(element.attr("abs:href").indexOf("="))+"   "+contador);

                        values[contador]=element.ownText();
                        clinks[contador]=element.attr("abs:href").substring(element.attr("abs:href").indexOf("="));


                        contador=contador+1;

                    }





                }











            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();


            if(values==null){

                tvfg.setVisibility(View.VISIBLE);
                listacursos.setVisibility(View.INVISIBLE);

            }else{
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
                listacursos.setAdapter(adapter);

            }

            contador=0;


        }




    }

    private class open2 extends AsyncTask<String, Void, String> {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Please wait, Loading Page...", true);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {


            try {

                Connection.Response res1 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php").method(Connection.Method.GET).timeout(10000).execute();
                Document doc = res1.parse();
                Map welcomCookies = res1.cookies();



                Connection.Response res2 = Jsoup.connect("http://esanvirtual.edu.pe/login/index.php")
                        .data("username", user)
                        .data("password", pass)
                        .cookies(welcomCookies)
                        .timeout(10000)
                        .method(Connection.Method.POST)
                        .execute();

                String MoodleSessionesanvirtual = res2.cookie("MoodleSessionesanvirtual");

                Document doc2 = res2.parse();
                Log.v("titulo", doc2.title());





                Document doc5 = Jsoup.connect("http://esanvirtual.edu.pe/grade/report/user/index.php?id"+clinks[poss])
                        .cookie("MoodleSessionesanvirtual", MoodleSessionesanvirtual)
                        .timeout(10000)
                        .get();
                Log.v("tableeee", doc5.select("tr").last().text());

                notss=doc5.select("tr").last().text();




            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            showPopup(getActivity(), curss,notss);




        }
    }

    private void showPopup(final Activity context,String curso,String nota) {



        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        double width = displaymetrics.widthPixels;

        Log.v("tamano",String.valueOf(height));
        Log.v("tamano",String.valueOf(width));

        double popupHeight = height*0.52;
        double popupWidth = width*0.625;




        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup2);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup2, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth((int)Math.round(popupHeight));
        popup.setHeight((int)Math.round(popupWidth));
        popup.setFocusable(true);

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // FUENTE PARA TEXTO EN POPUP Y BOTONES:
        String font_pathPP = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TPP = Typeface.createFromAsset(getActivity().getAssets(),font_pathPP);//llamanos a la CLASS TYPEFACE y la definimos
        // con un CREATE desde ASSETS con la ruta STRING

        // Getting a reference to Close button, and close the popup when clicked.

        Button close = (Button) layout.findViewById(R.id.close);

        close.setTypeface(TPP);


        TextView tv1 = (TextView) layout.findViewById(R.id.poptv1);
        tv1.setTypeface(TPP);
        TextView tv2 = (TextView) layout.findViewById(R.id.poptv2);
        tv2.setTypeface(TPP);


                tv1.setText(curso);
                tv2.setText(nota);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


    }



}



/**
 * Created by Diegoflg on 7/13/2015.
 *

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
        String rutafuente = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
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
 *
 */