package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	/**
	 * Esta clase no se usa, se usaba para mostrar un logo antes de iniciar la aplicacion
	 */

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lanzarThread();
    }
    
    private void lanzarThread(){
    	Thread timer = new Thread(){
    		public void run(){
    			try {
					sleep(2000); //espera de 2 segundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(Splash.this, MainActivity.class);
					startActivity(intent);
				}
    		}
    	};
    	
    	timer.start();
    }

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
 

    
}
