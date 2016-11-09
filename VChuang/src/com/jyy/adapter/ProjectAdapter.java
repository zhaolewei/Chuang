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
import android.view.ViewGroup;
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
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView =  View.inflate(context, R.layout.project_item, null);
			viewHolder. tv_title = (TextView) convertView.findViewById(R.id.tv_title_project_item);
			viewHolder. tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce_project_item);
			viewHolder. tv_location = (TextView) convertView.findViewById(R.id.tv_location_project_item);
			viewHolder. tv_support = (TextView) convertView.findViewById(R.id.tv_support_project_item);
			viewHolder. img_logo = (com.zlw.view.CircleImageView) convertView
					.findViewById(R.id.img_logo_project_item);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ProjectBean pb = projectList.get(position);
		viewHolder.tv_title.setText(pb.getTitle());
		viewHolder.tv_introduce.setText(pb.getIntroduce());
		viewHolder.tv_location.setText(pb.getLocation());
		viewHolder.tv_support.setText(pb.getZan_count() + "");
		
		
		//  通过网络地址设置图片
		Glide.with(MyApplication.getContext()).load(pb.getLogo_url()).diskCacheStrategy(DiskCacheStrategy.ALL)
		.placeholder(R.drawable.icon_default).into(viewHolder.img_logo);
		//viewHolder.img_logo.setImageResource(R.drawable.test_user_photo);
		
		return convertView;
		/*View v = convertView == null ? View.inflate(context, R.layout.project_item, null) : convertView;
		TextView tv_title = (TextView) v.findViewById(R.id.tv_title_project_item);
		TextView tv_introduce = (TextView) v.findViewById(R.id.tv_introduce_project_item);
		TextView tv_location = (TextView) v.findViewById(R.id.tv_location_project_item);
		TextView tv_support = (TextView) v.findViewById(R.id.tv_support_project_item);
		com.zlw.view.CircleImageView img_logo = (com.zlw.view.CircleImageView) v
				.findViewById(R.id.img_logo_project_item);

		ProjectBean pb = projectList.get(position);
		tv_title.setText(pb.getTitle());
		tv_introduce.setText(pb.getIntroduce());
		tv_location.setText(pb.getLocation());
		tv_support.setText(pb.getZan_count() + "");
		img_logo.setImageResource(R.drawable.test_user_photo);

		// 设置图片的标记，在更新ui时判断下url是否一致，防止图片重复
		img_logo.setTag(pb.getLogo_url());

		return v;*/
	}
	private final class ViewHolder{
		TextView tv_title ;
		TextView tv_introduce ;
		TextView tv_location ;
		TextView tv_support;
		com.zlw.view.CircleImageView img_logo ;
	}
}
