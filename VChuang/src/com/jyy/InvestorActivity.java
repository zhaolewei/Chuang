package com.jyy;

import java.util.ArrayList;

import com.jyy.adapter.InvestorAdapter;
import com.jyy.bean.InvestorBean;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class InvestorActivity extends Activity {

	private Context mContext;
	private ArrayList<InvestorBean> investorList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_investor);
		
		mContext = this;
		investorList = new ArrayList<InvestorBean>();
		
		ListView lv = (ListView) findViewById(R.id.lv_investor);
		lv.setAdapter(new InvestorAdapter(mContext, investorList));
		
		//给返回按钮设置点击事件
		ImageButton imgbtn_back = (ImageButton) findViewById(R.id.imgbtn_back_investor);
		imgbtn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				finish();
			}
		});
		
		ImageButton imgbtn_search = (ImageButton) findViewById(R.id.imgbtn_search_investor);
		
		
		//初始化investorList数据
		for (int i = 0; i < 10; i++) {
			InvestorBean ib = new InvestorBean(i, "汪涵", "投资经理，中银投资浙商产业基金", "1151", R.drawable.c); 
			investorList.add(ib);
			
		}
		
		//给listview条目设置点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}
}
