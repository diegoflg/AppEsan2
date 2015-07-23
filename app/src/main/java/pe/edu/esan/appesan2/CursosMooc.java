package pe.edu.esan.appesan2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Diegoflg on 7/22/2015.
 */
public class CursosMooc extends Fragment {

    CircleImageView cv1,cv2,cv3,cv4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lay_mooc, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        cv1=(CircleImageView)rootView.findViewById(R.id.cv1);
        cv2=(CircleImageView)rootView.findViewById(R.id.cv2);
        cv3=(CircleImageView)rootView.findViewById(R.id.cv3);
        cv4=(CircleImageView)rootView.findViewById(R.id.cv4);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Webfragment fragment;
                fragment = new Webfragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", "https://es.coursera.org/");
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });



        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Webfragment fragment;
                fragment = new Webfragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.edx.org/");
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

            }
        });


        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Webfragment fragment;
                fragment = new Webfragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.miriadax.net/");
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

            }
        });


        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Webfragment fragment;
                fragment = new Webfragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", "http://harvardx.harvard.edu/");
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

            }
        });



        return rootView;








    }
}
