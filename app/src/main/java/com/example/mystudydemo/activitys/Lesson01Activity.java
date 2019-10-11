package com.example.mystudydemo.activitys;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.TxtUtil;

public class Lesson01Activity extends AppCompatActivity implements View.OnClickListener {

    public TextView tv_lesson01;
    public Button btn_tel, btn_sms, btn_activity;
    public ImageView iv_activitylife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson01);
        initView();
        initData();
    }


    private void initView() {
        tv_lesson01 = findViewById(R.id.tv_lesson01);
        btn_activity = findViewById(R.id.btn_Activity);
        btn_tel = findViewById(R.id.btn_tel);
        btn_sms = findViewById(R.id.btn_sms);
        iv_activitylife = findViewById(R.id.iv_activitylife);
    }

    private void initData() {
        tv_lesson01.setText(TxtUtil.readAssetsTxt(this, "lesson01"));
        btn_activity.setOnClickListener(this);
        btn_tel.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_Activity: {
                if (iv_activitylife.getVisibility() == View.GONE)
                    iv_activitylife.setVisibility(View.VISIBLE);
                else
                    iv_activitylife.setVisibility(View.GONE);
            }
            break;
            case R.id.btn_sms: {
                intent = new Intent(Intent.ACTION_SENDTO);//android.intent.action.SENDTO
                //2). 携带数据(号码/内容)
                intent.setData(Uri.parse("smsto:1212"));
                //携带额外数据
                intent.putExtra("sms_body", "短信");
                //3). startActivity(intent)
                startActivity(intent);
            }
            break;
            case R.id.btn_tel: {
                //1). 创建一个Intent(隐式)
                //08-28 03:27:09.976: I/ActivityManager(1222): Displayed com.android.dialer/.DialtactsActivity: +535ms
                String action = Intent.ACTION_DIAL;
                intent = new Intent(action);
                //2). 携带数据
                intent.setData(Uri.parse("tel:1212")); //<data android:scheme="tel" />
                //3). startActivity(intent)
                startActivity(intent);
            }
            break;
            default:
                break;
        }

    }
}
