package com.threegroup.vchuang.ourfragment;

import java.util.ArrayList;

import com.jyy.InvestorActivity;
import com.jyy.Lunbotu;
import com.jyy.ProjectActivity;
import com.jyy.adapter.ActiveAdapter;
import com.jyy.bean.ActivityBean;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoverFragment extends Fragment {
	private View view;
	private ListView lv_activity;
	private ArrayList<ActivityBean> activityBeanList;
	private View header;
	private Context mContext;
	private ViewPager viewPager;
	private View lunbotu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mContext = getActivity();
		view = inflater.inflate(R.layout.fragment_discover, null);

		lunbotu = new Lunbotu((Activity) mContext).getLunbotu();

		// 初始化布局 View视图
		initViews();

		// Modle数据
		initData();

		// Controller控制器
		initAdapter();

		/**
		 * 设置活动listview条目的点击事件
		 */
		lv_activity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
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

	public void initAdapter() {

		/**
		 * ListView
		 */
		lv_activity.addHeaderView(lunbotu);
		lv_activity.addHeaderView(header);
		lv_activity.setAdapter(new ActiveAdapter(mContext, activityBeanList));

	}

}