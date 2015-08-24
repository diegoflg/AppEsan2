package pe.edu.esan.appesan2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
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

        mTabHost3.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(mTabHost3.getCurrentTab()==1){
                    waze();
                }
            }
        });
        return v;
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
            String url = "waze://?q=ESAN";
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

