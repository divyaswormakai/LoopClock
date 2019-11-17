package com.example.loopclock;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class loop extends Fragment {
    int interval,loopCount,counter;
    Button startBtn;
    EditText intervalTxt, countTxt;
    TextView looprem, timerem;
    Timer myTime;
    MediaPlayer mPlayer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loopView = inflater.inflate(R.layout.fragment_loop, container, false);
        startBtn =  loopView.findViewById(R.id.start);
        intervalTxt =  loopView.findViewById(R.id.interval);
        countTxt =  loopView.findViewById(R.id.loopcount);

        looprem = loopView.findViewById(R.id.looprem);
        timerem = loopView.findViewById(R.id.timerem);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartClick();
            }
        });

        mPlayer = MediaPlayer.create(getContext(),R.raw.sound);
        return loopView;
    }

    void StartClick(){
        String temp = startBtn.getText().toString().trim();
        if(temp =="Stop"){
            startBtn.setText("Start");

            StopLoop();
        }
        else{
            startBtn.setText("Stop");

            interval = Integer.valueOf(intervalTxt.getText().toString());
            loopCount = Integer.valueOf(countTxt.getText().toString())-1;
            counter = interval;

            looprem.setText(String.valueOf(loopCount));
            timerem.setText(String.valueOf(counter));

            StartTimer();
        }
    }

    void StartTimer(){
        myTime=new Timer();
        myTime.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    UpdateTime();
            }
        }, 0, 1000);
    }

    void UpdateTime(){

        counter--;
        if (counter < 0) {
            counter = interval;
            loopCount--;
            PlayTune();
            if(loopCount<0){
                StopLoop();
            }
            else {
                looprem.setText(String.valueOf(loopCount));
            }
        }
        if(loopCount>=0){
            timerem.setText(String.valueOf(counter));
        }
    }

    void StopLoop(){
        myTime.cancel();

        intervalTxt.setText("");
        countTxt.setText("");
        looprem.setText("0");
        timerem.setText("0");
    }

    void PlayTune(){
        mPlayer.start();
    }

}
