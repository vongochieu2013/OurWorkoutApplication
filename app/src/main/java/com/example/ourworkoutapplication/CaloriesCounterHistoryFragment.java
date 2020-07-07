package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class CaloriesCounterHistoryFragment extends Fragment {
  private FirebaseFirestore db;
  private Button goBackButton;
  private Button nextButton;
  private User currentUser;
  private CollectionReference userCC;
  private ArrayList<UserCC> ccList;
  private static String DATE = "date";
  private ListView mListView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_caloriescounter_history, container, false);
    goBackButton = root.findViewById(R.id.CCGoBackButton);
    nextButton = root.findViewById(R.id.CCNextButton);
    setData(root);
    userCC
            .orderBy(DATE, Query.Direction.DESCENDING)
            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
      @Override
      public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          UserCC usercc = documentSnapshot.toObject(UserCC.class);
          ccList.add(usercc);
        }
        CalorieCounterListAdapter ccadapter = new CalorieCounterListAdapter(getContext(), R.layout.adapter_calcounter_view_layout, ccList);
        mListView.setAdapter(ccadapter);
      }
    });
    Log.w("chris", "working so far");

    goBackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new RunningTrackerHistoryFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
      }
    });

    nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new WorkoutCompanionHistoryFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
      }
    });

    return root;
  }

  public void setData(View root) {
    db = FirebaseFirestore.getInstance();
    ccList = new ArrayList<>();
    mListView = root.findViewById(R.id.cclistView);
    currentUser = MainActivity.getCurrentUser();
    db = FirebaseFirestore.getInstance();
    currentUser = MainActivity.getCurrentUser();
    String ccName = "CC" + "-" + currentUser.getEmail();
    userCC = db.collection(ccName);
  }
}