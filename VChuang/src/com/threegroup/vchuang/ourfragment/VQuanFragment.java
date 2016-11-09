package com.threegroup.vchuang.ourfragment;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;
import com.jyy.bean.DataPackage;
import com.jyy.bean.VQuanBean;
import com.threegroup.vchuang.R;
import com.threegroup.vchuang.config.URLConfig;
import com.zlw.adapter.VQuanRecycleViewAdapter;
import com.zlw.adapter.VQuanRecycleViewAdapter.OnItemClickListener;
import com.zlw.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zlw.pullloadmorerecyclerview.PullLoadMoreRecyclerView.PullLoadMoreListener;
import com.zlw.utils.JsonTools;
import com.zlw.utils.VolleySingleton;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

/**
 * @author 赵乐玮
 *
 */
public class VQuanFragment extends Fragment {
	private PullLoadMoreRecyclerView rv;
	private FloatingActionButton fab;
	private Toolbar toolbar;

	private List<VQuanBean> list = new ArrayList<VQuanBean>();
	private VQuanRecycleViewAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_vquan, null);
		initView(view);

		initData();

		return view;
	}

	/**
	 * 网络请求- 加载数据
	 */
	private void initData() {

		StringRequest stringRequest = new StringRequest(URLConfig.PATH + URLConfig.URL_GetVQuan,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						DataPackage<VQuanBean> data = JsonTools.fromJson(response,
								new TypeToken<DataPackage<VQuanBean>>() {
								}.getType());
						list = data.datas;
						Log.i("zlw", "list.size:" + list.size());

						flushList();// 刷新界面List
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("zlw", "error:" + error);
					}
				});
		VolleySingleton.getVolleySingleton(getContext()).addToRequestQueue(stringRequest);
	}

	private void flushList() {
		Log.i("zlw", "刷新界面");

		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}

	private void initView(View view) {
		rv = (PullLoadMoreRecyclerView) view.findViewById(R.id.rv);
		rv.setLinearLayout();
		rv.setFooterViewText("loading");

		// rv.setRefreshing(true);// 设置下拉刷新是否可见
		rv.setPullRefreshEnable(true);// 设置是否下拉刷新
		rv.setPushRefreshEnable(true);// 设置是否上拉刷新
		rv.setOnPullLoadMoreListener(new PullLoadMoreListener() {

			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								rv.setPullLoadMoreCompleted();
								Toast.makeText(getActivity(), "刷新成功", 0).show();
							}
						});
					}
				}, 2000);

			}

			@Override
			public void onLoadMore() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								rv.setPullLoadMoreCompleted();
								Toast.makeText(getActivity(), "暂无数据", 0).show();
							}
						});
					}
				}, 2000);

			}
		});
		fab = (FloatingActionButton) view.findViewById(R.id.fab);
		// fab.attachToRecyclerView(rv);

		toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.WHITE);
		((AppCompatActivity) getActivity()).setTitle("V创平台");

		adapter = new VQuanRecycleViewAdapter(list);

		adapter.setClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(View v, int position) {
				Toast.makeText(getContext(), "position:" + position, 0).show();
			}
		});
		rv.setAdapter(adapter);

		// ViewCompat.animate(rv).translationY(toolbar.getHeight() * 2).start();
		// Animation translateIn = new TranslateAnimation(0, 0, 0,
		// toolbar.getHeight());
		// translateIn.setDuration(500);
		// translateIn.setFillAfter(true);
		// rv.startAnimation(translateIn);
	}

}
