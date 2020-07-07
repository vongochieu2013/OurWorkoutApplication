package com.example.ourworkoutapplication;

import android.annotation.SuppressLint;
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

public class WorkoutCompanionListAdapter extends ArrayAdapter<UserWC> {

    private Context mContext;
    private int mResource;

    public WorkoutCompanionListAdapter(Context context, int resource, ArrayList<UserWC> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information

        String workoutType = getItem(position).getWorkoutType();
        String workoutDesc = getItem(position).getWorkoutDesc();
        int reps1 = getItem(position).getRep1();
        int reps2 = getItem(position).getRep2();
        int reps3 = getItem(position).getRep3();

        Date date = getItem(position).getDate();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = DateFor.format(date);

        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        displayDataToScreen(convertView, stringDate, formattedDate, workoutType, workoutDesc, reps1, reps2, reps3);

        return convertView;
    }

    @SuppressLint("SetTextI18n")
    public void displayDataToScreen(View convertView, String stringDate, String formattedDate,  String workouttype, String workjoutdesc, int reps1, int reps2, int reps3){
        TextView tvdate = convertView.findViewById(R.id.wctextView1);
        TextView tvcurrenttime = convertView.findViewById(R.id.wctextView2);
        TextView tvworktype = convertView.findViewById(R.id.wctextView3);
        TextView tvworkdesc = convertView.findViewById(R.id.wctextView4);
        TextView tvset1 = convertView.findViewById(R.id.wctextView5);
        TextView tvset2 = convertView.findViewById(R.id.wctextView6);
        TextView tvset3 = convertView.findViewById(R.id.wctextView7);
        TextView tvrep1 = convertView.findViewById(R.id.wctextView8);
        TextView tvrep2 = convertView.findViewById(R.id.wctextView9);
        TextView tvrep3 = convertView.findViewById(R.id.wctextView10);
        tvdate.setText(stringDate);
        tvcurrenttime.setText(formattedDate);
        tvworktype.setText(workouttype);
        tvworkdesc.setText(workjoutdesc);
        tvset1.setText("Set 1:");
        tvset2.setText("Set 2:");
        tvset3.setText("Set 3:");
        tvrep1.setText(Integer.toString(reps1));
        tvrep2.setText(Integer.toString(reps2));
        tvrep3.setText(Integer.toString(reps3));


    }


}
