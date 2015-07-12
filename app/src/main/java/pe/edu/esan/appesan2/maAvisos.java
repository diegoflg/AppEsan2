package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by educacionadistancia on 09/07/2015.
 */
public class maAvisos extends ActionBarActivity {
    private String[] tipoAvisos= {"Conferencias", "Talleres", "Otras actividades", "Todo"};
    private ListView avisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

    //    avisos = (ListView) findViewById(R.id.listaAvisos);

      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tipoAvisos);
       // avisos.setAdapter(adapter);

    }
}
