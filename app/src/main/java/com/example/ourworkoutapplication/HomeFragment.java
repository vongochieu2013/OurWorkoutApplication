package com.example.ourworkoutapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {
  private Button historyButton;
  private CardView firstNewspapers;
  private CardView secondNewspapers;
  private CardView thirdNewspaper;
  private CardView fourthNewspaper;
  private CardView fifthNewspaper;
  private CardView sixthNewspaper;
  private CardView seventhNewspaper;
  private CardView eighthNewspaper;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    setUpData(root);

    firstNewspapers.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.medicalnewstoday.com/articles/324093#what-is-a-balanced-diet"));
        startActivity(browser);
      }
    });

    secondNewspapers.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://health.usnews.com/wellness/food/articles/what-makes-a-healthy-diet"));
        startActivity(browser);
      }
    });

    thirdNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health-news"));
        startActivity(browser);
      }
    });

    fourthNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cnn.com/health"));
        startActivity(browser);
      }
    });

    fifthNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.myfitnesspal.com/5-best-exercises-for-fat-loss-ranked/"));
        startActivity(browser);
      }
    });

    sixthNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coachmag.co.uk/full-body-workouts/6960/six-week-strength-training-workout-plan"));
        startActivity(browser);
      }
    });

    seventhNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/playlist?list=PLfj4lZyPiNakYgymIbJTGcoNXnrnk8yls"));
        startActivity(browser);
      }
    });

    eighthNewspaper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2bQ0MJL9mZI&list=PLEDBB97DCA7B48E58"));
        startActivity(browser);
      }
    });


    historyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new HistoryFragment());
        fr.commit();
      }
    });


    return root;
  }

  public void setUpData(View root) {
    historyButton = root.findViewById(R.id.historyButton);
    firstNewspapers = root.findViewById(R.id.first_newspaper);
    secondNewspapers = root.findViewById(R.id.second_newspaper);
    thirdNewspaper = root.findViewById(R.id.third_newspaper);
    fourthNewspaper = root.findViewById(R.id.fourth_newspaper);
    fifthNewspaper = root.findViewById(R.id.fifth_newspaper);
    sixthNewspaper = root.findViewById(R.id.sixth_newspaper);
    seventhNewspaper = root.findViewById(R.id.seventh_newspaper);
    eighthNewspaper = root.findViewById(R.id.eighth_newspaper);
  }
}
