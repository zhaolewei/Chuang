package com.jyy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.VQuanBean;
import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActiveAdapter extends BaseAdapter {
	private Context context;
	private List<ActivityBean> activityBeanList;
	private int myPosition = -1;
	private List<Boolean> list = new ArrayList<Boolean>();

	public ActiveAdapter(Context context, List<ActivityBean> activityBeanList) {
		this.context = context;
		this.activityBeanList = activityBeanList;
		
	}

	public void setList(List<ActivityBean> activityBeanList) {
		this.activityBeanList = activityBeanList;
	}

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
	public View getView(final int position, View convertView, ViewGroup parent) {
		for(int i=0; i<activityBeanList.size(); i++){
			list.add(i,false);
		}
		myPosition=position;
		final ViewHolder viewHolder;
		AnimatorSet set;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate( R.layout.activity_item, parent,false);
			viewHolder.tv_title_activity = (TextView) convertView.findViewById(R.id.tv_title_activity);
			viewHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
			viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			viewHolder.iv_activity = (ImageView) convertView.findViewById(R.id.iv_activity);
			viewHolder.tv_activity_content = (TextView) convertView.findViewById(R.id.tv_activity_content);
			viewHolder.rl_item = convertView.findViewById(R.id.rl_item);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			 set=(AnimatorSet) viewHolder.tv_activity_content.getTag();
//			if(set!=null&&set.isRunning()){
//				set.end();
//			}
		}

		ActivityBean a = activityBeanList.get(position);
		viewHolder.tv_title_activity.setText(a.getTitle());
		viewHolder.tv_location.setText(a.getLocation());
		viewHolder.tv_time.setText(a.getTime());

		viewHolder.tv_activity_content.setText(a.getContent());
		
		viewHolder.tv_activity_content.setVisibility(View.GONE);
		// 组合属性动画
		 set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.set_jyy_3);
		set.setTarget(viewHolder.rl_item);
		set.start();
		viewHolder.tv_activity_content.setVisibility(View.GONE);
		
		// 通过网络地址设置图片
		Glide.with(MyApplication.getContext()).load(a.getPic_url()).diskCacheStrategy(DiskCacheStrategy.ALL)
				.placeholder(R.drawable.icon_default).into(viewHolder.iv_activity);
		// viewHolder.iv_activity.setImageResource(R.drawable.test_user_photo);
		
		Log.i("jyy", "我的位置----"+myPosition);
		Log.i("jyy", "位置----"+position);
			// 添加点击事件
			viewHolder.rl_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.i("jyy", "我的位置****"+myPosition);
					Log.i("jyy", "位置****"+position);
					
					list.set(position, true);
					if(list.get(position)){
						// 组合属性动画
						AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.set_jyy_1);
						set.setTarget(viewHolder.rl_item);
						set.start();
						v.setTag(set);
						new Thread(new Runnable() {

							@Override
							public void run() {
								try {
									Thread.sleep(1500);

									Message msg = new Message();
									msg.obj = viewHolder;
									handler.sendMessage(msg);
									// viewHolder.tv_activity_content.setVisibility(View.VISIBLE);

								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}).start();
					}
					
					
					

				}
			});
		

		viewHolder.tv_activity_content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 组合属性动画
				AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.set_jyy_2);
				set.setTarget(viewHolder.rl_item);
				set.start();
				viewHolder.tv_activity_content.setVisibility(View.GONE);
				
			}
		});

		return convertView;


	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			ViewHolder viewHolder = (ViewHolder) msg.obj;

			viewHolder.tv_activity_content.setVisibility(View.VISIBLE);
			super.handleMessage(msg);
		}

	};

	private final class ViewHolder {
		TextView tv_title_activity;
		TextView tv_location;
		TextView tv_time;
		ImageView iv_activity;
		TextView tv_activity_content;
		View rl_item;

	}

}
