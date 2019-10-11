package com.example.mystudydemo.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.AndroidException;
import android.view.View;

import com.example.mystudydemo.R;

public class Lesson07Service extends Service {


    private MediaPlayer player;

    public Lesson07Service() {

    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String action =intent.getStringExtra("action");
        if(action!=null) {
            switch (action) {
                case "start":
                    startMusic();
                    break;
                case "stop":
                    stopMusic();
                    break;
                case "pause":
                    pauseMusic();
                    break;
                default:
                    break;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        stopMusic();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    public void startMusic() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.water_hander);

        }
        player.start();


    }

    public void stopMusic() {
        if (player != null) {
            player.stop();  //停止
            player.reset();   //重置
            player.release();   //释放资源
            player = null;
        }
    }

    public void pauseMusic() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public void exitMusic() {

        stopMusic();

    }


}
