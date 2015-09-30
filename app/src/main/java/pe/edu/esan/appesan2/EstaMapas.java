package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * Clase en la que se definen los mapas de Google Maps para el modulo de estacionamiento
 */
public class EstaMapas extends Fragment {
    //Declaracion de variables
    private GoogleMap googleMap2;
    MapView mE;
    double latitude;
    double longitude;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se infla la vista del layout
        View v = inflater.inflate(R.layout.lay_estamapas, container, false);

        //Se crea un paquete de datos
        Bundle bundle = this.getArguments();

        //Se crea y obtiene un valor dado en el modulo para reconocer el lugar escogido
        String lugar = bundle.getString("lugar");

        //Se infla o da valor a la vista del mapa
        mE = (MapView) v.findViewById(R.id.mapEsta);

        //se guarda el estado del mapa
        mE.onCreate(savedInstanceState);

        //se resume el estado del mapa
        mE.onResume();

        try {
            //Trata de inicializar el mapa en la vista actual
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            //Caso contrario da el error en la consola
            e.printStackTrace();
        }

        //Obtiene el mapa
        googleMap2 = mE.getMap();

    //el bundle es leido y depende de su valor para hacer lo siguiente
        //Si su valor es: polo
        if(lugar.equals("polo")){
            //La latitud sera:
            latitude = -12.098581;

            //La longitud sera:
            longitude = -76.970599;
            //-12.098581, -76.970599

            //Se creara un marcador de lugar en el lugar exacto de los dos parametros dados anteriormente y se dara como titulo el lugar escogido
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("EL POLO");

            //Se dara una imagen de vista al marcador que en este caso sera el color celeste
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerc));

            //Se añadira el maracador al mapa
            googleMap2.addMarker(marker);

            Log.i("LUGAR", "EL POLO");

        }else if(lugar.equals("alonso")){
            latitude = -12.105392;
            longitude = -76.963559;
            //-12.105392, -76.963559

            //Se creara un marcador de lugar en el lugar exacto de los dos parametros dados anteriormente y se dara como titulo el lugar escogido
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("ALONSO DE MOLINA");

            //Se dara una imagen de vista al marcador que en este caso sera el color verde
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerv));

            //Se añadira el marcador al mapa
            googleMap2.addMarker(marker);

            Log.i("LUGAR", "ALONSO DE MOLINA");
        }

        //Se podra obtener y enviar la locacion actual del celular del usuario
        googleMap2.setMyLocationEnabled(true);

        //Los controles de zoom no se veran en pantalla pero si se podra usar el zoom sin problema alguno
        googleMap2.getUiSettings().setZoomControlsEnabled(false);

        //Se determinara donde se encontrara la posicion de la camara que seria la latitide y longitud dadas dependiendo al lugar escogido
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(18).build();

        //Se hrar la animacion del movimiento de la camara del mapa
        googleMap2.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        return v;

    }

    @Override
    public void onResume() {
        //Resume o retorna el estado
        super.onResume();
        mE.onResume();
    }

    @Override
    public void onPause() {
        //Se pausa
        super.onPause();
        mE.onPause();
    }

    @Override
    public void onDestroy() {
        //Se destruye
        super.onDestroy();
        mE.onDestroy();
    }

    @Override
    public void onLowMemory() {
        //Cuando hay poca memoria
        super.onLowMemory();
        mE.onLowMemory();
    }

}
