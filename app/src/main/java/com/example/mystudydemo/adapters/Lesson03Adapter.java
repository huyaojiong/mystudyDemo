package com.example.mystudydemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudydemo.R;
import com.example.mystudydemo.entity.ShopInfo;
import com.example.mystudydemo.utils.ImageLoader;

import java.util.List;

public class Lesson03Adapter extends BaseAdapter {

    private List<ShopInfo> lists;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public Lesson03Adapter(Context context, List<ShopInfo> lists) {
        this.lists = lists;
        this.inflater = LayoutInflater.from(context);
        this.imageLoader = new ImageLoader(context, R.drawable.loading, R.drawable.error);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.item_base_adapter, null);

            viewHolder.imageView = convertView.findViewById(R.id.iv_item_baseicon);
            viewHolder.textView = convertView.findViewById(R.id.tv_item_basename);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ShopInfo bean = lists.get(position);

        viewHolder.imageView.setImageResource(bean.getIcon());
        viewHolder.textView.setText(bean.getName());

        //imageLoader.loadImage(imagePath,viewHolder.imageView);

        return convertView;
    }


    private class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }
}
