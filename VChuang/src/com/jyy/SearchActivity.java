package com.jyy;

import java.util.ArrayList;

import com.jyy.adapter.InvestorAdapter;
import com.jyy.adapter.ProjectAdapter;
import com.jyy.bean.ProjectBean;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity {

	private Context mContext;
	private ArrayList List;
	private ListView lv;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		//控制登录用户名图标大小
        EditText editText1 = (EditText) findViewById(R.id.et_search);
        Drawable drawable1 = getResources().getDrawable(R.drawable.search1);
        drawable1.setBounds(8, 0, 29, 25);//第一0是距左边距离，第二0是距上边距离，25分别是宽高
        editText1.setCompoundDrawables(drawable1, null, null, null);//只放左边
        
        //设置取消TextView的点击事件
        TextView tv_cancle = (TextView) findViewById(R.id.tv_cancel_search);
        tv_cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        lv =(ListView) findViewById(R.id.lv_search);
		
		
        //取出intent传来的值
        Intent intent = new Intent();
        String source = intent.getStringExtra("source");
        
        if(source != null){
        	if(source == "protect"){
        		//绑定项目的ProjectAdapter
        		lv.setAdapter(new ProjectAdapter(mContext, List));
        		/**
        		 * 初始化数据，通过dao里的方法获取 Project的List
        		 */
        	}
        	if(source == "investor"){
        		//绑定投资人的InvestorAdapter
        		lv.setAdapter(new InvestorAdapter(mContext, List));
        		/**
        		 *初始化数据，通过dao里的方法获取Investor的List 
        		 */
        	}
        }
		
		
        
	}
}
