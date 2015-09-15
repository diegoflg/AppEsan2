package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by educacionadistancia on 15/09/2015.
 */
public class EstaMapas extends Fragment {
    private GoogleMap googleMap2;
    MapView mE;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estamapas, container, false);

        mE = (MapView) v.findViewById(R.id.mapEsta);
        mE.onCreate(savedInstanceState);
        mE.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap2 = mE.getMap();

        double latitude = -12.105019;
        double longitude = -76.961066;

        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("EL POLO");

        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        googleMap2.addMarker(marker);
        googleMap2.setMyLocationEnabled(true);

        googleMap2.getUiSettings().setZoomControlsEnabled(false);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-12.105019, -76.961066)).zoom(18).build();
        googleMap2.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        return v;

    }
    @Override
    public void onResume() {
        super.onResume();
        mE.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mE.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mE.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mE.onLowMemory();
    }

}
