package pe.edu.esan.appesan2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
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
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment=null;

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
                case 3:
                    fragment= new IngresoCafeteria();
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A placeholder fragment containing a simple view.
     */


}
