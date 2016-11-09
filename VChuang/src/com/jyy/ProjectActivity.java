package com.jyy;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;
import com.jyy.adapter.ProjectAdapter;
import com.jyy.bean.ActivityBean;
import com.jyy.bean.DataPackage;
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
import android.widget.TextView;
import android.widget.Toast;

public class ProjectActivity extends Activity {

	private Context mContext;
	private List<ProjectBean> projectList;
	private ProjectAdapter projectAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);

		mContext = this;
		projectList = new ArrayList<ProjectBean>();

		ListView lv = (ListView) findViewById(R.id.lv_project);
		projectAdapter = new ProjectAdapter(mContext, projectList);
		lv.setAdapter(projectAdapter);

		// 给返回按钮设置点击事件
		ImageButton imgbtn = (ImageButton) findViewById(R.id.imgbtn_back_project);
		imgbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});

		// 给 搜索 TextView设置点击事件
		TextView tv_search = (TextView) findViewById(R.id.tv_search_project);
		tv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProjectActivity.this, SearchActivity.class);
				intent.putExtra("source", "project");
				startActivity(intent);
			}
		});

		// 初始化projectList数据
		getData();
		/*for (int i = 0; i < 10; i++) {
			ProjectBean pb = new ProjectBean(i, "童漫秀", "儿童动漫微电影第一平台", "北京", 6, "", "");
			projectList.add(pb);
		}*/

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void getData() {
		projectList = new ArrayList<ProjectBean>();
		StringRequest stringRequest = new StringRequest(URLConfig.PATH + URLConfig.URL_GetProject,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						DataPackage<ProjectBean> data = JsonTools.fromJson(response,
								new TypeToken<DataPackage<ProjectBean>>() {
								}.getType());
						List<ProjectBean> list = data.datas; // 获取到的数据
						projectList = list;
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

		projectAdapter.setList(projectList);
		projectAdapter.notifyDataSetChanged();
	}
}
