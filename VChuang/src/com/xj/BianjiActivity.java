package com.xj;


import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class BianjiActivity extends Activity {

	private Spinner spinner03;
	private EditText et_c_username;
	private EditText et_c_password;
	private EditText et_c_admitpwd;
	private EditText et_c_age;
	private EditText et_c_sex;
	private EditText et_c_company;
	private EditText et_c_phonenum;
	private EditText et_c_email;
	private EditText et_c_job;
	private EditText et_c_city;
	private EditText et_c_address;
	
	private MySqlite mysqlite;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bianji);
		
		initView();	
	}
	private void initView() {
		spinner03 = (Spinner) findViewById(R.id.spinner03);
		final List<String> list = new ArrayList<String>();
		list.add("投资");
		list.add("融资");
		// 将可选内容list与ArrayAdapter相连接
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将Adapter添加到Spinner
		spinner03.setAdapter(adapter);		
	}
	
	public void cButton(View v){
		et_c_username = (EditText) findViewById(R.id.et_c_username);
		et_c_password = (EditText) findViewById(R.id.et_c_password);
		et_c_admitpwd = (EditText) findViewById(R.id.et_c_admitpwd);
		et_c_age = (EditText) findViewById(R.id.et_c_age);
		et_c_sex = (EditText) findViewById(R.id.et_c_sex);
		et_c_company = (EditText) findViewById(R.id.et_c_company);
		et_c_phonenum = (EditText) findViewById(R.id.et_c_phonenum);
		et_c_email = (EditText) findViewById(R.id.et_c_email);
		et_c_job = (EditText) findViewById(R.id.et_c_job);
		et_c_city = (EditText) findViewById(R.id.et_c_city);
		et_c_address = (EditText) findViewById(R.id.et_c_address);
		
		changeInfo();
	}
	private void changeInfo() {
		String nameC = et_c_username.getText().toString();
		String pwdC = et_c_password.getText().toString();
		Log.i("password", pwdC);
		String apwdC = et_c_admitpwd.getText().toString();
		Log.i("password1", apwdC);
		int ageC = et_c_age.getText().length();
		String sexC = et_c_sex.getText().toString();
		String typeC = "投资";
		String companyC = et_c_company.getText().toString();
		String phonenumC = et_c_phonenum.getText().toString();
		String emailC = et_c_email.getText().toString();
		String jobC = et_c_job.getText().toString();
		String cityC = et_c_city.getText().toString();
		String addressC = et_c_address.getText().toString();

		// 校验输入的用户名和密码
		if (TextUtils.isEmpty(nameC) || TextUtils.isEmpty(pwdC)) {
			Toast.makeText(getApplicationContext(), "输入的用户名或密码不能为空", 1).show();
		} else if (!pwdC.equals(apwdC)) {
			Toast.makeText(getApplicationContext(), "输入的确认密码不正确", 1).show();
		}else{

		mysqlite = new MySqlite(this);
		UserDao dao = new UserDao(mysqlite);
		dao.update1User(new User(nameC, pwdC, ageC, sexC, typeC, companyC, phonenumC, emailC, jobC, cityC, addressC));
		Toast.makeText(getApplicationContext(), "修改成功", 1).show();
	}
	}
	public void dButton(View v){
		
		et_c_username = (EditText) findViewById(R.id.et_c_username);
		et_c_password = (EditText) findViewById(R.id.et_c_password);
		et_c_admitpwd = (EditText) findViewById(R.id.et_c_admitpwd);
		String nameC = et_c_username.getText().toString();
		String pwdC = et_c_password.getText().toString();
		String apwdC = et_c_admitpwd.getText().toString();
		if (TextUtils.isEmpty(nameC) || TextUtils.isEmpty(pwdC)) {
			Toast.makeText(getApplicationContext(), "输入的用户名或密码不能为空", 1).show();
		} else if (!pwdC.equals(apwdC)) {
			Toast.makeText(getApplicationContext(), "输入的确认密码不正确", 1).show();
		}else{
			mysqlite = new MySqlite(this);
			UserDao dao = new UserDao(mysqlite);
			dao.deleteUser(new User(nameC));
			Toast.makeText(getApplicationContext(), "删除成功", 1).show();
			Intent intent = new Intent(BianjiActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
	}
}
