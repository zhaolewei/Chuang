package com.threegroup.vchuang.ourfragment;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;
import com.jyy.InvestorActivity;
import com.jyy.Lunbotu;
import com.jyy.ProjectActivity;
import com.jyy.adapter.ActiveAdapter;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.DataPackage;
import com.jyy.bean.VQuanBean;
import com.threegroup.vchuang.R;
import com.threegroup.vchuang.config.URLConfig;
import com.zlw.utils.JsonTools;
import com.zlw.utils.VolleySingleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoverFragment extends Fragment {
	private View view;
	private ListView lv_activity;
	private List<ActivityBean> activityBeanList;
	private View header;
	private Context mContext;
	private View lunbotu;
	private ActiveAdapter activeAdapter;
	private View ll_float;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mContext = getActivity();
		view = inflater.inflate(R.layout.fragment_discover, null);

		lunbotu = new Lunbotu((Activity) mContext).getLunbotu();

		// 初始化布局 View视图
		initViews();

		// Modle数据
		//initData();
		getData();

		// Controller控制器
		initAdapter();

		/**
		 * 设置活动listview条目的点击事件
		 */
		/*lv_activity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
			}
		});*/

		lv_activity.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem >= 2) {  
					ll_float.setVisibility(View.VISIBLE);  
	                } else {  
	                	ll_float.setVisibility(View.GONE);  
	                }  
			}
		});
		
		return view;
	}

	public void initViews() {

		/**
		 * ListView
		 */
		lv_activity = (ListView) view.findViewById(R.id.lv_activity);

		header = View.inflate(getActivity(), R.layout.discover_header, null);

		ll_float = view.findViewById(R.id.ll_float);
		
		
		
		/**
		 * 项目库按钮，投资人按钮
		 */
		Button btn_project = (Button) header.findViewById(R.id.btn_project);
		btn_project.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ProjectActivity.class));
			}
		});
		Button btn_investor = (Button) header.findViewById(R.id.btn_investor);
		btn_investor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), InvestorActivity.class));
			}
		});

	}

	public void initData() {

		/**
		 * ListView
		 */
		activityBeanList = new ArrayList<ActivityBean>();
		for (int i = 0; i < 10; i++) {
			ActivityBean ab = new ActivityBean(i, "V创投递直通车，助力高效融资(10月最后10席)", "北京", "2016.11.2", "", "");
			activityBeanList.add(ab);
		}
	}

	private void getData() {
		activityBeanList = new ArrayList<ActivityBean>();
		StringRequest stringRequest = new StringRequest(URLConfig.PATH + URLConfig.URL_GetActive,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						DataPackage<ActivityBean> data = JsonTools.fromJson(response,
								new TypeToken<DataPackage<ActivityBean>>() {
								}.getType());
						List<ActivityBean> list = data.datas; // 获取到的数据
						activityBeanList = list;

						Log.i("jyy", "############  "+list.size());
						System.out.println("###########");
						
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
		Log.i("jyy", "刷新界面");

		activeAdapter.setList(activityBeanList);
		activeAdapter.notifyDataSetChanged();
	}

	public void initAdapter() {

		/**
		 * ListView
		 */
		lv_activity.addHeaderView(lunbotu);
		lv_activity.addHeaderView(header);
		
		activeAdapter = new ActiveAdapter(mContext, activityBeanList);
		lv_activity.setAdapter(activeAdapter);

	}

}