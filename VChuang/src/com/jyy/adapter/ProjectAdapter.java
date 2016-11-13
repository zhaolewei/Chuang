package com.jyy.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.InvestorBean;
import com.jyy.bean.ProjectBean;
import com.jyy.view.ImageViewForUrl;
import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectAdapter extends BaseAdapter {

	private Context context;
	private List<ProjectBean> projectList;

	// 通过构造方法接受要显示的项目数据集合
	public ProjectAdapter(Context context, List<ProjectBean> projectList) {

		this.context = context;
		this.projectList = projectList;

	}

	public void setList(List<ProjectBean> projectList) {
		this.projectList = projectList;
	}
	
	@Override
	public int getCount() {
		return projectList.size();
	}

	@Override
	public Object getItem(int position) {
		return projectList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return projectList.get(position).getId();
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView =  View.inflate(context, R.layout.project_item, null);
			viewHolder. tv_title = (TextView) convertView.findViewById(R.id.tv_title_project_item);
			viewHolder. tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce_project_item);
			viewHolder. tv_location = (TextView) convertView.findViewById(R.id.tv_location_project_item);
			viewHolder. tv_support = (TextView) convertView.findViewById(R.id.tv_support_project_item);
			viewHolder. img_logo = (com.zlw.view.CircleImageView) convertView
					.findViewById(R.id.img_logo_project_item);
			viewHolder. iv_zan = (ImageView) convertView.findViewById(R.id.iv_zan_project);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ProjectBean pb = projectList.get(position);
		viewHolder.tv_title.setText(pb.getTitle());
		viewHolder.tv_introduce.setText(pb.getIntroduce());
		viewHolder.tv_location.setText(pb.getLocation());
		viewHolder.tv_support.setText(pb.getZan_count() + "");
		//设置iv_zan的点击事件
		viewHolder.iv_zan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//设置点赞的放大动画
				ScaleAnimation sa = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
						Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				sa.setDuration(300);
				sa.setRepeatCount(1);
				sa.setRepeatMode(Animation.REVERSE);
				v.startAnimation(sa);
				//
				viewHolder.tv_support.setText(Integer.parseInt((String) viewHolder.tv_support.getText())+1+"");
				
			}
		});
		
		//  通过网络地址设置图片
		Glide.with(MyApplication.getContext()).load(pb.getLogo_url()).diskCacheStrategy(DiskCacheStrategy.ALL)
		.placeholder(R.drawable.icon_default).into(viewHolder.img_logo);
		//viewHolder.img_logo.setImageResource(R.drawable.test_user_photo);
		
		return convertView;
		
	}
	private final class ViewHolder{
		TextView tv_title ;
		TextView tv_introduce ;
		TextView tv_location ;
		TextView tv_support;
		com.zlw.view.CircleImageView img_logo ;
		ImageView iv_zan;
	}
}
