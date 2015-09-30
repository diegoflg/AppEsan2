package pe.edu.esan.appesan2;

import android.app.Activity;
import android.os.Bundle;

/**
 * Clase que se creo para mostrar avisos fuera de la aplicacion, actualmente no se usa
 */
public class NotificationView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }
}
