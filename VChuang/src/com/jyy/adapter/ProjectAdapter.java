package com.jyy.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.jyy.bean.ProjectBean;
import com.jyy.view.ImageViewForUrl;
import com.threegroup.vchuang.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ProjectBean> projectList;

	// 通过构造方法接受要显示的项目数据集合
	public ProjectAdapter(Context context, ArrayList<ProjectBean> projectList) {

		this.context = context;
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
		View v = convertView == null ? View.inflate(context, R.layout.project_item, null) : convertView;
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
		tv_support.setText(pb.getSupport());
		img_logo.setImageResource(R.drawable.test_user_photo);

		// 设置图片的标记，在更新ui时判断下url是否一致，防止图片重复
		img_logo.setTag(pb.getLogo_url());

		return v;
	}

}
