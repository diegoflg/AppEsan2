package pe.edu.esan.appesan2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity2Activity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public int tipo=1;
    public int menu=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Intent i=getIntent();
        Bundle b=i.getExtras();

        tipo=b.getInt("tipo");

        Log.v("tipo", String.valueOf(tipo));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.esan);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.ini(tipo);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        onNavigationDrawerItemSelected(Datah.getInstance().getData());

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment=null;
        menu=position;

        if(tipo==1){

            switch (position){
                case 0:
                    fragment= new Noticia();
                    break;
                case 1:
                    fragment= new Horario();
                    break;
                case 2:
                    fragment= new Notas();
                    break;
                case 3:
                    fragment= new Calendario();
                    break;
                case 4:
                    fragment= new Cafeteria();
                    break;
                case 5:
                    fragment= new Mapa();
                    break;
                case 6:
                    fragment= new Directorio();
                    break;
                case 7:
                    fragment= new Talleres();
                    break;
                case 8:
                    fragment = new Biblioteca();
                    break;
                case 9:
                    fragment = new Impresiones();
                    break;


                case 10:
                    fragment = new CursosMooc();
                    break;
                case 11:
                    fragment = new EstadoDePagos();
                    break;
                case 12:
                    fragment = new ConexionEsan();
                    break;
                case 13:
                    fragment = new EducacionEjecutiva();
                    break;
                case 14:
                    fragment = new Fablab();
                    break;
                case 15:
                    fragment = new Dpa();
                    break;
                //case 14:
               //    fragment = new Gamificacion();
                 //   break;

            }

        }

        if(tipo==2){

            switch (position){
                case 0:
                    fragment= new Noticia();
                    break;
                case 1:
                    fragment= new Calendario();
                    break;
                case 2:
                    fragment= new Cafeteria();
                    break;
            }
        }


        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {

        if(tipo==1){

            String[] stringArray = getResources().getStringArray(R.array.section_titles);
            if (number >= 1) {
                mTitle = stringArray[number - 1];
            }
        }

        if(tipo==2){

            String[] stringArray = getResources().getStringArray(R.array.section_titles2);
            if (number >= 1) {
                mTitle = stringArray[number - 1];
            }
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_activity2, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            MainActivity2Activity.super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (menu==4){
            //user defined onBackPressed method. Not of Fragment.
            Cafeteria.onBackPressed();
    }else{

    }
}




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            int lang=Datah.getInstance().getLang();
            Datah.getInstance().setData(menu);


            Intent intent = getIntent();
            finish();
            startActivity(intent);


            switch(lang){
                case 0:

                    Locale locale = new Locale("es");
                    Locale.setDefault(locale);
                    Configuration mConfig = new Configuration();
                    mConfig.locale = locale;
                    getBaseContext().getResources().updateConfiguration(mConfig,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

                case 1:

                    Locale locale2 = new Locale("en");
                    Locale.setDefault(locale2);
                    Configuration mConfig2 = new Configuration();
                    mConfig2.locale = locale2;
                    getBaseContext().getResources().updateConfiguration(mConfig2,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

                case 2:

                    Locale locale3 = new Locale("fr");
                    Locale.setDefault(locale3);
                    Configuration mConfig3 = new Configuration();
                    mConfig3.locale = locale3;
                    getBaseContext().getResources().updateConfiguration(mConfig3,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

            }




        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            Datah.getInstance().setData(menu);
            int lang=Datah.getInstance().getLang();
            Intent intent = getIntent();
            finish();
            startActivity(intent);


            switch(lang){
                case 0:

                    Locale locale = new Locale("es");
                    Locale.setDefault(locale);
                    Configuration mConfig = new Configuration();
                    mConfig.locale = locale;
                    getBaseContext().getResources().updateConfiguration(mConfig,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

                case 1:

                    Locale locale2 = new Locale("en");
                    Locale.setDefault(locale2);
                    Configuration mConfig2 = new Configuration();
                    mConfig2.locale = locale2;
                    getBaseContext().getResources().updateConfiguration(mConfig2,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

                case 2:

                    Locale locale3 = new Locale("fr");
                    Locale.setDefault(locale3);
                    Configuration mConfig3 = new Configuration();
                    mConfig3.locale = locale3;
                    getBaseContext().getResources().updateConfiguration(mConfig3,
                            getBaseContext().getResources().getDisplayMetrics());

                    break;

            }


        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */


}
