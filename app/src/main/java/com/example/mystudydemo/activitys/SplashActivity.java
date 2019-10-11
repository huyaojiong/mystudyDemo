package com.example.mystudydemo.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.mystudydemo.R;

public class SplashActivity extends AppCompatActivity {

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
            handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity();
            }
        },2000);
    }

    private  void startActivity()
    {
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity();
        return super.onTouchEvent(event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }


    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}
