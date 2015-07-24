package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Diegoflg on 7/24/2015.
 */
public class EstadoDePagos extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_estado, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        return rootView;


    }
}
