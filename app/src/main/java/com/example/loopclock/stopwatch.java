package com.example.loopclock;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.loopclock.R;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class stopwatch extends Fragment {

    TextView timer;
    ScrollView scroller;
    LinearLayout ll;
    Button start,lap;
    int sec=00,min=00,hr=00;
    String pattern;
    Boolean startBool = true;
    Timer myTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View stopWatch = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        timer = stopWatch.findViewById(R.id.timer);
        start = stopWatch.findViewById(R.id.start);
        lap = stopWatch.findViewById(R.id.lap);
        scroller = stopWatch.findViewById(R.id.scroll);
        ll = stopWatch.findViewById(R.id.ll);

        pattern = hr+"0 : "+min+"0 : 0"+sec;
        timer.setText(pattern);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClick();
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lapClick();
            }
        });

        return stopWatch;
    }

    void startClick(){
        if(startBool){
            start.setText("Pause");
            lap.setText("Lap");
            startBool = false;
            StartTimer();
        }
        else{
            start.setText("Start");
            lap.setText("Stop");
            startBool = true;
            PauseTimer();
        }
    }

    void lapClick(){
        String temp = (String) lap.getText();
        if(temp == "Lap"){
            //do something to store data in scroller
            TextView tempt = new TextView(getContext());
            tempt.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tempt.setGravity(Gravity.CENTER_HORIZONTAL);
            tempt.setText(timer.getText().toString());

            ll.addView(tempt);
        }
        else{   //stop
            sec=0;
            min=0;
            hr=0;

            pattern = hr+"0 : "+min+"0 : 0"+sec;
            timer.setText(pattern);

            ll.removeAllViews();
        }
    }

    void StartTimer(){
        myTime=new Timer();
        myTime.scheduleAtFixedRate(new TimerTask() {
            int count =0;
            @Override
            public void run() {
                UpdateTime();
            }
        }, 0, 1000);
    }

    void UpdateTime(){
        sec++;
        if(sec>=60){
            min++;
            sec=0;
        }
        if(min>=60){
            hr++;
            min=0;
        }
        ArrangeText();
    }

    void PauseTimer(){
        myTime.cancel();
    }

    void ArrangeText(){
        if(hr<=9){
            if(min<=9){
                if(sec<=9){
                    pattern = hr+"0 : 0"+min+" : 0"+sec;
                }
                else{
                    pattern = hr+"0 : 0"+min+" : "+sec;
                }
            }
            else{
                if(sec<=9){
                    pattern = hr+"0 : "+min+" : 0"+sec;
                }
                else{
                    pattern = hr+"0 : "+min+" : "+sec;
                }
            }
        }
        else{
            if(min<=9){
                if(sec<=9){
                    pattern = hr+" : 0"+min+" : 0"+sec;
                }
                else{
                    pattern = hr+" : 0"+min+" : "+sec;
                }
            }
            else{
                if(sec<=9){
                    pattern = hr+" : "+min+" : 0"+sec;
                }
                else{
                    pattern = hr+" : "+min+" : "+sec;
                }
            }
        }

        timer.setText(pattern);
    }
}
