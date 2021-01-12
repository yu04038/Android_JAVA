package com.example.threadexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_stop;
    Thread thread;
    boolean isThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //스레드 시작
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = true;
                thread = new Thread() {
                    public void run() {
                        while(isThread) { //isThread가 true일 때
                            try {
                                sleep(5000); //특정 초만큼 쉬게 한다.
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }
                };
                thread.start();
            }
        });

        //스레드 종료
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = false;
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
        }
    };
}