package com.example.ourworkoutapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RunningTrackerListAdapter extends ArrayAdapter<UserHistory> {

  private Context mContext;
  private int mResource;

  public RunningTrackerListAdapter(Context context, int resource, ArrayList<UserHistory> objects) {
    super(context, resource, objects);
    mContext = context;
    mResource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    //get the persons information
    Date date = getItem(position).getDate();
    String time = getItem(position).getTime();
    String workoutType = getItem(position).getWorkoutType();
    double distance = getItem(position).getDistance();

    SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
    String stringDate = DateFor.format(date);

    String strDateFormat = "hh:mm:ss a";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate = dateFormat.format(date);

    LayoutInflater inflater = LayoutInflater.from(mContext);
    convertView = inflater.inflate(mResource, parent, false);

    displayDataToScreen(convertView, stringDate, time, workoutType, distance, formattedDate);

    return convertView;
  }

  public void displayDataToScreen(View convertView, String stringDate, String time, String workoutType, double distance, String formattedDate) {
    TextView tvDate = convertView.findViewById(R.id.RTTextView1);
    TextView tvTime = convertView.findViewById(R.id.RTTextView2);
    TextView tvWorkoutType = convertView.findViewById(R.id.RTTextView3);
    TextView tvDistance = convertView.findViewById(R.id.RTTextView4);
    TextView tvCurrentTime = convertView.findViewById(R.id.RTTextView5);
    tvDate.setText(stringDate);
    tvTime.setText(time);
    tvWorkoutType.setText(workoutType);
    tvDistance.setText(Double.toString(distance));
    tvCurrentTime.setText(formattedDate);
  }
}