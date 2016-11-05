package com.jyy.adapter;

import java.util.ArrayList;

import com.jyy.bean.InvestorBean;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LunbotuAdapter extends PagerAdapter {

	private Context context;
	private ArrayList<ImageView> imageViewList;
	
	public LunbotuAdapter(Context context ,ArrayList<ImageView> imageViewList ) {
		this.context = context;
		this.imageViewList = imageViewList;
	}
	
	

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		// 指定复用的判断逻辑
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// 当划到新的条目, 又返回来, view是否可以被复用.
			// 返回判断规则
			return view == object;
		}

		// 返回要显示的条目内容
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int newPosition = position % imageViewList.size();
			ImageView imageView = imageViewList.get(newPosition);
			// a. 把View对象添加到container中
			container.addView(imageView);
			// b. 把View对象返回给框架, 适配器
			return imageView; // 必须重写, 否则报异常
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}
	

}
