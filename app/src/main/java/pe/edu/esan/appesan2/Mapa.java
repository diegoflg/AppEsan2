package pe.edu.esan.appesan2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapa extends Fragment {
    private GoogleMap googleMap;
    MapView m;
    TabHost mTabHost3;
    ImageView imagenMapa;

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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setRetainInstance(true);
        mTabHost3 = (TabHost) v.findViewById(R.id.tabHost3);
        mTabHost3.setup();

        TabHost.TabSpec spec = mTabHost3.newTabSpec("Tab 1");
        spec.setContent(R.id.google);
        spec.setIndicator("GoogleMaps");
        mTabHost3.addTab(spec);

        spec=mTabHost3.newTabSpec("Tab 2");
        spec.setContent(R.id.waze);
        spec.setIndicator("Waze");
        mTabHost3.addTab(spec);

        spec=mTabHost3.newTabSpec("Tab 3");
        spec.setContent(R.id.mapaesan);
        spec.setIndicator("MapaEsan");
        mTabHost3.addTab(spec);

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
        mTabHost3.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffc90039")); // selected

        m = (MapView) v.findViewById(R.id.mapView);
        m.onCreate(savedInstanceState);
        m.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = m.getMap();

        double latitude = -12.105019;
        double longitude = -76.961066;

        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("UNIVERSIDAD ESAN");

        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        googleMap.addMarker(marker);
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setZoomControlsEnabled(false);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-12.105019, -76.961066)).zoom(18).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        //---------------------------------MAPA DE ESAN-------------------------------------
        imagenMapa = (ImageView)v.findViewById(R.id.imagenMapa);

        final float dpi = getResources().getDisplayMetrics().density;



        //----------------------------------------------------------------------------------

        listamapa = (ListView)v.findViewById(R.id.listamapa);
        String[] values = new String[]{"PABELLON A", "PABELLÓN B", "AULAS C", "PABELLÓN D", "COMEDOR PRINCIPAL", "CAFETERÍA 338"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listamapa.setAdapter(adapter);
        listamapa.setTextFilterEnabled(false);

        mTabHost3.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost3.getCurrentTab() == 1) {
                    waze();
                } else {
                    if (mTabHost3.getCurrentTab() == 2) {


                        //--------------------------ESAN MAPA-------------------------------
                        imagenMapa.setImageResource(R.drawable.esanmap);

                        imagenMapa.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                dumpEvent(event);

                                // Handle touch events here...
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
                                imagenMapa.setImageMatrix(matrix);
                                Log.i(TAG, "LOCALIZADO EN: " + matrix);

                                return true;
                            }
                        });
                    }
                }
            }
        });


        listamapa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int inicio;
            final float ttt[] = new float[9];
            Handler H2 = new Handler();

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                switch (position) {
                    case 0:
                        //PABELLON A
                        matrix.getValues(ttt);
                        final float[] tx0 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty0 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx0[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty0[0]));

                        int txI0 = (int) tx0[0];
                        int tyI0 = (int) ty0[0];

                        int txF0 = txI0 + (int)(419*(double)dpi);
                        int tyF0 = tyI0 + (int)(501*(double)dpi);
                        //Matrix{[1.0, 0.0, -419.24722][0.0, 1.0, -501.22787][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF0));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF0));

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
                                //Toast.makeText(getActivity(), "SALE IMAGEN", Toast.LENGTH_SHORT).show();
                                toastshow(getActivity(), 0);
                            }
                        },2400
                        );

                        matrix.getValues(ttt);

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
                        break;
                }
            }
        });

        return v;
    }

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

    @Override
    public void onResume() {
        super.onResume();
        m.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        m.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        m.onLowMemory();
    }


    public void waze(){
        try
        {
            //String url = "waze://?ll=<-12.105019>,<-76.961066>&z=10";
            String url = "waze://?ll=-12.105,-76.961&z=10";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(url) );
            startActivity( intent );
        }
        catch ( ActivityNotFoundException ex  )
        {
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
            startActivity(intent);
        }
    }

    public void toastshow(final Activity context, int caso){

        RelativeLayout toastR = (RelativeLayout) context.findViewById(R.id.toast);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.toastmapa,toastR);
        ImageView  imview1= (ImageView) view.findViewById(R.id.imageView1);
        TextView tv1 = (TextView) view.findViewById(R.id.tvdelmapa);
        //FUENTE PARA TEXTOS:
        String font_pathT = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TT = Typeface.createFromAsset(getActivity().getAssets(),font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING
        tv1.setTypeface(TT);
        tv1.setTextColor(Color.parseColor("#000000"));


        switch (caso){
            case 0:
                imview1.setImageResource(R.drawable.edificioa);
                tv1.setText("PABELLÓN A");
                break;
            case 1:
                imview1.setImageResource(R.drawable.edificiob);
                tv1.setText("PABELLÓN B");
                break;
            case 2:
                imview1.setImageResource(R.drawable.pabelloncc);
                tv1.setText("SALONES C");
                break;
            case 3:
                imview1.setImageResource(R.drawable.edificiodd);
                tv1.setText("PABELLÓN D");
                break;
            case 4:
                imview1.setImageResource(R.drawable.comedor);
                tv1.setText("COMEDOR");
                break;
            case 5:
                imview1.setImageResource(R.drawable.cafe);
                tv1.setText("CAFETERÍA 338");
                break;
        }

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.show();
    }

}


