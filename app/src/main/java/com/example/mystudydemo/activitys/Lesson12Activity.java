package com.example.mystudydemo.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.TxtUtil;

public class Lesson12Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson12);
        initView();
        initData();
    }

    public TextView tv_lesson12;




    private void initView() {
        tv_lesson12 = findViewById(R.id.tv_lesson12);

    }

    private void initData() {
        tv_lesson12.setText(TxtUtil.readAssetsTxt(this, "lesson12"));

    }


}
