package com.xj;


import com.threegroup.vchuang.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	}
	
	public void renzheng(View v){
		View view = null;
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		
		view = layoutInflater.inflate(R.layout.dialog_rz_view,null);
		
		final EditText Email_rz = (EditText) view.findViewById(R.id.email_rz);
		final EditText Phone_rz = (EditText) view.findViewById(R.id.phone_rz);
	    
		Button button1 = (Button) view.findViewById(R.id.button1);
		Button button2 = (Button) view.findViewById(R.id.button2);
		final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请输入认证信息")
		.setView(view)
		.show();
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String email_rz = Email_rz.getText().toString();
				String phone_rz = Phone_rz.getText().toString();
				
				dialog.dismiss();
			}

			
		});
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
	}
	
	public void bianji(View v){
		startActivity(new Intent(SettingActivity.this,BianjiActivity.class));
	}
	
    public void bangding(View v){
        View view = null;
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		
		view = layoutInflater.inflate(R.layout.dialog_bangding_view,null);
		
		final EditText Email_bd = (EditText) view.findViewById(R.id.email_bd);
		final EditText Phone_bd = (EditText) view.findViewById(R.id.phone_bd);
	    
		Button button3 = (Button) view.findViewById(R.id.button3);
		Button button4 = (Button) view.findViewById(R.id.button4);
		final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请输入认证信息")
		.setView(view)
		.show();
		
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String email_bd = Email_bd.getText().toString();
				String phone_bd = Phone_bd.getText().toString();
				
				dialog.dismiss();
			}
		
		});
		button4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
	}
}

