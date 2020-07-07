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

public class WorkoutCompanionHistoryFragment extends Fragment {
  private FirebaseFirestore db;
  private Button goBackButton;
  private User currentUser;
  private CollectionReference userWC;
  private ArrayList<UserWC> wcList;
  private static String DATE = "date";
  private ListView mListView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_wc_history, container, false);
    goBackButton = root.findViewById(R.id.WCGoBackButton);
    setData(root);
    userWC
      .orderBy(DATE, Query.Direction.DESCENDING)
      .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
      @Override
      public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          UserWC userwc = documentSnapshot.toObject(UserWC.class);
          wcList.add(userwc);
        }
        WorkoutCompanionListAdapter ccadapter = new WorkoutCompanionListAdapter(getContext(), R.layout.adapter_wc_view_layout, wcList);
        mListView.setAdapter(ccadapter);
      }
    });
    goBackButton.setOnClickListener(new View.OnClickListener() {
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

  public void setData(View root) {
    db = FirebaseFirestore.getInstance();
    wcList = new ArrayList<>();
    mListView = root.findViewById(R.id.wclistView);
    currentUser = MainActivity.getCurrentUser();
    db = FirebaseFirestore.getInstance();
    String wcName = "WClog" + "-" + currentUser.getEmail();
    userWC = db.collection(wcName);
  }
}