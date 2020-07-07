package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class WorkoutCompanionFragment extends Fragment {
    //variables

    private CardView pullButton;
    private CardView pushButton;
    private CardView legsButton;
    private String woType;

   

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_companion, container, false);
        setData(root);

        //buttons
        pullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woType = "Pull";
                replaceFragment();
            }
        });

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woType = "Push";
                replaceFragment();
            }
        });

        legsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woType = "Legs";
                replaceFragment();
            }
        });

        return root;
    }

    public void setData(View root){
        pullButton = root.findViewById(R.id.PullButton);
        pushButton = root.findViewById(R.id.PushButton);
        legsButton = root.findViewById(R.id.LegsButton);
    }

    public void replaceFragment() { // replaces fragment and sends workout type to sets and reps frag
        Fragment wcsrfrag = new WCSetsRepsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString("woType", woType);
        wcsrfrag.setArguments(bundle);
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, wcsrfrag);
        fr.commit();


    }


}