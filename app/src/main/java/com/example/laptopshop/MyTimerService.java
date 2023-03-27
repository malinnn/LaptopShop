package com.example.laptopshop;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyTimerService extends Service
{
    private final static String TAG = "StopwatchService";
    public static final String STOPWATCH = "com.example.stopwatch";

    Intent i = new Intent(STOPWATCH);

    private Handler StopwatchHandler = new Handler();

    private boolean stopped = false;
    private long startTime, elapsedTime, sec, min, hr;
    private String hours, minutes, seconds;
    private final int refreshRate = 100;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

        if (stopped)
        {
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else
        {
            startTime = System.currentTimeMillis();
        }
        StopwatchHandler.removeCallbacks(startTimer);
        StopwatchHandler.postDelayed(startTimer, 0);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateTimer (float time)
    {
        sec = (long)(time/1000);
        min = (long)((time/1000)/60);
        hr = (long)(((time/1000)/60)/60);

        sec = sec % 60;
        seconds=String.valueOf(sec);

        if(sec == 0) { seconds = "00"; }
        if(sec <10 && sec > 0) { seconds = "0"+seconds; }

        min = min % 60;
        minutes=String.valueOf(min);

        if(min == 0) { minutes = "00"; }
        if(min < 10 && min > 0) { minutes = "0"+minutes; }

        hours = String.valueOf(hr);

        if(hr == 0) { hours = "00"; }
        if(hr < 10 && hr > 0) { hours = "0" + hours; }

        i.putExtra("hours", hours);
        i.putExtra("minutes", minutes);
        i.putExtra("seconds", seconds);

        sendBroadcast(i);
    }

    private Runnable startTimer = new Runnable() {
        @Override
        public void run()
        {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            StopwatchHandler.postDelayed(this, refreshRate);
        }
    };
}