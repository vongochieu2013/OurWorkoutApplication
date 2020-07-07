package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {
  private CardView RTHistoryButton;
  private CardView CCHistoryButton;
  private CardView WCHistoryButton;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_history, container, false);
    setData(root);
    RTHistoryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new RunningTrackerHistoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragment_container, fragment).commit();
      }
    });

    CCHistoryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new CaloriesCounterHistoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragment_container, fragment).commit();
      }
    });

    WCHistoryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new WorkoutCompanionHistoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragment_container, fragment).commit();
      }
    });

    return root;
  }

  public void setData(View root) {
    RTHistoryButton = root.findViewById(R.id.RTHistoryButton);
    CCHistoryButton = root.findViewById(R.id.CCHistoryButton);
    WCHistoryButton = root.findViewById(R.id.WCHistoryButton);
  }
}