package com.example.piechartexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.dinuscxj.progressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";

    CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressBar = findViewById(R.id.cpb_circlebar);

        circleProgressBar.setProgress(70);  // 해당 퍼센트를 적용

    }// onCreate()..

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }
}