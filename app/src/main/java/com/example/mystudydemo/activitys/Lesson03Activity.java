package com.example.mystudydemo.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mystudydemo.R;
import com.example.mystudydemo.adapters.Lesson03Adapter;
import com.example.mystudydemo.entity.ShopInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lesson03Activity extends AppCompatActivity {

    private ListView lv_lesson03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson03);
        initView();
    }

    private void initView() {
        lv_lesson03 = findViewById(R.id.lv_lesson03);
    }

    public void arrayAdapterTest(View view) {
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Lesson03Activity.this, R.layout.item_array_adapter, data);
        lv_lesson03.setAdapter(adapter);

    }

    public void simpleAdapterTest(View view) {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = null;

        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, Object>();
            map.put("icon", R.drawable.activity);
            map.put("name", "activity" + i);
            data.add(map);
        }

        String[] from = {"icon", "name"};
        int[] to = {R.id.iv_item_content, R.id.tv_item_name};

        SimpleAdapter adapter = new SimpleAdapter(Lesson03Activity.this, data, R.layout.item_simple_adapter, from, to);
        lv_lesson03.setAdapter(adapter);
    }

    public void baseAdapterTest(View view) {

        List<ShopInfo> lists = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            lists.add(new ShopInfo(R.drawable.activity, "Activity" + i));
        }

        Lesson03Adapter adapter = new Lesson03Adapter(Lesson03Activity.this, lists);
        lv_lesson03.setAdapter(adapter);

    }
}
