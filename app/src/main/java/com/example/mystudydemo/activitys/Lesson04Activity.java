package com.example.mystudydemo.activitys;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.SPUtil;
import com.example.mystudydemo.utils.ToastUtil;

public class Lesson04Activity extends AppCompatActivity {


    public LinearLayout ll_main;
    private EditText dialog_name, dialog_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson04);

        initView();

    }

    private void initView() {

        ll_main = findViewById(R.id.ll_content_user);

        dialog_name = ll_main.findViewById(R.id.dialag_name);
        dialog_password = ll_main.findViewById(R.id.dialag_password);

    }

    public void spSave(View view) {

        SPUtil.put(Lesson04Activity.this, dialog_name.getText().toString(), dialog_password.getText().toString());
        ToastUtil.showShortToast("保存成功");
    }


    public void inphoneSave(View view) {
    }

    public void outphoneSave(View view) {
    }

    public void spLoad(View view) {
        dialog_password.setText(
        SPUtil.get(Lesson04Activity.this,dialog_name.getText().toString(),"").toString());
    }
}
