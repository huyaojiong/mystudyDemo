package com.example.mystudydemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mystudydemo.R;
import com.example.mystudydemo.utils.DataUtils;


public class TitleListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //listview加载数据
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, DataUtils.TITLES));

        //默认选中第一个item
        getListView().setItemChecked(0, true);
        //显示第一个详情
        showDetail(0);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetail(position);
    }

    /**
     * 显示指定下标的详情
     * @param position
     */
    private void showDetail(int position) {
        //创建DetailFragment
        DetailFragment fragment = new DetailFragment();
        //将对应的详情数据携带过去
        Bundle args = new Bundle();
        args.putString("DETAIL", DataUtils.DETAILS[position]);
        fragment.setArguments(args);
        //将其替换到id为fl_main_container的容器布局中
        getFragmentManager().beginTransaction().replace(R.id.fr_test12, fragment).commit();
    }
}
