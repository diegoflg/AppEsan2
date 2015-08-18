package pe.edu.esan.appesan2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
                    //fragment= new Noticia();
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Noticia(), "Noticia").commit();
                    }

                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 1:
                    //fragment= new Horario();
                    //fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Horario")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Horario(), "Horario").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 2:
                    //fragment= new Notas();
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Notas")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Notas(), "Notas").commit();
                    }
                    //SE ESCONDEN:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 3:
                    //fragment= new Calendario();
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Calendario(), "Calendario").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 4:
                    //fragment= new Cafeteria();
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Cafeteria(), "Cafeteria").commit();
                    }
                    //SE ESCONDEN:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 5:
                    //fragment= new Mapa();
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Mapa(), "Mapas").commit();
                    }
                    //SE ESCONDEN:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;
                case 6:
                    //fragment= new Directorio();
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Directorio(), "Directorio").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 7:
                    //fragment= new Talleres();
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Talleres(), "Talleres").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 8:
                    //fragment = new Biblioteca();
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Biblioteca(), "Notas").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 9:
                    //fragment = new Impresiones();
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Impresiones(), "Impresiones").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 10:
                    //fragment = new CursosMooc();
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new CursosMooc(), "CursosMooc").commit();
                    }
                    //SE ESCONDEN:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 11:
                   //fragment = new EstadoDePagos();
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new EstadoDePagos(), "Estadodepagos").commit();
                    }
                   //SE ESCONDEN:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 12:
                    //fragment = new ConexionEsan();
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new ConexionEsan(), "ConexionEsan").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 13:
                    //fragment = new EducacionEjecutiva();
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new EducacionEjecutiva(), "EducacionEjecutiva").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 14:
                    //fragment = new Fablab();
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Fablab(), "FabLab").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                    }
                    break;


                case 15:
                    //fragment = new Dpa();
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("DPA")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Dpa(), "DPA").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Horario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Notas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Mapas") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Directorio") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Talleres") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Biblioteca") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Impresiones") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Impresiones")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("CursosMooc") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }
                    break;
                //case 14:
               //    fragment = new Gamificacion();
                 //   break;

            }

        }

        if(tipo==2){

            switch (position){
                case 0:
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Noticia(),"Noticia").commit();
                    }
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }
                    //fragment= new Noticia();
                    break;
                case 1:
                    if(fragmentManager.findFragmentByTag("Calendario") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Calendario(),"Calendario").commit();
                    }
                    if(fragmentManager.findFragmentByTag("Noticia") != null) {
                        //if the other fragment is visible, hide it.
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    //fragment= new Calendario();
                    break;
                case 2:
                    fragment= new Cafeteria();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    break;
            }
        }


        //fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentByTag("Noticia") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Noticia")).commit();
        }
        if(fragmentManager.findFragmentByTag("Horario") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Horario")).commit();
        }
        if(fragmentManager.findFragmentByTag("Notas") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Notas")).commit();
        }
        if(fragmentManager.findFragmentByTag("Calendario") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Calendario")).commit();
        }
        if(fragmentManager.findFragmentByTag("Cafeteria") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Cafeteria")).commit();
        }
        if(fragmentManager.findFragmentByTag("Mapas") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Mapas")).commit();
        }
        if(fragmentManager.findFragmentByTag("Directorio") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Directorio")).commit();
        }
        if(fragmentManager.findFragmentByTag("Talleres") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Talleres")).commit();
        }
        if(fragmentManager.findFragmentByTag("Biblioteca") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Biblioteca")).commit();
        }
        if(fragmentManager.findFragmentByTag("Impresiones") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Impresiones")).commit();
        }
        if(fragmentManager.findFragmentByTag("CursosMooc") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("CursosMooc")).commit();
        }
        if(fragmentManager.findFragmentByTag("Estadodepagos") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
        }
        if(fragmentManager.findFragmentByTag("ConexionEsan") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
        }
        if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
        }
        if(fragmentManager.findFragmentByTag("FabLab") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("FabLab")).commit();
        }
        if(fragmentManager.findFragmentByTag("DPA") != null){
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("DPA")).commit();
        }
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
     *
     *
     *
     if ( !isNetworkAvailable()) { // loading offline
     myWebView.loadUrl("http://blog.ue.edu.pe/");
     myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
     //myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
     Log.i(TAG, "SIN INTERNET");
     }else{
     Log.i(TAG, "CON INTERNET");
     myWebView.loadUrl("http://blog.ue.edu.pe/");

     myWebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );




     }
     * A placeholder fragment containing a simple view.
     */


}
