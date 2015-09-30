package pe.edu.esan.appesan2;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class Mapa extends Fragment {
    //Declaración de variables
    TabHost mTabHost3;
    ImageView imagenMapa;
    com.sothree.slidinguppanel.SlidingUpPanelLayout sliding4;
    private static final int LONG_DELAY = 3500;

    private  final String TAG = "APP";
    // These matrices will be used to move and zoom image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;

    ListView listamapa;

    Handler handler1 = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.lay_mapa, container, false);

        //Retiene el estado del fragmento
        setRetainInstance(true);

        //Da valor a la variable anteriormente declarada con un elemento del layout dado
        mTabHost3 = (TabHost) v.findViewById(R.id.tabHost3);
        mTabHost3.setup();

        //Permite la existencia de dos opciones para el tabhost
        TabHost.TabSpec spec = mTabHost3.newTabSpec("Tab 1");
        //Añade el contenido al tab
        spec.setContent(R.id.mapaesan);
        //Añade el nombre del tab
        spec.setIndicator("Mapa interno");
        //Añade el tab al TabHost
        mTabHost3.addTab(spec);

        spec=mTabHost3.newTabSpec("Tab 2");
        //Añade el contenido al tab
        spec.setContent(R.id.waze);
        //Añade el nombre del tab
        spec.setIndicator("Esan en Waze");
        //Añade el tab al TabHost
        mTabHost3.addTab(spec);

        //El tabHost muestra el primer tab como vista principal, se cuenta desde 0
        mTabHost3.setCurrentTab(0);


        //FUENTE PARA TÍTULO EN TABHOST:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        for(int i=0;i<mTabHost3.getTabWidget().getChildCount();i++)
        { mTabHost3.getTabWidget().setStripEnabled(true);
            mTabHost3.getTabWidget().setRightStripDrawable(R.drawable.greyline);
            mTabHost3.getTabWidget().setLeftStripDrawable(R.drawable.greyline);

            TextView tv = (TextView) mTabHost3.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setTypeface(TFT);

        }
        mTabHost3.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffc90039")); //unselected
        mTabHost3.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffc90039")); // selected



        //---------------------------------MAPA DE ESAN-------------------------------------
        //Se da valor al elemento declarado buscandolo en el layout
        imagenMapa = (ImageView)v.findViewById(R.id.imagenMapa);

        //Se da una imagen al elemento ImageView
        imagenMapa.setImageResource(R.drawable.esanmap);

        //Sirve para obtener datos de altura y ancho de la pantalla del celular
        final float dpi = getResources().getDisplayMetrics().density;

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int aa=height/6;

        //Se da valor al elemento declarado buscandolo en el layout
        sliding4=(com.sothree.slidinguppanel.SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout4);
        sliding4.setAnchorPoint(aa);


        //----------------------------------------------------------------------------------
        //Se da valor al elemento buscandolo en el layout
        listamapa = (ListView)v.findViewById(R.id.listamapa);

        //Se crea una cadena de valores para la lista
        String[] values = new String[]{"PABELLON A", "PABELLÓN B", "AULAS C", "PABELLÓN D", "COMEDOR PRINCIPAL", "CAFETERÍA 338"};

        //Se crea un adaptador simple para la lista
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        //Se asigna el adaptador a la lista
        listamapa.setAdapter(adapter);

        listamapa.setTextFilterEnabled(false);

        //Se inicia el estado del slidingUpPanel en collapsed para que este cerrado
        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

        //Se crea una matriz de inicio para la ubicacion de la imagen
        float inicio[] = new float[]{1, 0, -209, 0, 1, -372, 0, 0, 1};

        //Se da los valores a la matriz
        matrix.setValues(inicio);

        //Se da la matriz con los valores a la imagen
        imagenMapa.setImageMatrix(matrix);

        //--------------------------ESAN MAPA-------------------------------
        //{[1.0, 0.0, -209.54346][0.0, 1.0, -371.75314][0.0, 0.0, 1.0]}

        imagenMapa.setOnTouchListener(new View.OnTouchListener() {
            //Si la imagen es tocada entra a este metodo

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Llama al metodo del mismo nombres
                dumpEvent(event);

                // Handle touch events here...
                //Permite manejar los tipos de toques que se le puede hacer a la imagen como mover y hacer zoom
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        savedMatrix.set(matrix);
                        start.set(event.getX(), event.getY());
                        Log.d(TAG, "mode=DRAG");
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        Log.d(TAG, "oldDist=" + oldDist);
                        if (oldDist > 10f) {
                            savedMatrix.set(matrix);
                            midPoint(mid, event);
                            mode = ZOOM;
                            Log.d(TAG, "mode=ZOOM");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        Log.d(TAG, "mode=NONE");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            // ...
                            matrix.set(savedMatrix);
                            matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            Log.d(TAG, "newDist=" + newDist);
                            if (newDist > 10f) {
                                matrix.set(savedMatrix);
                                float scale = newDist / oldDist;
                                matrix.postScale(scale, scale, mid.x, mid.y);
                            }
                        }
                        break;
                }
                //Le da el valor de la matriz a la imagen
                imagenMapa.setImageMatrix(matrix);

                Log.i(TAG, "LOCALIZADO EN: " + matrix);

                return true;
            }
        });



        mTabHost3.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //Cundo el tab es cambiado de mapa interno a waze

                //Cuando el tab actual escogido es Esan en waze entra
                if (mTabHost3.getCurrentTab() == 1) {
                    //Realiza el metodo con el mismo nombre
                    waze();

                    //Oculta el slidingUpPanel
                    sliding4.setVisibility(View.INVISIBLE);
                }
            }
        });


        listamapa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Cuando se da clic a un item en la lista del slidingUpPanel

            //Declaracion de variables
            int inicio;

            //Cadena de 9 numeros flotantes para una matriz
            final float ttt[] = new float[9];

            //Manejador de tiempo
            Handler H2 = new Handler();

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Segun la posicion del dato escogido de la lista entrara el caso con su respectiva posicion
                switch (position) {
                    case 0:
                        //PABELLON A

                        //Obtiene los valores de la matriz actual
                        matrix.getValues(ttt);

                        //Declara dos cadenas flotantes y obtiene los datos de la matriz
                        final float[] tx0 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty0 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx0[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty0[0]));

                        int txI0 = (int) tx0[0];
                        int tyI0 = (int) ty0[0];

                        // Se hace una operacin para definir cuanto le falta a la matriz actual para llegar a ser la matriz escogida
                        int txF0 = txI0 + (int)(419*(double)dpi);
                        int tyF0 = tyI0 + (int)(501*(double)dpi);
                        //Matrix{[1.0, 0.0, -419.24722][0.0, 1.0, -501.22787][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF0));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF0));

                        //Se divide entre 50 para que el handler funcione de manera que la imagen se desplace y muestre ese movimiento
                        //desde donde esta hacia donde debe llegar segun la opcion escogida de la lista
                        final int txD0 = txF0 / 50;
                        final int tyD0 = tyF0 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "PABELLON A");
                                    tx0[0] = tx0[0] - txD0;
                                    ty0[0] = ty0[0] - tyD0;
                                    float values1[] = new float[]{1, 0, tx0[0], 0, 1, ty0[0], 0, 0, 1};
                                    matrix.setValues(values1);
                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Muestra el toast con la imagen del lugar escogido despues de 2.4 segundos
                                toastshow(getActivity(), 0);
                            }
                        },2400
                        );

                        //Obtiene los valores dela matriz actual
                        matrix.getValues(ttt);

                        //Se colapsa el slidingUpPanel para una mejor visualizacion del mapa
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;

                    case 1:
                        //PABELLON B
                        matrix.getValues(ttt);
                        final float[] tx1 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty1 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx1[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty1[0]));

                        int txI1 = (int) tx1[0];
                        int tyI1 = (int) ty1[0];

                        int txF1 = txI1 + (int)(407*(double)dpi);
                        int tyF1 = tyI1 + (int)(551*(double)dpi);
                        //Matrix{[1.0, 0.0, -406.66956][0.0, 1.0, -551.1239][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF1));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF1));

                        final int txD1 = txF1 / 50;
                        final int tyD1 = tyF1 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "PABELLON B");
                                    tx1[0] = tx1[0] - txD1;
                                    ty1[0] = ty1[0] - tyD1;
                                    float values1[] = new float[]{1, 0, tx1[0], 0, 1, ty1[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                         //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                         toastshow(getActivity(), 1);
                        }
                        },2400
                        );
                        matrix.getValues(ttt);
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;

                    case 2:
                        //AULAS C
                        matrix.getValues(ttt);
                        final float[] tx2 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty2 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx2[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty2[0]));

                        int txI2 = (int) tx2[0];
                        int tyI2 = (int) ty2[0];

                        int txF2 = txI2 + (int)(250*(double)dpi);
                        int tyF2 = tyI2 + (int)(224*(double)dpi);
                        //Matrix{[1.0, 0.0, -250.15863][0.0, 1.0, -224.30475][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF2));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF2));

                        final int txD2 = txF2 / 50;
                        final int tyD2 = tyF2 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 2 VICERECTORADO");
                                    tx2[0] = tx2[0] - txD2;
                                    ty2[0] = ty2[0] - tyD2;
                                    float values1[] = new float[]{1, 0, tx2[0], 0, 1, ty2[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                                               toastshow(getActivity(), 2);
                                           }
                                       },2400
                        );
                        matrix.getValues(ttt);
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;

                    case 3:
                        //PABELLÓN D
                        matrix.getValues(ttt);
                        final float[] tx3 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty3 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx3[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty3[0]));

                        int txI3 = (int) tx3[0];
                        int tyI3 = (int) ty3[0];

                        int txF3 = txI3 + (int)(286*(double)dpi);
                        int tyF3 = tyI3 + (int)(517*(double)dpi);
                        //Matrix{[1.0, 0.0, -286.18173][0.0, 1.0, -517.15027][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF3));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF3));

                        final int txD3 = txF3 / 50;
                        final int tyD3 = tyF3 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 3");
                                    tx3[0] = tx3[0] - txD3;
                                    ty3[0] = ty3[0] - tyD3;
                                    float values1[] = new float[]{1, 0, tx3[0], 0, 1, ty3[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                                               toastshow(getActivity(), 3);
                                           }
                                       },2400
                        );
                        matrix.getValues(ttt);
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                    case 4:
                        //COMEDOR PRINCIPAL
                        matrix.getValues(ttt);
                        final float[] tx4 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty4 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx4[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty4[0]));

                        int txI4 = (int) tx4[0];
                        int tyI4 = (int) ty4[0];

                        int txF4 = txI4 + (int)(295*(double)dpi);
                        int tyF4 = tyI4 + (int)(395*(double)dpi);
                        //Matrix{[1.0, 0.0, -295.18204][0.0, 1.0, -394.76968][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF4));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF4));

                        final int txD4 = txF4 / 50;
                        final int tyD4 = tyF4 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 3");
                                    tx4[0] = tx4[0] - txD4;
                                    ty4[0] = ty4[0] - tyD4;
                                    float values1[] = new float[]{1, 0, tx4[0], 0, 1, ty4[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                                               toastshow(getActivity(), 4);
                                           }
                                       },2400
                        );
                        matrix.getValues(ttt);
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;

                    case 5:
                        //CAFETERÍA 338
                        matrix.getValues(ttt);
                        final float[] tx5 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty5 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx5[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty5[0]));

                        int txI5 = (int) tx5[0];
                        int tyI5 = (int) ty5[0];

                        int txF5 = txI5 + (int)(337*(double)dpi);
                        int tyF5 = tyI5 + (int)(356*(double)dpi);
                        //Matrix{[1.0, 0.0, -337.12903][0.0, 1.0, -355.8181][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF5));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF5));

                        final int txD5 = txF5 / 50;
                        final int tyD5 = tyF5 / 50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 3");
                                    tx5[0] = tx5[0] - txD5;
                                    ty5[0] = ty5[0] - tyD5;
                                    float values1[] = new float[]{1, 0, tx5[0], 0, 1, ty5[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        H2.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                                               toastshow(getActivity(), 5);
                                           }
                                       },2400
                        );
                        matrix.getValues(ttt);
                        sliding4.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        break;
                }
            }
        });

        return v;
    }


    //Los siguientes metodos son sacados de: http://stackoverflow.com/questions/28190319/moving-image-with-its-touch-event-in-android
    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    //Metodo que abre el mapa de Esan en Waze
    public void waze(){
        try
        {
            //Abre Waze con el mapa de ESAN si es que esta instalada la aplicacion de Waze
            //String url = "waze://?ll=<-12.105019>,<-76.961066>&z=10";
            String url = "waze://?ll=-12.105,-76.961&z=10";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(url) );
            startActivity( intent );
        }
        catch ( ActivityNotFoundException ex  )
        {
            //Abre Waze en el Play Store en caso de no tener instalada la app Waze
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
            startActivity(intent);
        }
    }

    public void toastshow(final Activity context, int caso){
        //Muestra un Toast o mensaje en pantalla el cual contendra la imagen del lugar y su nombre respectivo segun se
        //escoga en la lista de datos

        //Se da un elemento del layout
        RelativeLayout toastR = (RelativeLayout) context.findViewById(R.id.toast);

        //Se obtiene el System Service
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Se da valor a la vista con el layout
        View view = layoutInflater.inflate(R.layout.toastmapa,toastR);

        //Se declara y se da valor a una vista de imagen con el elemento del mismo tipo en el layout
        ImageView  imview1= (ImageView) view.findViewById(R.id.imageView1);

        //Se declara y se da valor a un cuadro de texto con el elemento del mismo tipo en el layout
        TextView tv1 = (TextView) view.findViewById(R.id.tvdelmapa);

        //FUENTE PARA TEXTOS:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);
        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //Se asigna un tipo de fuente al cuadro de texto
        tv1.setTypeface(TT);

        //Se le da color a la fuente del cuadro de texto
        tv1.setTextColor(Color.parseColor("#000000"));

        //Segun el dato escogido de la lista se mostraran una imagen y texto
        switch (caso){
            case 0:
                //Se da una imagen del edificio A al elemento
                imview1.setImageResource(R.drawable.edificioa);

                //Se da un texto para el cuadro de texto
                tv1.setText("PABELLÓN A");

                break;
            case 1:
                //Se da una imagen del edificio B al elemento
                imview1.setImageResource(R.drawable.edificiob);

                //Se da un texto para el cuadro de texto
                tv1.setText("PABELLÓN B");
                break;
            case 2:
                //Se da una imagen de los salones C al elemento
                imview1.setImageResource(R.drawable.pabelloncc);

                //Se da un texto para el cuadro de texto
                tv1.setText("SALONES C");
                break;
            case 3:
                //Se da una imagen del edificio D al elemento
                imview1.setImageResource(R.drawable.edificiodd);

                //Se da un texto para el cuadro de texto
                tv1.setText("PABELLÓN D");
                break;
            case 4:
                //Se da una imagen del comedor al elemento
                imview1.setImageResource(R.drawable.comedor);

                //Se da un texto para el cuadro de texto
                tv1.setText("COMEDOR");
                break;
            case 5:
                //Se da una imagen de la cafeteria al elemento
                imview1.setImageResource(R.drawable.cafe);

                //Se da un texto para el cuadro de texto
                tv1.setText("CAFETERÍA 338");
                break;
        }

        //Se declara y crea un toast en el fragmento actual
        Toast toast = new Toast(context);
        //Se le da una vista al toast
        toast.setView(view);

        //Se le asigna una duracion al toast
        toast.setDuration(Toast.LENGTH_LONG);

        //Aparece el toast
        toast.show();
    }

}


