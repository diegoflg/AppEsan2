package pe.edu.esan.appesan2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;



public class MainActivity2Activity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static String TAG = "MUNDO ESAN";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    int prueba=0;

    private Noticia mTaskFragment1;
    private Horario mTaskFragment2;
    private Notas mTaskFragment3;
    private Fragment mTaskFragment4;
    private Cafeteria mTaskFragment5;
    private Mapa mTaskFragment6;
    private Directorio mTaskFragment7;
    private Talleres mTaskFragment8;
    private Fragment mTaskFragment9;
    private Impresiones mTaskFragment10;
    private CursosMooc mTaskFragment11;
    private ConexionEsan mTaskFragment12;
    private EducacionEjecutiva mTaskFragment13;
    private Fablab mTaskFragment14;
    private Estacionamiento mTaskFragment15;

    FragmentManager fragmentManager = getSupportFragmentManager();




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public int tipo=1;
    public int menu=0;

    public void comenzo(){
        this.prueba=3;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {





        //FragmentManager fragmentManager = getSupportFragmentManager();

        //fragmentManager.putFragment(savedInstanceState, "Calendaro",mTaskFragment4);

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        notify("Recordatorio","No se olvide de rellenar la encuenta de cada curso.Mas informacion en su correo");






        if (savedInstanceState != null) {
            // Restore value of members from saved state


            mTaskFragment4 = fragmentManager.getFragment(savedInstanceState, "Calendario");
        }

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


        Log.i("iniciarf", String.valueOf(Datah.getInstance().getData()));




    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Fragment fragment=null;
        menu=position;

        Log.i("iniciart1", String.valueOf(menu));


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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    break;


                case 3:

                    mTaskFragment4 = fragmentManager.findFragmentByTag("Calendario");

                    //fragment= new Calendario();
                    if(mTaskFragment4 != null){
                        Log.v("no nulo","no nulo");
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Calendario")).commit();
                    }else{
                        Log.v("nulo","nulo");
                        mTaskFragment4= new Calendario();
                        fragmentManager.beginTransaction().add(R.id.container, mTaskFragment4, "Calendario").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    break;
                case 6:
                    //fragment= new Directorio();
                    mTaskFragment7 = (Directorio) fragmentManager.findFragmentByTag("Directorio");


                    if(mTaskFragment7 != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Directorio")).commit();
                    }else{
                        mTaskFragment7 = new Directorio();
                        fragmentManager.beginTransaction().add(R.id.container, mTaskFragment7, "Directorio").commit();
                    }

                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    break;


                case 8:
                    //fragment = new Biblioteca();
                    mTaskFragment9 = fragmentManager.findFragmentByTag("Biblioteca");


                    if(mTaskFragment9 != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                    }else{
                        mTaskFragment9 = new Biblioteca();
                        fragmentManager.beginTransaction().add(R.id.container, mTaskFragment9, "Biblioteca").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    break;


                case 13:
                    //fragment = new Educacion Ejecutiva();
                    if(fragmentManager.findFragmentByTag("EducacionEjecutiva") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new EducacionEjecutiva(), "EducacionEjecutiva").commit();
                    }
                    //Los otros se esconden
                    if(fragmentManager.findFragmentByTag("Noticia") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
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
                    if(fragmentManager.findFragmentByTag("ActArt") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    break;

                case 15:
                    //fragment = new estacionamiento
                    fragment= new Estacionamiento();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Estacionamiento").commit();
                    break;
                //case 14:
               //    fragment = new Gamificacion();
                 //   break;
                case 16:
                    fragment= new Maestrias();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Maestrias").commit();
                    break;

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
                    if(fragmentManager.findFragmentByTag("Registroesta") != null) {
                        //if the other fragment is visible, hide it.
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Registroesta")).commit();
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
                    if(fragmentManager.findFragmentByTag("Registroesta") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Registroesta")).commit();
                    }
                    //fragment= new Calendario();
                    break;
                case 2:
                    fragment= new Cafeteria();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Cafeteria").commit();
                    break;

                case 3:
                    fragment= new Registroesta();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment, "Registroesta").commit();
                    //fragment= new Estacionamiento()
                    break;

            }
        }


        //fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
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
        if (menu==0){
            if(fragmentManager.findFragmentByTag("Noticia") != null){
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Noticia")).commit();
            }else{
                fragmentManager.beginTransaction().add(R.id.container, new Noticia(), "Noticia").commit();
            }

            //Los otros se esconden
            if(fragmentManager.findFragmentByTag("ActArt") != null){
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
            }

        }else{

        }

        if (menu==16){
            //fragmentManager.beginTransaction().replace(R.id.container, new Estacionamiento()).commit();
            //if(fragmentManager.findFragmentByTag("Map1") != null){
             //   fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Map1")).commit();
            //}

           // if(fragmentManager.findFragmentByTag("Map2") != null){
           //     fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Map2")).commit();
           // }

      //  }else{

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

    private void notify(String notificationTitle, String notificationMessage){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Notification notification = new Notification(R.drawable.esan,"New Message", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);

        notification.setLatestEventInfo(MainActivity2Activity.this, notificationTitle,notificationMessage, pendingIntent);
        notificationManager.notify(9999, notification);
    }

}
