package com.example.ourworkoutapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;


public class WCSetsRepsFragment extends Fragment {
  private EditText etDescription;
  private EditText etS1Reps;
  private EditText etS2Reps;
  private EditText etS3Reps;
  private String wcDesc;
  private String wcType;
  private int s1Reps;
  private int s2Reps;
  private int s3Reps;
  private Button wcsrSubmitButton;
  private Button wcsrBackButton;
  private User currUser = MainActivity.getCurrentUser();

  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private Date currentTime = Calendar.getInstance().getTime();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_wc_sets_reps, container, false);
    setData(root);
    Bundle bundle = getArguments();
    if (bundle != null) {
      wcType = bundle.getString("woType");
    }
    wcsrSubmitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (etDescription.getText().toString().length() <= 0) {
          wcDesc = "";
        } else {
          wcDesc = etDescription.getText().toString().trim();
        }

        if (etS1Reps.getText().toString().length() <= 0) {
          s1Reps = 0;
        } else {
          s1Reps = Integer.parseInt(etS1Reps.getText().toString().trim());
        }
        if (etS2Reps.getText().toString().length() <= 0) {
          s2Reps = 0;
        } else {
          s2Reps = Integer.parseInt(etS2Reps.getText().toString().trim());
        }
        if (etS3Reps.getText().toString().length() <= 0) {
          s3Reps = 0;
        } else {
          s3Reps = Integer.parseInt(etS3Reps.getText().toString().trim());
        }

        setWClog(wcType, wcDesc, currentTime, s1Reps, s2Reps, s3Reps);
        Toast.makeText(getContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
      }
    });

    wcsrBackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new WorkoutCompanionFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
      }
    });

    return root;
  }

  public void setData(View root) {
    etDescription = root.findViewById(R.id.WCSRdesc);
    etS1Reps = root.findViewById(R.id.s1reps);
    etS2Reps = root.findViewById(R.id.s2reps);
    etS3Reps = root.findViewById(R.id.s3reps);
    wcsrSubmitButton = root.findViewById(R.id.WCSRsubmitButton);
    wcsrBackButton = root.findViewById(R.id.WCSRGoBackButton);
  }

  public void setWClog(String workoutType, String workoutDesc, Date date, int rep1, int rep2, int rep3) {
    UserWC currWCUser = new UserWC();
    currWCUser.setWorkoutType(workoutType);
    currWCUser.setWorkoutDesc(workoutDesc);
    currWCUser.setDate(date);
    currWCUser.setRep1(rep1);
    currWCUser.setRep2(rep2);
    currWCUser.setRep3(rep3);
    String WCName = "WClog" + "-" + currUser.getEmail();
    db.collection(WCName).add(currWCUser);
  }
}