package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = (EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("JO", ""); //꺼내오는 단계
        et_save.setText(value); //value의 string값을 EditText에다가 넣어줌
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); //activity를 벗어났을 때 실행, 앱을 재실행했을 때 여기서 저장했던 것을 onCreate에서 불러옴

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString(); //EditText에 현재 써져있는 값을 받아온다.
        editor.putString("JO", value);
        editor.commit(); //save를 완료하라는 뜻

    }
}