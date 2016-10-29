package com.zlw.mymodel.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.R;
import com.zlw.mymodel.adapter.RecyclerViewAdapter;
import com.zlw.mymodel.service.DataService;
import com.zlw.mymodel.service.Item;
import com.zlw.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <页面一>
 * Created by zlw on 2016/6/28 0028.
 */
public class ListFragment extends Fragment {

    private static int COUNT = 10;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private DataService dataService;
    private int index = 0;  //当前数据量


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataService = DataService.getInstance();

        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        // mPullLoadMoreRecyclerView.setRefreshing(true);//设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(false);//设置是否下拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);//设置是否上拉刷新
        mPullLoadMoreRecyclerView.setFooterViewText("正在加载...");//设置上拉刷新文字
        mPullLoadMoreRecyclerView.setLinearLayout();
        //加载适配器
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), getInitData());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置监听事件
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
//        mPullLoadMoreRecyclerView.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null));//setEmptyView
    }


    //============配置加载回调事件===============

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        /**
         * 上拉刷新回调
         */
        @Override
        public void onRefresh() {
            getData2();
        }

        /**
         * 下拉加载回调
         */
        @Override
        public void onLoadMore() {
            getData();
        }
    }

    //==========数据操作==============


    /**
     * 获取增加后的增加数据
     */
    private List<Item> getList() {
        List<Item> data = new ArrayList<Item>();
        List<Item> lists = dataService.getDataByStart(index, COUNT); //获取的数据
        data.addAll(mRecyclerViewAdapter.getDataList()); //原始数据
        Log.i("zlw", "getList|size1:" + data.size());
        data.addAll(lists);
        Log.i("zlw", "getList|size2:" + data.size());
        index += lists.size();
        return data;
    }

    /**
     * 获取初始数据
     */
    private List<Item> getInitData() {
        List<Item> data = new ArrayList<Item>();
        data.addAll(dataService.getDataByStart(0, COUNT));
        index = COUNT;
        return data;
    }

    /**
     * 加载下拉后的数据
     */
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.setDataList(getList());
                        mRecyclerViewAdapter.notifyDataSetChanged();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 2000);
    }

    /**
     * 加载初始数据
     */
    private void getData2() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.setDataList(getInitData());
                        mRecyclerViewAdapter.notifyDataSetChanged();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 2000);
    }


}
