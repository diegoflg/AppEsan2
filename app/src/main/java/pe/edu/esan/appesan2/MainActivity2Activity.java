package pe.edu.esan.appesan2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;


/**
 * Actividad principal que se abre despues de hacer login correctamente, aqui se encuentra el navigationDrawer y
 * todos los fragmentos de la aplicacion se abren en esta actividad.
 */


public class MainActivity2Activity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static String TAG = "MUNDO ESAN";//Tag para diferenciar los logs

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;//Se crea el NavigationDrawerFragment llamado mNavigationDrawerFragment
    int prueba=0;// Se crea un entero llamado prueba con un valor inicializado en 0



    /**
     * Fragmentos creados de prueba, algunos no estan en uso, porquee el funcionamiento
     * creando las variables de los fragmentos y llamandolos directamente era el mismo
     */
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
    private Career mTaskFragment14;
    private Estacionamiento mTaskFragment15;

    FragmentManager fragmentManager = getSupportFragmentManager();//Se obtiene la clase que maneja los fragmentos llamandola fragmentManager




    @Override
    public void onConfigurationChanged(Configuration newConfig) {//Called by the system when the device configuration changes while your component is running.
        super.onConfigurationChanged(newConfig);

    }

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;//Se crea el CharSequence llamado mTitle
    public int tipo=1;// Se crea un entero llamado tipo con un valor inicializado en 1
    public int menu=0;// Se crea un entero llamado menu con un valor inicializado en 0

    public void comenzo(){//metodo que cambia el valor de prueba a 3
        this.prueba=3;
    }

    @Override//Override que se ejecuta cuando hay algun saveInstance
    protected void onSaveInstanceState(Bundle savedInstanceState) {





        //FragmentManager fragmentManager = getSupportFragmentManager();

        //fragmentManager.putFragment(savedInstanceState, "Calendaro",mTaskFragment4);

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

       // notify("Recordatorio","No se olvide de rellenar la encuenta de cada curso.Mas informacion en su correo");
       // se uso para mandar notificaciones






        if (savedInstanceState != null) {
            // Restore value of members from saved state


            mTaskFragment4 = fragmentManager.getFragment(savedInstanceState, "Calendario");//se restaura calendario
        }

        Intent i=getIntent();//Se crea el Intent i y se obtienen los valores del Intent recibido al crear la actividad
        Bundle b=i.getExtras();//Se crea el Bundle b y se obtiene el Bundle recibido al crear la actividad

        tipo=b.getInt("tipo");//la variable tipo obtendra el valor del dato "tipo" almacenado en el bundle.
        // tipo es el tipo de usuario que hizo login
        //dependiendo de cada tipo de usuario se mostrara un navigationDrawer diferente

        Log.v("tipo", String.valueOf(tipo));//log para identificar que tipo es el que ingreso

        getSupportActionBar().setDisplayShowHomeEnabled(true);//Set whether to include the application home affordance in the action bar.
        getSupportActionBar().setIcon(R.drawable.esan);//Se pone de icono en el ActionBar el icon esan almaccenado en la carpeta drawable

        final ActionBar ab = getSupportActionBar();//Se crea el ActionBar llamado ab
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);//Set an alternate drawable to display next to the icon/logo/title when DISPLAY_HOME_AS_UP is enabled.
            ab.setDisplayHomeAsUpEnabled(true);//Set whether home should be displayed as an "up" affordance. Set this to true if selecting "home" returns up by a single level in your UI rather than back to the top level or front page.




        mNavigationDrawerFragment = (NavigationDrawerFragment)//Se relaciona el navigation drawer con su respectivo fragment
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();//Se obtiene el titulo de fragmento

        mNavigationDrawerFragment.ini(tipo);//Se ejecuta el metodo ini() de mNavigationDrawerFragment que inicia el mNavigationDrawerFragment con el tipo respectivo

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));







    }

    @Override//override que se ejecuta cuando de hace click a algun item del navigationDrawer
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Fragment fragment=null;//Se crea el Fragment fragment con valor inicial nulo
        menu=position;//el entero menu obtendraa el valor de la posicion clickeada en el navigationDrawer

        Log.i("iniciart1", String.valueOf(menu));//log para verificaciones


        if(tipo==1){//Si el tipo es igual a 1:

            switch (position){//Dependiendo de la posicion mostrara algun fragmento
                case 0://Si das click al item que esta en la posicion 0 parara lo siguiente:
                    //fragment= new Noticia();
                    Datah.getInstance().setMenu(0);//Se guardara en Datah el valor de 0 indicando que este fragmento no es estacionamiento
                    // para evitar que estacionamiento suene si esta abierto otro fragmento

                    if(fragmentManager.findFragmentByTag("Noticia") != null){//Si ya existe el framente noticia
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Noticia")).commit();//lo mostramos
                    }else{//si no existe
                        fragmentManager.beginTransaction().add(R.id.container, new Noticia(), "Noticia").commit();//lo agregamos
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
                    Datah.getInstance().setMenu(0);
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


                case 2:
                    Datah.getInstance().setMenu(0);

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


                case 3:
                    Datah.getInstance().setMenu(0);
                    //fragment= new Cafeteria();
                    if(fragmentManager.findFragmentByTag("Cafeteria") != null){
                        fragmentManager.beginTransaction().replace(R.id.container, new Cafeteria(), "Cafeteria").commit();
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


                case 4:
                    Datah.getInstance().setMenu(0);
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
                case 5:
                    Datah.getInstance().setMenu(0);
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




                case 6:
                    Datah.getInstance().setMenu(0);
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


                case 7:
                    Datah.getInstance().setMenu(0);
                    //fragment = new Impresiones();

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://impresiones.esan.edu.pe:7290/login.cfm"));
                        startActivity(i);
                    }else {
                        if (fragmentManager.findFragmentByTag("Impresiones") != null) {
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Impresiones")).commit();
                        } else {
                            fragmentManager.beginTransaction().add(R.id.container, new Impresiones(), "Impresiones").commit();
                        }
                        //Los otros se esconden
                        if (fragmentManager.findFragmentByTag("Noticia") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Noticia")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("ActArt") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Horario") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Horario")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Notas") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Notas")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Calendario") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Calendario")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Cafeteria") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Cafeteria")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Mapas") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Mapas")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Directorio") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Directorio")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Talleres") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Talleres")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Biblioteca") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Biblioteca")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("CursosMooc") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CursosMooc")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Estadodepagos") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estadodepagos")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("ConexionEsan") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ConexionEsan")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("EducacionEjecutiva") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("EducacionEjecutiva")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("FabLab") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("DPA") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("DPA")).commit();
                        }
                        if (fragmentManager.findFragmentByTag("Estacionamiento") != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                        }
                    }
                    break;


                case 8:
                    Datah.getInstance().setMenu(0);
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





                case 9:
                    Datah.getInstance().setMenu(0);
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


                case 10:
                    Datah.getInstance().setMenu(0);
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


                case 11:
                    Datah.getInstance().setMenu(0);
                    //fragment = new Career();
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Career(), "FabLab").commit();
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

                case 12:
                    Datah.getInstance().setMenu(1);//Identificamos que se ha hecho click en estacionamiento
                    //fragment = new estacionamiento


                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Estacionamiento(), "Estacionamiento").commit();
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
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }

                    break;
                //case 14:
               //    fragment = new Gamificacion();
                 //   break;
                case 13:

                    Datah.getInstance().setMenu(0);
                    //fragment = new Career();
                    if(fragmentManager.findFragmentByTag("DPA") != null){
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("DPA")).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container, new Maestrias(), "DPA").commit();
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
                    if(fragmentManager.findFragmentByTag("Estacionamiento") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Estacionamiento")).commit();
                    }
                    if(fragmentManager.findFragmentByTag("FabLab") != null){
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("FabLab")).commit();
                    }

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


    public void onSectionAttached(int number) {//Se identifica la seccion atachada

        if(tipo==1){//Si es tipo 1

            String[] stringArray = getResources().getStringArray(R.array.section_titles);//Se obtienen los titulos guardados en strings en el array section_titles
            if (number >= 1) {
                mTitle = stringArray[number - 1];
            }
        }

        if(tipo==2){//Si es tipo 2

            String[] stringArray = getResources().getStringArray(R.array.section_titles2);//Se obtienen los titulos guardados en strings en el array section_titles2
            if (number >= 1) {
                mTitle = stringArray[number - 1];
            }
        }
    }

    public void restoreActionBar() {//Metodo que restaura el actionBar
        ActionBar actionBar = getSupportActionBar();//Se crea el ActionBar llamado actionBar que obtiene el actionbar usado
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);//Se pone en modo standard
        actionBar.setDisplayShowTitleEnabled(true);//Se activa el titulo
        actionBar.setTitle(mTitle);//se agrega el titulo
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Initialize the contents of the Activity's standard options menu.
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



    @Override//Override que se ejecuta cuando presionas el boton atras
    public void onBackPressed() {
        if (menu==3){///Si el menu esta en la posicion 3
            //user defined onBackPressed method. Not of Fragment.
            Cafeteria.onBackPressed();//Se ejecutara el metodo onBackPressed() que esta en la clase Cafeteria
    }else{

    }
        if (menu==0){////Si el menu esta en la posicion 0
            if(fragmentManager.findFragmentByTag("Noticia") != null){//y noticias ya ha sido creaado
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Noticia")).commit();//se mostrara noticias
            }else{
                fragmentManager.beginTransaction().add(R.id.container, new Noticia(), "Noticia").commit();//de lo contrario se agregara
            }

            //Los activity articulo se esconde
            if(fragmentManager.findFragmentByTag("ActArt") != null){
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ActArt")).commit();
            }

            //Esto es para que al presionar atras cuando tienes un articulo de noticia aabierto regrese al menu de noticias

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

    private void notify(String notificationTitle, String notificationMessage){//metodo creado para enviar notificaciones fuera de la aplicacion
        //ACTUALMENTE NO EN USO
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")
//Set up notification
        Notification notification = new Notification(R.drawable.esan,"New Message", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);

        notification.setLatestEventInfo(MainActivity2Activity.this, notificationTitle,notificationMessage, pendingIntent);
        notificationManager.notify(9999, notification);//Post a notification to be shown in the status bar.
    }

}
