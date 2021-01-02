package com.example.listexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>(); //리스트를 String의 형태로 넣겠다는 뜻

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, data); //listview와 list를 연결하기 위한 adapter 생성
        list.setAdapter(adapter);

        data.add("add 1");
        data.add("add 2");
        data.add("add 3");
        adapter.notifyDataSetChanged(); //저장을 완료하기 위함.
    }
}