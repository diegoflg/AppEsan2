package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
        cv1=(CircleImageView)rootView.findViewById(R.id.cv1);
        cv2=(CircleImageView)rootView.findViewById(R.id.cv2);
        cv3=(CircleImageView)rootView.findViewById(R.id.cv3);
        cv4=(CircleImageView)rootView.findViewById(R.id.cv4);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment=null;
                fragment = new Webfragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();


                Log.d("click", "1");

            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("click", "2");

            }
        });


        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("click", "3");

            }
        });


        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("click", "4");

            }
        });



        return rootView;








    }
}
