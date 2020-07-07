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

public class CalorieCounterListAdapter extends ArrayAdapter<UserCC> {

    private Context mContext;
    private int mResource;

    public CalorieCounterListAdapter(Context context, int resource, ArrayList<UserCC> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        String mealdesc = getItem(position).getMealDescriptions();
        int cals = getItem(position).getCalories();
        String stringcals = Integer.toString(cals);

        Date date = getItem(position).getDate();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = DateFor.format(date);

        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        displayDataToScreen(convertView, formattedDate, stringDate, mealdesc, stringcals);

        return convertView;
    }

    public void displayDataToScreen(View convertView, String formattedDate, String stringDate,  String mealdesc, String cals){
        TextView tvdate = convertView.findViewById(R.id.CCtextView1);
        TextView tvmealdesc = convertView.findViewById(R.id.CCtextView2);
        TextView tvcals = convertView.findViewById(R.id.CCtextView3);
        TextView tvcurrtime = convertView.findViewById(R.id.CCtextView4);
        tvdate.setText(stringDate);
        tvmealdesc.setText(mealdesc);
        tvcals.setText(cals);
        tvcurrtime.setText(formattedDate);
    }

}
