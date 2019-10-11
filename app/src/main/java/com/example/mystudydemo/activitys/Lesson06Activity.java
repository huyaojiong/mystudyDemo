package com.example.mystudydemo.activitys;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.ToastUtil;
import com.example.mystudydemo.utils.TxtUtil;
import com.example.mystudydemo.views.Lesson06View;

public class Lesson06Activity extends AppCompatActivity implements View.OnTouchListener {


    private static String tag = "Lesson06Activity";
    private TextView tv_lesson06;
    public Lesson06View view;
    public RelativeLayout rl_test6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson06);

        initView();

        initData();

    }

    private void initData() {

        tv_lesson06.setText(TxtUtil.readAssetsTxt(this, "lesson06"));
        view.setOnTouchListener( this);


    }


    private int lastX, lastY;
    private int parentbottom, parentright;

    private void initView() {
        view = findViewById(R.id.iv_test6_touch);
        tv_lesson06 = findViewById(R.id.tv_lesson06);
        rl_test6 = (RelativeLayout) view.getParent();

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.i(tag, "dispatchTouchEvent()" + ev.getAction());
        //logs += "Lesson06Activity" + "  dispatchTouchEvent()  " + ev.getAction() + "\n";

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //logs += "Lesson06Activity" + "  onTouchEvent()  " + event.getAction() + "\n";
        Log.i(tag, "onTouchEvent()" + event.getAction());
        return super.onTouchEvent(event);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(v.getId()==R.id.iv_test6_touch) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();

            switch (event.getAction()) {

                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    //计算事件偏移
                    int dx = eventX - lastX;
                    int dy = eventY - lastY;

                    int left = view.getLeft() + dx;
                    int right = view.getRight() + dx;
                    int top = view.getTop() + dy;
                    int bottom = view.getBottom() + dy;

                    if (left < 0) {
                        right += -left;
                        left = 0;
                    }

                    if (top < 0) {
                        bottom += -top;
                        top = 0;
                    }

                    if (right > parentright) {
                        left -= right - parentright;
                        right = parentright;
                    }

                    if (bottom > parentbottom) {
                        top -= bottom - parentbottom;
                        bottom = parentbottom;
                    }


                    view.layout(left, top, right, bottom);
                    //再次记录eventX/eventY;
                    lastX = eventX;
                    lastY = eventY;
                    break;
                case MotionEvent.ACTION_DOWN:

                    if (parentbottom == 0) {
                        parentbottom = rl_test6.getBottom();
                    }
                    if (parentright == 0) {
                        parentright = rl_test6.getRight();
                    }
                    lastX = eventX;
                    lastY = eventY;
                    break;
                default:
                    break;
            }
        }
        //logs += "Lesson06View" + "  Listener onTouch()  " + event.getAction() + "\n";
        return true;

    }

    private  boolean exit=false;  //标识是否可以退出
    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
        {
            if(!exit)
            {
                exit=true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit=false;
                    }
                },2000);
                ToastUtil.showShortToast("再按一次确定退出！");
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);//退出时清空所有的
    }
}
