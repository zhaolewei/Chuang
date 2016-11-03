package com.jyy.adapter;

import java.util.ArrayList;

import com.jyy.bean.InvestorBean;
import com.jyy.bean.ProjectBean;
import com.jyy.view.ImageViewForUrl;
import com.threegroup.vchuang.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InvestorAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<InvestorBean> InvestorList;
	
	// 通过构造方法接受要显示的项目数据集合
	public InvestorAdapter(Context context , ArrayList<InvestorBean> InvestorList) {
		
		this.context = context;
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
		View v= convertView == null? View.inflate(context, R.layout.investor_item, null):convertView;
		TextView tv_name = (TextView) v.findViewById(R.id.tv_name_investor_item);
		TextView tv_introduce = (TextView) v.findViewById(R.id.tv_introduce_investor_item);
		TextView tv_harvestnum = (TextView) v.findViewById(R.id.tv_hanrvestnum_investor_item);
		ImageViewForUrl img_icon = (ImageViewForUrl) v.findViewById(R.id.img_icon_investor_item);
		
		InvestorBean ib = InvestorList.get(position);
		tv_name.setText(ib.getName());
		tv_introduce.setText(ib.getIntroduce());
		tv_harvestnum.setText(ib.getHarvestnum());
		img_icon.setBackgroundResource(ib.getIcon());
		
		return v;
	}

}
