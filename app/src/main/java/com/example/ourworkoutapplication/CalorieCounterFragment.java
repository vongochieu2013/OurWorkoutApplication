package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalorieCounterFragment extends Fragment {

  private Date currentTime = Calendar.getInstance().getTime();
  private EditText etMealDescriptionCC;
  private EditText etCaloriesCC;
  private String mealDescription;
  private int userCaloriesGoal;
  private int caloriesDay;
  private Button submitButton;
  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private User currentUser;
  private TextView dailyCaloriesCC;
  private TextView goalCaloriesCC;
  private TextView passGoalText;
  private TextView dailyCaloriesTextView;
  private TextView goalCaloriesTextView;
  private TextView dateTextView;
  private TextView dateCC;
  private CollectionReference userCCHistory;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_caloriecounter, container, false);
    setData(root);
    submitButton = root.findViewById(R.id.submitCCButton);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        
        if(etMealDescriptionCC.getText().toString().length() <= 0){
          mealDescription = "";
        }else { mealDescription = etMealDescriptionCC.getText().toString().trim(); }
        if(etCaloriesCC.getText().toString().length() <= 0){
          caloriesDay = 0;
        }else { caloriesDay = Integer.parseInt(etCaloriesCC.getText().toString().trim()); }

        setCC(mealDescription, caloriesDay, currentTime);
        checkForTotalCalories();
        dailyCaloriesTextView.setVisibility(View.VISIBLE);
        goalCaloriesTextView.setVisibility(View.VISIBLE);
        passGoalText.setVisibility(View.VISIBLE);
        goalCaloriesCC.setVisibility(View.VISIBLE);
        dailyCaloriesCC.setVisibility(View.VISIBLE);
        dateTextView.setVisibility(View.VISIBLE);
        dateCC.setVisibility(View.VISIBLE);
      }
    });
    return root;

  }

  public void setData(View root) {
    etMealDescriptionCC = root.findViewById(R.id.mealDescriptionCCText);
    etCaloriesCC = root.findViewById(R.id.caloriesCCText);
    dailyCaloriesCC = root.findViewById(R.id.dailyCaloriesCC);
    goalCaloriesCC = root.findViewById(R.id.goalCaloriesCC);
    passGoalText = root.findViewById(R.id.passGoalText);
    dailyCaloriesTextView = root.findViewById(R.id.dailyCaloriesTextView);
    goalCaloriesTextView = root.findViewById(R.id.goalCaloriesTextView);
    dateTextView = root.findViewById(R.id.dateTextView);
    dateCC = root.findViewById(R.id.dateCC);
    dailyCaloriesTextView.setVisibility(View.INVISIBLE);
    dateTextView.setVisibility(View.INVISIBLE);
    dateCC.setVisibility(View.INVISIBLE);
    goalCaloriesTextView.setVisibility(View.INVISIBLE);
    passGoalText.setVisibility(View.INVISIBLE);
    goalCaloriesCC.setVisibility(View.INVISIBLE);
    dailyCaloriesCC.setVisibility(View.INVISIBLE);
  }

  public void setCC(String mealDescription, int caloriesPerDay, Date date) {
    UserCC currentUserCC = new UserCC(date, mealDescription, caloriesPerDay);
    currentUser = MainActivity.getCurrentUser();
    userCaloriesGoal = currentUser.getCalories();
    String CCName = "CC" + "-" + currentUser.getEmail();
    userCCHistory = db.collection(CCName);
    userCCHistory.add(currentUserCC);
  }

  public void checkForTotalCalories() {
    final int[] totalCalories = {0};
    userCCHistory
      .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
      @Override
      public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          UserCC userHistory = documentSnapshot.toObject(UserCC.class);
          Date currentDate = userHistory.getDate();
          int currentCalories = userHistory.getCalories();

          Date date = new Date();
          SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
          String stringCurrentDate = DateFor.format(date); // It will be the current date.
          String stringCCDate = DateFor.format(currentDate); // It is the time from the userHistory
          dateCC.setText(stringCurrentDate);
          if (stringCurrentDate.equals(stringCCDate)) {
            totalCalories[0] += currentCalories;
          }
        }
        goalCaloriesCC.setText(Integer.toString(userCaloriesGoal));
        dailyCaloriesCC.setText(Integer.toString(totalCalories[0]));
        if (totalCalories[0] >= userCaloriesGoal) {
          String result = "You passed the calories goal for today!";
          passGoalText.setText(result);
        } else {
          String result = "You didn't reach the calories goal for today!";
          passGoalText.setText(result);
        }
      }
    });
  }

}