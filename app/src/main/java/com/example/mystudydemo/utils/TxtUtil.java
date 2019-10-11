package com.example.mystudydemo.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class TxtUtil {
    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName + ".txt");
            //context.getClass().getClassLoader().getResourceAsStream("assets/"+资源名);
            int size = is.available();  ////获取信息的长度
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];   //创建字节数组用来存储流数据
            is.read(buffer);          //将流数据读入字节数组中
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }
}