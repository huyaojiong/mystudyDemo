package com.example.mystudydemo.activitys;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudydemo.R;

public class Lesson10Activity extends AppCompatActivity {

    private TextView tv_lesson10;
    private ImageView iv_lesson10,iv_drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson10);
        initView();
    }

    private void initView() {
        tv_lesson10=findViewById(R.id.tv_lesson10);
        iv_lesson10=findViewById(R.id.iv_lesson10);
        iv_drawable=findViewById(R.id.iv_drawable);
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
        tv_lesson10.setText("Xml缩放动画: Xml缩放动画: 宽度从0.0到1.5, 高度从0.0到1.0, 延迟1s开始,持续3s,圆心为右下角, 最终固定");
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_test);
        iv_lesson10.startAnimation(animation);

    }


    public void alpha_code(View view) {

        tv_lesson10.setText("Code透明度动画: 从完全透明到完全不透明, 持续2s");
        //1. 创建动画对象
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        // 2. 设置
        animation.setDuration(4000);
        // 3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void alpha_xml(View view) {
        tv_lesson10.setText("Xml透明度动画: 从完全不透明到完全透明, 持续4s");
        //1. 定义动画文件
        //2. 加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_test);
        animation.setFillAfter(true);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void rotate_code(View view) {
        tv_lesson10.setText("Code旋转动画: 以图片中心点为中心, 从负90度到正90度, 持续5s");
        //1. 创建动画对象
        RotateAnimation animation = new RotateAnimation(-90, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //2. 设置
        animation.setDuration(5000);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void rotate_xml(View view) {
        tv_lesson10.setText("Xml旋转动画: 以左顶点为坐标, 从正90度到负90度, 持续5s");
        //1. 定义动画文件
        //2. 加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_test);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void translate_code(View view) {
        tv_lesson10.setText("Code移动动画: 向右移动一个自己的宽度, 向下移动一个自己的高度, 持续2s");
        //1. 创建动画对象
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1, Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1);
        //2. 设置
        animation.setDuration(2000);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void translate_xml(View view) {
        tv_lesson10.setText("xml移动动画: 从屏幕的右边逐渐回到原来的位置, 持续2s"); //***此效果用于界面切换的动画效果
        //1. 定义动画文件
        //2. 加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_test);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }

    public void set_code(View view) {
        tv_lesson10.setText("Code复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续1s");
        //1. 创建透明动画并设置
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        //2. 创建旋转动画并设置
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setStartOffset(2000);//延迟
        //3. 创建复合动画对象
        AnimationSet animationSet = new AnimationSet(true);
        //4. 添加两个动画
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        //5. 启动复合动画对象
        iv_lesson10.startAnimation(animationSet);
    }

    public void set_xml(View view) {
        tv_lesson10.setText("Xml复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续2s");
        //1. 定义动画文件
        //2. 加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_test);
        //3. 启动动画
        iv_lesson10.startAnimation(animation);
    }
    AnimationDrawable ad=null;
    public void drawable_start(View view) {

        ad = (AnimationDrawable) iv_drawable.getBackground();
        ad.start();
    }

    public void drawable_stop(View view) {
        ad.stop();
        //iv_lesson10.setBackgroundResource(R.drawable.atguigu);
    }
}
