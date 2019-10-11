package com.example.mystudydemo.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudydemo.R;

public class Lesson10Activity extends AppCompatActivity {

    private TextView tv_lesson10;
    private ImageView iv_lesson10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson10);
        initView();
    }

    private void initView() {
        tv_lesson10=findViewById(R.id.tv_lesson10);
        iv_lesson10=findViewById(R.id.iv_lesson10);
    }

    public void scale_code(View view) {
        tv_lesson10.setText("Code缩放动画：宽度从0.5到1.5，高度从0.0到1.0，缩放的圆心为顶部中心点，延迟1s开始，持续2s，" +
                "最终还原。");
        ScaleAnimation animation = new ScaleAnimation(0.5f,1.5f,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0);

        animation.setDuration(2000);
        animation.setStartOffset(1000);
        animation.setFillBefore(true);

        iv_lesson10.startAnimation(animation);
    }

    public void scale_xml(View view) {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_test);

        iv_lesson10.startAnimation(animation);

    }


}
