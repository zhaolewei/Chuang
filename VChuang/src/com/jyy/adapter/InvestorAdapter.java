package com.jyy.adapter;

import java.util.List;

import com.jyy.bean.InvestorBean;
import com.threegroup.vchuang.R;
import com.zlw.view.CircleImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InvestorAdapter extends BaseAdapter {

	private Context context;
	private List<InvestorBean> InvestorList;

	// 通过构造方法接受要显示的项目数据集合
	public InvestorAdapter(Context context, List<InvestorBean> InvestorList) {

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
		View v = convertView == null ? View.inflate(context, R.layout.investor_item, null) : convertView;
		TextView tv_name = (TextView) v.findViewById(R.id.tv_name_investor_item);
		TextView tv_introduce = (TextView) v.findViewById(R.id.tv_introduce_investor_item);
		TextView tv_harvestnum = (TextView) v.findViewById(R.id.tv_hanrvestnum_investor_item);
		CircleImageView img_icon = (CircleImageView) v.findViewById(R.id.img_icon_investor_item);

		InvestorBean ib = InvestorList.get(position);
		tv_name.setText(ib.getName());
		tv_introduce.setText(ib.getIntroduce());
		tv_harvestnum.setText(ib.getHarvest_num() + "");
		img_icon.setImageResource(R.drawable.test_user_photo);

		// 设置图片的标记，在更新ui时判断下url是否一致，防止图片重复
		img_icon.setTag(ib.getIcon_url());

		return v;
	}

}
