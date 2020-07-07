package com.example.ourworkoutapplication;

import android.os.Bundle;
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

public class RunningTrackerHistoryFragment extends Fragment {
  private FirebaseFirestore db;
  private CollectionReference userHistory;
  private Button goBackButton;
  private Button nextButton;
  private User currentUser;
  private ArrayList<UserHistory> peopleList;
  private ListView mListView;
  private static String DATE = "date";

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_runtracker_history, container, false);
    setUpData(root);
    userHistory
      .orderBy(DATE, Query.Direction.DESCENDING)
      .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
      @Override
      public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        String data = "";
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          UserHistory userHistory = documentSnapshot.toObject(UserHistory.class);
          peopleList.add(userHistory);
        }
        RunningTrackerListAdapter adapter = new RunningTrackerListAdapter(getContext(), R.layout.adapter_runhistory_view_layout, peopleList);
        mListView.setAdapter(adapter);
      }
    });

    goBackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new HistoryFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
      }
    });

    nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new CaloriesCounterHistoryFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
      }
    });

    return root;
  }

  private void setUpData(View root) {
    goBackButton = root.findViewById(R.id.RTGoBackButton);
    nextButton = root.findViewById(R.id.RTNextButton);
    mListView = root.findViewById(R.id.listView);
    peopleList = new ArrayList<>();
    db = FirebaseFirestore.getInstance();
    currentUser = MainActivity.getCurrentUser();
    String historyName = "history" + "-" + currentUser.getEmail();
    userHistory = db.collection(historyName);
  }
}

