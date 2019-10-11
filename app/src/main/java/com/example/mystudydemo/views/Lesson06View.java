package com.example.mystudydemo.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.mystudydemo.activitys.Lesson06Activity;


public class Lesson06View extends ImageView {

    private static String tag="Lesson06View";
    public Lesson06View(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(tag,"dispatchTouchEvent()"+event.getAction());
        //Lesson06Activity.logs+="Lesson06View "+ "dispatchTouchEvent() " + event.getAction()+"\n";
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Lesson06Activity.logs+="Lesson06View"+ " onTouchEvent() " + event.getAction()+"\n";
        Log.e(tag,"onTouchEvent()"+event.getAction());
        return super.onTouchEvent(event);
    }
}
