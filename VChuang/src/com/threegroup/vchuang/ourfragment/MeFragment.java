package com.threegroup.vchuang.ourfragment;

import com.threegroup.vchuang.R;
import com.xj.FriendActivity;
import com.xj.MymsgActivity;
import com.xj.SettingActivity;
import com.xj.ShouCangActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MeFragment extends Fragment {
	private ImageView iv_addfriends;
	private ImageView iv_discuss;
	private ImageView iv_shoucang;
	private ImageView iv_dynamic;
	private ImageView iv_guanzhu;
	private ImageView iv_setting;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_me, null);
		
		   iv_addfriends = (ImageView)view.findViewById(R.id.iv_addfriends);
		   iv_discuss = (ImageView)view.findViewById(R.id.iv_discuss);
		   iv_shoucang = (ImageView)view.findViewById(R.id.iv_shoucang);
		   iv_dynamic = (ImageView)view.findViewById(R.id.iv_dynamic);
		   iv_guanzhu = (ImageView)view.findViewById(R.id.iv_guanzhu);
		   iv_setting = (ImageView)view.findViewById(R.id.iv_setting);
		
		iv_addfriends.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						FriendActivity.class));
			}
		});
		iv_discuss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						SettingActivity.class));
			}
		});
		iv_shoucang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						ShouCangActivity.class));
			}
		});
		iv_dynamic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						SettingActivity.class));
			}
		});
		iv_guanzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						ShouCangActivity.class));
			}
		});
		iv_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						SettingActivity.class));
			}
		});
		   
		   
		return view;
	}
	
	
}
