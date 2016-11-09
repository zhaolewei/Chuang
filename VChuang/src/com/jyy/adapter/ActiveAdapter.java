package com.jyy.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.VQuanBean;
import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActiveAdapter extends BaseAdapter {
	private Context context;
	private List<ActivityBean> activityBeanList;

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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = View.inflate(context, R.layout.activity_item, null);
			viewHolder.tv_title_activity = (TextView) convertView.findViewById(R.id.tv_title_activity);
			viewHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
			viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			viewHolder.iv_activity = (ImageView) convertView.findViewById(R.id.iv_activity);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ActivityBean a = activityBeanList.get(position);
		viewHolder.tv_title_activity.setText(a.getTitle());
		viewHolder.tv_location.setText(a.getLocation());
		viewHolder.tv_time.setText(a.getTime());
		
		// 通过网络地址设置图片
		Glide.with(MyApplication.getContext()).load(a.getPic_url()).diskCacheStrategy(DiskCacheStrategy.ALL)
		.placeholder(R.drawable.icon_default).into(viewHolder.iv_activity);
		//viewHolder.iv_activity.setImageResource(R.drawable.test_user_photo);
		
		
		return convertView;
		
		/*View v = convertView == null ? View.inflate(context, R.layout.activity_item, null) : convertView;
		TextView tv_title_activity = (TextView) v.findViewById(R.id.tv_title_activity);
		TextView tv_location = (TextView) v.findViewById(R.id.tv_location);
		TextView tv_time = (TextView) v.findViewById(R.id.tv_time);
		ImageView iv_activity = (ImageView) v.findViewById(R.id.iv_activity);

		ActivityBean a = activityBeanList.get(position);
		tv_title_activity.setText(a.getTitle());
		tv_location.setText(a.getLocation());
		tv_time.setText(a.getTime());
		iv_activity.setImageResource(R.drawable.test_user_photo);

		// 设置图片的标记，在更新ui时判断下url是否一致，防止图片重复
		iv_activity.setTag(a.getPic_url());
		return v;*/
		
	}

	private final class ViewHolder{
		TextView tv_title_activity;
		TextView tv_location;
		TextView tv_time;
		ImageView iv_activity;
	}
	
}
