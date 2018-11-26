package com.example.fikridzakwan.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffSet;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.stopWatch);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase() >= 1000000000)) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Bing1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startChronometer(View view){
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
            chronometer.start();
            running = true;
            Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
        }

    }

    public void pauseChronometer(View view){
        if (running) {
            chronometer.stop();
            pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT).show();
        }

    }

    public void resetChronometer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffSet = 0;

    }
}
