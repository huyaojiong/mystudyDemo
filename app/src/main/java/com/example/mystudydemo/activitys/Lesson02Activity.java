package com.example.mystudydemo.activitys;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.ToastUtil;

import java.util.Calendar;

public class Lesson02Activity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox cb_simple_boy, cb_simple_girl, cb_simple_no;
    private RadioGroup rg_simlp_sex;
    private SeekBar sb_test3_loading3;
    private ProgressBar pb_test3_loading1, pb_test3_loading2;
    private Button btn_test3_alertdialog, btn_test3_simpledialog,
            btn_test3_viewdialog, btn_test3_progressdialog, btn_test3_progressdialog2,
            btn_test3_datedialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson02);

        initView();

        initData();
    }


    private void initView() {
        cb_simple_boy = findViewById(R.id.cb_simple_boy);
        cb_simple_girl = findViewById(R.id.cb_simple_girl);
        cb_simple_no = findViewById(R.id.cb_simple_no);

        rg_simlp_sex = findViewById(R.id.rg_simple_sex);

        pb_test3_loading1 = findViewById(R.id.pb_test3_loading1);
        pb_test3_loading2 = findViewById(R.id.pb_test3_loading2);

        sb_test3_loading3 = findViewById(R.id.sb_test3_loading3);

        btn_test3_alertdialog = findViewById(R.id.btn_test3_alertdialog);
        btn_test3_simpledialog = findViewById(R.id.btn_test3_simpledialog);
        btn_test3_viewdialog = findViewById(R.id.btn_test3_viewdialog);

        btn_test3_progressdialog = findViewById(R.id.btn_test3_progressdialog);
        btn_test3_progressdialog2 = findViewById(R.id.btn_test3_progressdialog2);

        btn_test3_datedialog=findViewById(R.id.btn_test3_datedialog);
    }

    private void initData() {
        rg_simlp_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //找到选中的radioButton
                RadioButton radioButton = findViewById(checkedId);
                //得到文本
                String sex = radioButton.getText().toString();

                ToastUtil.showShortToast(sex);

            }
        });


        sb_test3_loading3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int progress = sb_test3_loading3.getProgress();
                pb_test3_loading2.setProgress(progress);

                if (progress == sb_test3_loading3.getMax()) {
                    pb_test3_loading1.setVisibility(View.GONE);
                } else {
                    pb_test3_loading1.setVisibility(View.VISIBLE);
                }


            }
        });


        btn_test3_alertdialog.setOnClickListener(this);
        btn_test3_simpledialog.setOnClickListener(this);
        btn_test3_viewdialog.setOnClickListener(this);
        btn_test3_progressdialog.setOnClickListener(this);
        btn_test3_progressdialog2.setOnClickListener(this);
        btn_test3_datedialog.setOnClickListener(this);
    }


    public void confirm(View view) {
        StringBuffer sb = new StringBuffer();
        if (cb_simple_boy.isChecked()) {
            sb.append(cb_simple_boy.getText().toString()).append(" ");
        }
        if (cb_simple_girl.isChecked()) {
            sb.append(cb_simple_girl.getText().toString()).append(" ");
        }
        if (cb_simple_no.isChecked()) {
            sb.append(cb_simple_no.getText().toString());
        }
        //提示
        ToastUtil.showShortToast(sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test3_alertdialog:
                new AlertDialog.Builder(this)
                        .setTitle("标题")
                        .setMessage("你确定吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShortToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShortToast("取消");
                            }
                        })
                        .show();
                break;

            case R.id.btn_test3_simpledialog:
                final String[] items = {"红", "橙", "黄", "绿", "青", "蓝", "紫"};
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShortToast(items[which].toString());

                                dialog.dismiss();

                            }
                        })
                        .show();
                break;
            case R.id.btn_test3_viewdialog:
                View view = View.inflate(this, R.layout.dialog_view, null);
                new AlertDialog.Builder(this)
                        .setView(view)

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShortToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShortToast("取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_test3_progressdialog:
                final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中....");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        dialog.dismiss();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShortToast("加载完成!");
                            }
                        });


                    }
                }).start();
                break;

            case R.id.btn_test3_progressdialog2:
                final ProgressDialog dialog2 =new ProgressDialog(this);

                dialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                dialog2.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dialog2.setMax(20);
                        for (int i = 0; i < 20; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            dialog2.setProgress(dialog2.getProgress()+1);
                        }

                        dialog2.dismiss();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShortToast("加载完成!");
                            }
                        });


                    }
                }).start();
                break;

            case R.id.btn_test3_datedialog:
                Calendar calendar = Calendar.getInstance();

                final int year=calendar.get(Calendar.YEAR);
                final int monthOfYear = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    }
                },year,monthOfYear,dayOfMonth).show();

                break;
            default:
                break;

        }
    }
}
