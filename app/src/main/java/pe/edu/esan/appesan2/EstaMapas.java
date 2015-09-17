package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    double latitude;
    double longitude;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_estamapas, container, false);
        Bundle bundle = this.getArguments();
        String lugar = bundle.getString("lugar");
        mE = (MapView) v.findViewById(R.id.mapEsta);
        mE.onCreate(savedInstanceState);
        mE.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap2 = mE.getMap();


        if(lugar.equals("polo")){
            latitude = -12.098581;
            longitude = -76.970599;
            //-12.098581, -76.970599

            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("EL POLO");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerc));
            googleMap2.addMarker(marker);
            Log.i("LUGAR", "EL POLO");

        }else if(lugar.equals("alonso")){
            latitude = -12.105392;
            longitude = -76.963559;
            //-12.105392, -76.963559

            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("ALONSO DE MOLINA");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerv));
            googleMap2.addMarker(marker);
            Log.i("LUGAR", "ALONSO DE MOLINA");
        }

        googleMap2.setMyLocationEnabled(true);

        googleMap2.getUiSettings().setZoomControlsEnabled(false);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(18).build();
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
