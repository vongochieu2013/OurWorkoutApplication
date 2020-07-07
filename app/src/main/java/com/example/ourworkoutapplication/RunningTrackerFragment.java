package com.example.ourworkoutapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class RunningTrackerFragment extends Fragment implements AdapterView.OnItemSelectedListener {
  private Chronometer chronometer;
  private Button startButton;
  private Button pauseButton;
  private Button resetButton;
  private Button confirmDistanceButton;
  private TextView distanceText;
  private EditText distanceWorkout;
  private Spinner spinner;
  private long pauseOffSet;
  private boolean running;
  private MediaPlayer player;
  private String workoutType;
  private String chronometerText;
  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private User currentUser = MainActivity.getCurrentUser();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_startworkout, container, false);
    setData(root);
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!running) {
          chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
          chronometer.start();
          pauseButton.setVisibility(View.VISIBLE);
          resetButton.setVisibility(View.INVISIBLE);
          playMusic();
          running = true;
        }
      }
    });

    pauseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (running) {
          chronometer.stop();
          pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
          pauseButton.setVisibility(View.INVISIBLE);
          resetButton.setVisibility(View.VISIBLE);
          pauseMusic();
          running = false;
        }
      }
    });
    resetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!running) {
          setDistanceVisible();
          chronometerText = chronometer.getText().toString().substring(6);
          stopMusic();
          pauseOffSet = 0;
        }
      }
    });

    confirmDistanceButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        double distance ;
        if( distanceWorkout.getText().toString().length() <= 0){
          distance = 0;
        } else {
          distance = Double.parseDouble(distanceWorkout.getText().toString().trim());
        }

        Date date = new Date();
        String resultText = "Running Summary:\n\tDate: " + date + "\n\tTime: " + chronometerText +
                            "\n\tWorkout Type: " + workoutType + "\n\tDistance: " + distance + " miles";
        Toast.makeText(getContext(), resultText, Toast.LENGTH_LONG).show();
        chronometer.setBase(SystemClock.elapsedRealtime());
        setUserHistory(date, chronometerText, workoutType, distance);
      }
    });


    return root;
  }


  public void setData(View root) {
    chronometer = root.findViewById(R.id.chronometer);
    startButton = root.findViewById(R.id.startWorkoutButton);
    pauseButton = root.findViewById(R.id.pauseWorkoutButton);
    resetButton = root.findViewById(R.id.resetWorkoutButton);
    spinner = root.findViewById(R.id.workOutType);
    distanceText = root.findViewById(R.id.distanceText);
    distanceWorkout = root.findViewById(R.id.distanceWorkout);
    confirmDistanceButton = root.findViewById(R.id.confirmDistanceButton);
    setDistanceInvisible();
    resetButton.setVisibility(View.INVISIBLE);
    setUpSpinner(root);
    chronometer.setFormat("Time: %s");
    chronometer.setBase(SystemClock.elapsedRealtime());
  }

  public void setUserHistory(Date date, String time, String workoutType, double distance) {
    UserHistory currentUserHistory = new UserHistory(date, time, workoutType, distance);
    currentUser = MainActivity.getCurrentUser();
    String historyName = "history" + "-" + currentUser.getEmail();
    db.collection(historyName).add(currentUserHistory);
    setDistanceInvisible();
    pauseButton.setVisibility(View.VISIBLE);
    resetButton.setVisibility(View.INVISIBLE);
  }

  public void setUpSpinner(View root) {
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(), R.array.workoutType, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);
  }

  public void playMusic() {
    if (player == null) {
      player = MediaPlayer.create(getContext(), R.raw.pop_workoutsong);
      player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
          stopPlayer();
        }
      });
    }
    player.start();
  }

  public void pauseMusic() {
    if (player != null) {
      player.pause();
    }
  }

  public void stopMusic() {
    stopPlayer();
  }

  public void stopPlayer() {
    if (player != null) {
      player.release();
      player = null;
    }
  }

  public void setDistanceVisible() {
    distanceText.setVisibility(View.VISIBLE);
    distanceWorkout.setVisibility(View.VISIBLE);
    confirmDistanceButton.setVisibility(View.VISIBLE);
  }

  public void setDistanceInvisible() {
    distanceText.setVisibility(View.INVISIBLE);
    distanceWorkout.setVisibility(View.INVISIBLE);
    confirmDistanceButton.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    workoutType = parent.getItemAtPosition(position).toString();
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
  }

}

