package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreenActivity extends AppCompatActivity {
  private Bundle bundle = new Bundle();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_homescreen);
    BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
    bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
          case R.id.nav_home:
            selectedFragment = new HomeFragment();
            break;
          case R.id.nav_caloriecounter:
            selectedFragment = new CalorieCounterFragment();
            break;
          case R.id.nav_runningtracker:
            selectedFragment = new RunningTrackerFragment();
            break;
          case R.id.nav_workoutcompanion:
            selectedFragment = new WorkoutCompanionFragment();
            break;
          case R.id.nav_profiles:
            selectedFragment = new ProfileFragment();
            break;
        }
        selectedFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
      }
    });

    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
  }

}