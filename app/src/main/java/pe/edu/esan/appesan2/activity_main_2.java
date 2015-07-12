package pe.edu.esan.appesan2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;


public class activity_main_2 extends ActionBarActivity {
    private TabHost tabHost;
    private String[] tipoAvisos= {"Conferencias", "Talleres", "Otras actividades", "Todo"};
    private ListView avisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.Cursos);
        spec.setIndicator("Cursos");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.Horario);
        spec.setIndicator("Horario");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab3");
        spec.setContent(R.id.Examenes);
        spec.setIndicator("Examenes");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab4");
        spec.setContent(R.id.Avisos);
        spec.setIndicator("Avisos");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab5");
        spec.setContent(R.id.Menu);
        spec.setIndicator("Menu");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        avisos = (ListView) findViewById(R.id.listaAvisos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tipoAvisos);
        avisos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
