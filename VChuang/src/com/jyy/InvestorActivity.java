package com.jyy;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;
import com.jyy.adapter.InvestorAdapter;
import com.jyy.bean.DataPackage;
import com.jyy.bean.InvestorBean;
import com.jyy.bean.ProjectBean;
import com.threegroup.vchuang.R;
import com.threegroup.vchuang.config.URLConfig;
import com.zlw.utils.JsonTools;
import com.zlw.utils.VolleySingleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class InvestorActivity extends Activity {

	private Context mContext;
	private List<InvestorBean> investorList;
	private InvestorAdapter investorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_investor);

		mContext = this;
		investorList = new ArrayList<InvestorBean>();

		ListView lv = (ListView) findViewById(R.id.lv_investor);
		investorAdapter = new InvestorAdapter(mContext, investorList);
		lv.setAdapter(investorAdapter);

		// 给返回按钮设置点击事件
		ImageButton imgbtn_back = (ImageButton) findViewById(R.id.imgbtn_back_investor);
		imgbtn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
		// 给搜索按钮设置点击事件
		ImageButton imgbtn_search = (ImageButton) findViewById(R.id.imgbtn_search_investor);
		imgbtn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InvestorActivity.this, SearchActivity.class);
				intent.putExtra("source", "investor");
				startActivity(intent);
			}
		});

		// 初始化investorList数据
		getData();
		/*for (int i = 0; i < 10; i++) {
			InvestorBean ib = new InvestorBean(i, "汪涵", "投资经理，中银投资浙商产业基金", 113, "", "");
			investorList.add(ib);

		}*/

		// 给listview条目设置点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void getData() {
		investorList = new ArrayList<InvestorBean>();
		StringRequest stringRequest = new StringRequest(URLConfig.PATH + URLConfig.URL_GetInvestor,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						DataPackage<InvestorBean> data = JsonTools.fromJson(response,
								new TypeToken<DataPackage<InvestorBean>>() {
								}.getType());
						List<InvestorBean> list = data.datas; // 获取到的数据
						investorList = list;
						flushList();// 刷新界面List
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("zlw", "error:" + error);
					}
				});
		VolleySingleton.getVolleySingleton(getApplicationContext()).addToRequestQueue(stringRequest);
	}
	private void flushList() {
		Log.i("jyy", "刷新界面");

		investorAdapter.setList(investorList);
		investorAdapter.notifyDataSetChanged();
	}
}
