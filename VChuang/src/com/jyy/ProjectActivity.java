package com.jyy;

import java.util.ArrayList;

import com.jyy.adapter.ProjectAdapter;
import com.jyy.bean.ProjectBean;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	private ArrayList<ProjectBean> projectList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);

		mContext = this;
		projectList = new ArrayList<ProjectBean>();
		
		ListView lv =(ListView) findViewById(R.id.lv_project);
		lv.setAdapter(new ProjectAdapter(mContext, projectList));
		
		//给返回按钮设置点击事件
				ImageButton imgbtn = (ImageButton) findViewById(R.id.imgbtn_back_project);
				imgbtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

						finish();
					}
				});
		
		//给 搜索 TextView设置点击事件
				TextView tv_search = (TextView) findViewById(R.id.tv_search_project);
				tv_search.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ProjectActivity.this, SearchActivity.class);
						intent.putExtra("source", "project");
						startActivity(intent);
					}
				});
				
		//初始化projectList数据
		for (int i = 0; i < 10; i++) {
			ProjectBean pb = new ProjectBean(i, "童漫秀", "儿童动漫微电影第一平台", "北京", "6", R.drawable.d);
			projectList.add(pb);
		}
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
			}
		});
		

	}
}
