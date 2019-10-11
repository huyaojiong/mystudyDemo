package com.example.mystudydemo.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mystudydemo.R;
import com.example.mystudydemo.entity.TuchongEntity;
import com.example.mystudydemo.utils.DataUtils;
import com.example.mystudydemo.utils.ScreenUtil;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private XBanner mBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBanner();
        initData();
    }

    private void initView()
    {
        mBanner= findViewById(R.id.xbanner);
        mBanner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(this) *9/ 16));
        gridView = findViewById(R.id.gridView);

    }

    private void initBanner() {
        //设置广告图片点击事件
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });
        //加载广告图片
        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
                TuchongEntity.FeedListBean.EntryBean listBean = ((TuchongEntity.FeedListBean.EntryBean) model);
                String url = "https://photo.tuchong.com/" + listBean.getImages().get(0).getUser_id() + "/f/" + listBean.getImages().get(0).getImg_id() + ".jpg";
                Glide.with(MainActivity.this).load(url).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });
        List<TuchongEntity.FeedListBean.EntryBean> data = new ArrayList<>();
        mBanner.setBannerData(data);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        //加载网络图片资源
        String url = "https://api.tuchong.com/2/wall-paper/app";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "加载广告数据失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        TuchongEntity advertiseEntity = new Gson().fromJson(response, TuchongEntity.class);
                        List<TuchongEntity.FeedListBean> others = advertiseEntity.getFeedList();
                        List<TuchongEntity.FeedListBean.EntryBean> data = new ArrayList<>();
                        for (int i = 0; i <others.size(); i++) {
                            TuchongEntity.FeedListBean feedListBean = others.get(i);
                            if ("post".equals(feedListBean.getType())) {
                                data.add(feedListBean.getEntry());
                            }
                        }
                        //刷新数据之后，需要重新设置是否支持自动轮播
                        mBanner.setAutoPlayAble(data.size() > 1);
                        mBanner.setBannerData(data);
                    }
                });

        List<Map<String, Object>> item = getData();
        // SimpleAdapter对象，匹配ArrayList中的元素
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, item, R.layout.gridviewitem, new String[] { "itemImage", "itemName" }, new int[] { R.id.itemImage, R.id.itemName });
        gridView.setAdapter(simpleAdapter);
        // 添加点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int index = arg2 + 1;// id是从0开始的，所以需要+1
                //Toast.makeText(getApplicationContext(), "你按下了选项：" + index, 0).show();
                Intent intent=null;
                switch (index)
                {
                    case 1: intent=new Intent(MainActivity.this,Lesson01Activity.class);
                            startActivity(intent); break;
                    case 2: intent=new Intent(MainActivity.this,Lesson02Activity.class);
                        startActivity(intent); break;
                    case 3: intent=new Intent(MainActivity.this,Lesson03Activity.class);
                        startActivity(intent); break;
                    case 4: intent=new Intent(MainActivity.this,Lesson04Activity.class);
                        startActivity(intent); break;
                    case 6: intent=new Intent(MainActivity.this,Lesson06Activity.class);
                        startActivity(intent); break;
                    case 7: intent=new Intent(MainActivity.this,Lesson07Activity.class);
                        startActivity(intent); break;
                    case 12: intent=new Intent(MainActivity.this,Lesson12Activity.class);
                        startActivity(intent); break;
                    default:
                        break;

                }

            }
        });
    }
    /**
     * 将图标图片和图标名称存入ArrayList中
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();


        for (int i = 0; i < DataUtils.listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", DataUtils.listImg[i]);
            item.put("itemName", DataUtils.listName[i]);
            items.add(item);
        }
        return items;

    }

    public void onClick(View view) {
        mBanner.setBannerCurrentItem(2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
