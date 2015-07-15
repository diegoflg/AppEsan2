package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class IngresoCafeteria extends Fragment {
    TabHost mTabHost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lay_ingresocafeteria, container, false);

        mTabHost = (TabHost) v.findViewById(R.id.tabHost2);
        mTabHost.setup();

        TabHost.TabSpec spec = mTabHost.newTabSpec("Tab 1");
        spec.setContent(R.id.LaRuta);
        spec.setIndicator("La Ruta");
        mTabHost.addTab(spec);

        spec=mTabHost.newTabSpec("Tab 2");
        spec.setContent(R.id.DeliSabores);
        spec.setIndicator("Deli Sabores");
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);
        return v;
    }
}
