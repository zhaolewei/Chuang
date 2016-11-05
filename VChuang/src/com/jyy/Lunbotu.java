package com.jyy;

import java.util.ArrayList;

import com.jyy.adapter.LunbotuAdapter;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Lunbotu {
	private Activity activity;
	private Context context;
	private ViewPager viewPager;
	private LinearLayout ll_point_container;
	private ArrayList<ImageView> imageViewList;
	private int lastEnablePoint = 0;
	private boolean isRunning = true;
	private View view;

	

	public Lunbotu(Activity activity) {
		this.activity = activity ;
		context = activity;
		
				// 初始化布局 View视图
				initViews();

				// Modle数据
				initData();

				// Controller控制器
				initAdapter();

				//开启轮循
				startLunxun();
		
	}
	/**
	 * 返回一个轮播图的View对象
	 * @return view
	 */
	public View getLunbotu(){
		return view;
	}
	private void startLunxun(){
		// 开启轮循
				new Thread() {
					@Override
					public void run() {

						while (isRunning) {
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
								}
							});
						}
					}
				}.start();
	}
	
	

	private void initViews() {
		view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.lunbotu, null);
		/**
		 * 轮播图
		 */
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			/**
			 * 处理轮播图状态改变事件
			 */
			@Override
			public void onPageScrollStateChanged(int state) {
				// 滚动时调用
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// 滚动状态变化时调用
			}

			@Override
			public void onPageSelected(int position) {
				int newPosition = position % imageViewList.size();
				// 新的条目被选中时调用
				ll_point_container.getChildAt(lastEnablePoint).setEnabled(false);
				ll_point_container.getChildAt(newPosition).setEnabled(true);
				lastEnablePoint = newPosition;
			}
		});

		ll_point_container = (LinearLayout) view.findViewById(R.id.ll_point_container);
	}

	private void initData() {

		/**
		 * 轮播图
		 */
		// 图片资源
		int[] imageResIds = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		// 初始化要展示的5个ImageView
		imageViewList = new ArrayList<ImageView>();

		ImageView imageView;
		View pointView;
		LayoutParams layoutParams;
		for (int i = 0; i < imageResIds.length; i++) {
			// 初始化要显示的图片
			imageView = new ImageView(context);
			imageView.setBackgroundResource(imageResIds[i]);
			imageViewList.add(imageView);

			// 添加小白点
			pointView = new View(context);
			pointView.setBackgroundResource(R.drawable.selector_bg_point);

			layoutParams = new LinearLayout.LayoutParams(10, 10);
			if (i != 0)
				layoutParams.leftMargin = 10;

			pointView.setEnabled(false);
			ll_point_container.addView(pointView, layoutParams);

		}
	}

	private void initAdapter() {

		/**
		 * 轮播图
		 */
		ll_point_container.getChildAt(0).setEnabled(true);
		// 设置适配器
		viewPager.setAdapter(new LunbotuAdapter(context, imageViewList));
		// 默认设置到中间某个位置
		// int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 %
		// imageViewList.size());
		// 2147483647 / 2 = 1073741823 - (1073741823 % 5)
		viewPager.setCurrentItem(5000000); // 设置到某个位置
	}

	

}
