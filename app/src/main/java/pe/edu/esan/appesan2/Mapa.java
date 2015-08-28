package pe.edu.esan.appesan2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Matrix3f;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-12.105019, -76.961066)).zoom(18).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        //---------------------------------MAPA DE ESAN-------------------------------------
        imagenMapa = (ImageView)v.findViewById(R.id.imagenMapa);

        //----------------------------------------------------------------------------------

        listamapa = (ListView)v.findViewById(R.id.listamapa);
        String[] values = new String[]{"1 Rectorado","2 Vicerrectorado de Investigación","3 Vicerrectorado Académico","4 Counter Admision Pregrado", "5",
        "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
        "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listamapa.setAdapter(adapter);
        listamapa.setTextFilterEnabled(false);

        //final float lugarInicio[] = new float[]{1, 0, -218, 0, 1, -210, 0, 0, 1};
        //matrix.setValues(lugarInicio);
        //imagenMapa.setImageMatrix(matrix);
        //Matrix{[1.0, 0.0, -218.49846][0.0, 1.0, -211.48648][0.0, 0.0, 1.0]}
        final float ttt[] = new float[9];

        mTabHost3.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost3.getCurrentTab() == 1) {
                    waze();
                } else {
                    if (mTabHost3.getCurrentTab() == 2) {
                        //--------------------------ESAN MAPA-------------------------------
                        imagenMapa.setImageResource(R.drawable.mapaesan);

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


                                //float tx = ttt[Matrix.MTRANS_X];
                                //float ty = ttt[Matrix.MTRANS_Y];
                                //Log.i(TAG, "TRAS X:" + String.valueOf(tx));
                                //Log.i(TAG, "TRAS Y:" + String.valueOf(ty));

                                return true;
                            }
                        });
                    }
                }
            }
        });


        listamapa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int inicio;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        matrix.getValues(ttt);
                        final float[] tx = {ttt[Matrix.MTRANS_X]};
                        final float[] ty = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty[0]));

                        int txI = (int)tx[0];
                        int tyI = (int)ty[0];

                        int txF = txI+790;
                        int tyF = tyI+303;
                        //Matrix{[1.0, 0.0, -790.8795][0.0, 1.0, -303.7743][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF));

                        final int txD = txF/50;
                        final int tyD = tyF/50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 1 RECTORADO");
                                    tx[0] = tx[0] - txD;
                                    ty[0] = ty[0] - tyD;
                                    float values1[] = new float[]{1, 0, tx[0], 0, 1, ty[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        matrix.getValues(ttt);
                        break;
                    case 1:
                        matrix.getValues(ttt);
                        final float[] tx2 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty2 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx2[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty2[0]));

                        int txI2 = (int)tx2[0];
                        int tyI2 = (int)ty2[0];

                        int txF2 = txI2+821;
                        int tyF2 = tyI2+257;
                        //Matrix{[1.0, 0.0, -821.2681][0.0, 1.0, -257.27686][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF2));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF2));

                        final int txD2 = txF2/50;
                        final int tyD2 = tyF2/50;

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
                        matrix.getValues(ttt);
                        break;
                    case 2:
                        matrix.getValues(ttt);
                        final float[] tx3 = {ttt[Matrix.MTRANS_X]};
                        final float[] ty3 = {ttt[Matrix.MTRANS_Y]};
                        Log.i(TAG, "TRAS X:" + String.valueOf(tx3[0]));
                        Log.i(TAG, "TRAS Y:" + String.valueOf(ty3[0]));

                        int txI3 = (int)tx3[0];
                        int tyI3 = (int)ty3[0];

                        int txF3 = txI3+811;
                        int tyF3 = tyI3+368;
                        //Matrix{[1.0, 0.0, -811.1596][0.0, 1.0, -368.02783][0.0, 0.0, 1.0]}
                        Log.i(TAG, "NÚMERO ENTERO RESTADO X: " + String.valueOf(txF3));
                        Log.i(TAG, "NÚMERO ENTERO RESTADO Y: " + String.valueOf(tyF3));

                        final int txD3 = txF3/50;
                        final int tyD3 = tyF3/50;

                        for (inicio = 1; inicio < 51; inicio++) {
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "CASO 2 VICERECTORADO");
                                    tx3[0] = tx3[0] - txD3;
                                    ty3[0] = ty3[0] - tyD3;
                                    float values1[] = new float[]{1, 0, tx3[0], 0, 1, ty3[0], 0, 0, 1};
                                    matrix.setValues(values1);

                                    imagenMapa.setImageMatrix(matrix);
                                }
                            }, 50 * inicio);
                        }
                        matrix.getValues(ttt);
                        break;
                    case 3:
                        Log.i(TAG, "CASO 4 COUNTER ADMISIÓN PREGRADO");
                        float values4[] = new float[]{2, 0, -1234, 0, 2, -1090, 0, 0, 1};
                        matrix.setValues(values4);
                        imagenMapa.setImageMatrix(matrix);
                        // Matrix{[2.0, 0.0, -1234.3146][0.0, 2.0, -1089.7734][0.0, 0.0, 1.0]}

                        //float values3[] = new float[]{1, 0, -920,  0, 2, -821,   0, 0, 1};  PARA NÚMERO 15
                        //Matrix{[1.5498602, 0.0, -919.74603][0.0, 1.5498602, -821.2699][0.0, 0.0, 1.0]} PARA NÚMERO 15
                        break;
                    case 4:
                        Log.i(TAG, "CASO 5 EDIFICIO D");
                        float values5[] = new float[]{2, 0, -1358, 0, 2, -1022, 0, 0, 1};
                        matrix.setValues(values5);
                        imagenMapa.setImageMatrix(matrix);
                        //Matrix{[1.8273392, 0.0, -1358.7382][0.0, 1.8273392, -1022.03357][0.0, 0.0, 1.0]}

                        break;
                    case 5:
                        Log.i(TAG, "CASO 6 EDIFICIO A");
                        float values6[] = new float[]{2, 0, -1567, 0, 2, -1021, 0, 0, 1};
                        matrix.setValues(values6);
                        imagenMapa.setImageMatrix(matrix);
                        //MMatrix{[2.0, 0.0, -1567.3457][0.0, 2.0, -1021.3891][0.0, 0.0, 1.0]}
                        break;
                    case 6:
                        Log.i(TAG, "CASO 7 AULA C");
                        float values7[] = new float[]{2, 0, -1385, 0, 2, -807, 0, 0, 1};
                        matrix.setValues(values7);
                        imagenMapa.setImageMatrix(matrix);
                        //Matrix{[2.0, 0.0, -1385.1122][0.0, 2.0, -807.6029][0.0, 0.0, 1.0]}
                        break;
                    case 7:
                        Log.i(TAG, "CASO 8 AULA E");
                        float values8[] = new float[]{2, 0, -1425, 0, 2, -901, 0, 0, 1};
                        matrix.setValues(values8);
                        imagenMapa.setImageMatrix(matrix);
                        //Matrix{[2.0, 0.0, -1425.3995][0.0, 2.0, -901.4075][0.0, 0.0, 1.0]}
                        break;
                    case 8:
                        Log.i(TAG, "CASO 9 AULA D");
                        float values9[] = new float[]{2, 0, -1509, 0, 2, -907, 0, 0, 1};
                        matrix.setValues(values9);
                        imagenMapa.setImageMatrix(matrix);
                        //Matrix{[2.0, 0.0, -1508.6394][0.0, 2.0, -907.395][0.0, 0.0, 1.0]}
                        break;
                    case 9:
                        Log.i(TAG, "CASO 10 EDIFICIO B");
                        float values10[] = new float[]{2, 0, -1530, 0, 2, -1139, 0, 0, 1};
                        matrix.setValues(values10);
                        imagenMapa.setImageMatrix(matrix);
                        //Matrix{[2.2273455, 0.0, -1529.8878][0.0, 2.2273455, -1139.291][0.0, 0.0, 1.0]}
                        //Matrix{[2.0762625, 0.0, -1564.3815][0.0, 2.0762625, -938.5481][0.0, 0.0, 1.0]} 2DA

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


}


