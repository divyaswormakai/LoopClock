package com.example.loopclock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class clock extends Fragment {
    TextView timetxt,title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        View clc =  inflater.inflate(R.layout.fragment_clock, container, false);
        timetxt = (TextView) clc.findViewById(R.id.time);
        title = (TextView) clc.findViewById(R.id.clocktext);
        title.setText("Your current time is");
        timetxt.setText(currentTime);
        //to update the time every second
        Timer myTime=new Timer();
        myTime.scheduleAtFixedRate(new TimerTask() {
            int count =0;
            @Override
            public void run() {
                UpdateTime();
            }
        }, 0, 1000);

        return clc;
    }

    public void UpdateTime(){
        String currentTime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());
        timetxt.setText(currentTime);
    }
}
