package com.threegroup.vchuang.ourfragment;

import java.util.ArrayList;

import com.jyy.bean.ActivityBean;
import com.threegroup.vchuang.ProjectActivity;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
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

public class DiscoverFragment extends Fragment implements OnPageChangeListener,OnClickListener{
	private ViewPager viewPager;
	private LinearLayout ll_point_container;
	private View view;
	private int[] imageResIds;
	private ArrayList<ImageView> imageViewList;
	private int lastEnablePoint=0;
	private boolean isRunning = false;
	private ListView lv_activity;
	private ArrayList<ActivityBean> activityBeanList;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_discover, null);
		
		//初始化布局  View视图
		initViews();
		
		//Modle数据
		initData();
		
		//Controller控制器
		initAdapter();
		
		//开启轮循
		new Thread() {
			@Override
			public void run() {

				isRunning = true;
				while (isRunning) {

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					((Activity) getContext()).runOnUiThread(new Runnable() {

						@Override
						public void run() {
							viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
						}
					});
				}

			}
		}.start();
		
		
		lv_activity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
			}
		});
		
		
		return view;
	}
	
	
	@Override
	public void onDestroy() {
		isRunning = false;
		super.onDestroy();
	}
	
	public void initViews() {

		/**
		 * 轮播图
		 */
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		viewPager.setOnPageChangeListener(this);
		
		ll_point_container = (LinearLayout) view.findViewById(R.id.ll_point_container);
		
		/**
		 * 项目库按钮，投资人按钮
		 */
		Button btn_project = (Button) view.findViewById(R.id.btn_project);
		btn_project.setOnClickListener(this);
		Button btn_investor = (Button) view.findViewById(R.id.btn_investor);
		btn_investor.setOnClickListener(this);
		
		/**
		 * ListView
		 */
		lv_activity = (ListView) view.findViewById(R.id.lv_activity);
		
		
	}

	public void initData() {

		/**
		 * 轮播图
		 */
		//图片资源
		int[] imageResIds = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
		//初始化要展示的5个ImageView
		imageViewList = new ArrayList<ImageView>();
		
		ImageView imageView;
		View pointView;
		LayoutParams layoutParams;
		for(int i=0 ; i<imageResIds.length ; i++){
			//初始化要显示的图片
			imageView = new ImageView(getContext());
			imageView.setBackgroundResource(imageResIds[i]);
			imageViewList.add(imageView);
			
			//添加小白点
			pointView = new View(getContext());
			pointView.setBackgroundResource(R.drawable.selector_bg_point);
			
			layoutParams = new LinearLayout.LayoutParams(10, 10);
			if(i != 0 )
				layoutParams.leftMargin = 10;
			
			pointView.setEnabled(false);
			ll_point_container.addView(pointView,layoutParams);
			
		}
		/**
		 * ListView
		 */
		activityBeanList = new ArrayList<ActivityBean>();
		
		for(int i=0 ; i<10 ; i++){
			ActivityBean ab = new ActivityBean(i, "V创投递直通车，助力高效融资(10月最后10席)", "北京", "2016.11.2", R.drawable.a1);
			activityBeanList.add(ab);
		}
	}

	public void initAdapter() {

		/**
		 * 轮播图
		 */
		ll_point_container.getChildAt(0).setEnabled(true);
		//设置适配器
		viewPager.setAdapter(new MyPagerAdapter());
		//默认设置到中间某个位置
		//int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
		// 2147483647 / 2 = 1073741823 - (1073741823 % 5)
		viewPager.setCurrentItem(5000000); // 设置到某个位置

		/**
		 * ListView
		 */
		lv_activity.setAdapter(new MyBaseAdapter());
		
	}
	
	/**
	 * ListView
	 */
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return activityBeanList.size();
		}

		@Override
		public Object getItem(int position) {
			return activityBeanList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return activityBeanList.get(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView == null ? View.inflate(getContext(), R.layout.activity_item, null) : convertView;
			TextView tv_title_activity = (TextView) v.findViewById(R.id.tv_title_activity);
			TextView tv_location = (TextView) v.findViewById(R.id.tv_location);
			TextView tv_time = (TextView) v.findViewById(R.id.tv_time);
			ImageView iv_activity = (ImageView) v.findViewById(R.id.iv_activity);
			
			ActivityBean a = activityBeanList.get(position);
			tv_title_activity.setText(a.getTitle());
			tv_location.setText(a.getLocation()); 
			tv_time.setText(a.getTime());
			iv_activity.setImageResource(a.getPic());
			return v;
		}
		
	}
	
	/**
	 * 轮播图
	 */
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		//指定复用的判断逻辑
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// 当划到新的条目, 又返回来, view是否可以被复用.
			// 返回判断规则
			return view == object;
		}
		
		//返回要显示的条目内容
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int newPosition = position%imageViewList.size();
			ImageView imageView = imageViewList.get(newPosition);
			// a. 把View对象添加到container中
			container.addView(imageView);
			// b. 把View对象返回给框架, 适配器
			return imageView;  // 必须重写, 否则报异常
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View)object);
		}
	}
/**
 * 处理轮播图状态改变事件
 */
	@Override
	public void onPageScrollStateChanged(int state) {
		//滚动时调用
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//滚动状态变化时调用
	}

	@Override
	public void onPageSelected(int position) {
		int newPosition = position%imageViewList.size();
		//新的条目被选中时调用
		ll_point_container.getChildAt(lastEnablePoint).setEnabled(false);
		ll_point_container.getChildAt(newPosition).setEnabled(true);
		lastEnablePoint = newPosition;
	}

/**
 * 处理点击事件
 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_project:
			startActivity(new Intent(getActivity(),ProjectActivity.class));
			break;
		case R.id.btn_investor:
			Toast.makeText(getActivity(),"投资人列表",Toast.LENGTH_SHORT).show();
			break;

		}

	}
}