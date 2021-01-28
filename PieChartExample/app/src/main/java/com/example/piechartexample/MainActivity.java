package com.example.piechartexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.dinuscxj.progressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";

    CircleProgressBar circleProgressBar1, circleProgressBar2, circleProgressBar3;
    int i, j, k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressBar1 = findViewById(R.id.cpb_circlebar1);
        circleProgressBar2 = findViewById(R.id.cpb_circlebar2);
        circleProgressBar3 = findViewById(R.id.cpb_circlebar3);

        final Handler handler1 = new Handler();
        final Handler handler2 = new Handler();
        final Handler handler3 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 70) {
                    circleProgressBar1.setProgress(i);
                    i++;
                    handler1.postDelayed(this, 10);
                } else {
                    handler1.removeCallbacks(this);
                }
            }
        }, 10);
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (j <= 50) {
                    circleProgressBar2.setProgress(j);
                    j++;
                    handler2.postDelayed(this, 10);
                } else {
                    handler2.removeCallbacks(this);
                }
            }
        }, 10);

        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (k <= 90) {
                    circleProgressBar3.setProgress(k);
                    k++;
                    handler3.postDelayed(this, 10);
                } else {
                    handler3.removeCallbacks(this);
                }
            }
        }, 10);
    }// onCreate()..

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }
}