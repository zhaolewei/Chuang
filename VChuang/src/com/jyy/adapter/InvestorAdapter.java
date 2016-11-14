package com.jyy.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.InvestorBean;
import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;
import com.zlw.view.CircleImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InvestorAdapter extends BaseAdapter {

	private Context context;
	private List<InvestorBean> InvestorList;

	// 通过构造方法接受要显示的项目数据集合
	public InvestorAdapter(Context context, List<InvestorBean> InvestorList) {

		this.context = context;
		this.InvestorList = InvestorList;

	}

	public void setList(List<InvestorBean> InvestorList) {
		this.InvestorList = InvestorList;
	}
	
	@Override
	public int getCount() {
		return InvestorList.size();
	}

	@Override
	public Object getItem(int position) {
		return InvestorList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return InvestorList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = View.inflate(context, R.layout.investor_item, null);
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name_investor_item);
			viewHolder.tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce_investor_item);
			viewHolder.tv_harvestnum = (TextView) convertView.findViewById(R.id.tv_hanrvestnum_investor_item);
			viewHolder.img_icon = (CircleImageView) convertView.findViewById(R.id.img_icon_investor_item);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		InvestorBean ib = InvestorList.get(position);
		viewHolder.tv_name.setText(ib.getName());
		viewHolder.tv_introduce.setText(ib.getIntroduce());
		viewHolder.tv_harvestnum.setText(ib.getHarvest_num() + "");
		
		//  通过网络地址设置图片
		Glide.with(MyApplication.getContext()).load(ib.getIcon_url()).diskCacheStrategy(DiskCacheStrategy.ALL)
		.placeholder(R.drawable.icon_default).into(viewHolder.img_icon);
		
		return convertView;
	}
	private final class ViewHolder{
		TextView tv_name ;
		TextView tv_introduce ;
		TextView tv_harvestnum ;
		CircleImageView img_icon ;
		
	}
	
}
