package pe.edu.esan.appesan2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Se crea la actividad
        super.onCreate(savedInstanceState);

        //Se da el contenido segun el layout dado
        setContentView(R.layout.activity_maps);

        //Llama al metodo con el mismo nombre
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        //Resume, regresa
        super.onResume();

        //LLama al metodo con el mismo nombre
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                //Llma al metodo con el mismo nombres
                setUpMap();

                //Permite obtener y mandar la locacion actual del usuario
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    private void setUpMap() {
        //Añade un marcador al mapa
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        //Permite obtener y enviar la locacion actual del usuario
        mMap.setMyLocationEnabled(true);
    }
}



