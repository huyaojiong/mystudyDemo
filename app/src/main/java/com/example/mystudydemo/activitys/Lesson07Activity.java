package com.example.mystudydemo.activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.internal.telephony.ITelephony;
import com.example.mystudydemo.R;
import com.example.mystudydemo.services.Lesson07Service;
import com.example.mystudydemo.utils.ToastUtil;
import com.example.mystudydemo.utils.TxtUtil;

import java.lang.reflect.Method;

public class Lesson07Activity extends AppCompatActivity {

    private Intent intent;
    private ServiceConnection conn;
    public TextView tv_lesson07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson07);


        initView();
        initData();
    }


    private void initView() {
        tv_lesson07 = findViewById(R.id.tv_lesson07);

    }

    private void initData() {
        tv_lesson07.setText(TxtUtil.readAssetsTxt(this, "lesson07"));
    }


    public void startMyService(View view) {
        intent = new Intent(this, Lesson07Service.class);
        startService(intent);
        ToastUtil.showShortToast("开启服务");

    }

    public void stopMyService(View view) {
        intent = new Intent(this, Lesson07Service.class);
        stopService(intent);
        ToastUtil.showShortToast("关闭服务");
    }

    public void bindMyService(View view) {

        intent = new Intent(this, Lesson07Service.class);
        if (conn == null) {
            conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };

            bindService(intent, conn, Context.BIND_AUTO_CREATE);
            ToastUtil.showShortToast("绑定服务");

        } else {
            ToastUtil.showShortToast("已绑定服务");
        }

    }

    public void unbindMyService(View view) {

        if (conn != null) {
            unbindService(conn);
            conn = null;
            ToastUtil.showShortToast("解绑服务");
        } else {
            ToastUtil.showShortToast("未绑定服务");
        }
    }

    public void startMusic(View view) {
        intent = new Intent(this, Lesson07Service.class);
        intent.putExtra("action", "start");
        startService(intent);

    }

    public void stopMusic(View view) {
        intent = new Intent(this, Lesson07Service.class);
        intent.putExtra("action", "stop");
        startService(intent);
    }

    public void pauseMusic(View view) {
        intent = new Intent(this, Lesson07Service.class);
        intent.putExtra("action", "pause");
        startService(intent);
    }

    public void exitMusic(View view) {
        intent = new Intent(this, Lesson07Service.class);
        //intent.putExtra("action","start");
        stopService(intent);
        finish();
    }

    public void endCall(View view) throws Exception {

        Class c = Class.forName("android.os.ServiceManager");

        Method method = c.getMethod("getService", String.class);

        IBinder iBinder=(IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);


        ITelephony telephony = ITelephony.Stub.asInterface(iBinder);

        telephony.endCall();

    }
}
