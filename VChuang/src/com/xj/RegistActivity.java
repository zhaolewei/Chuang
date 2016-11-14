package com.xj;

import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.R;
import com.zlw.view.CircleImageView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistActivity extends Activity {

	private Spinner spinner02;
	private CircleImageView circle;
	private EditText et_regist_username;
	private EditText et_regist_password;
	private EditText et_regist_admitpwd;
	private EditText et_regist_age;
	private EditText et_regist_sex;
	private EditText et_regist_company;
	private EditText et_regist_phonenum;
	private EditText et_regist_email;
	private EditText et_regist_job;
	private EditText et_regist_city;
	private EditText et_regist_address;

	private MySqlite mysqlite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		// 初始化控件
		initView();
	}

	private void initView() {
		spinner02 = (Spinner) findViewById(R.id.spinner02);
		final List<String> list = new ArrayList<String>();
		list.add("投资");
		list.add("融资");
		// 将可选内容list与ArrayAdapter相连接
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将Adapter添加到Spinner
		spinner02.setAdapter(adapter);

	}

	// 选择上传的头像上传对话框
	public void bt_changeImage(View v) {
		circle = (CircleImageView) findViewById(R.id.photoimg);
		final String[] items = new String[] { "图片1", "图片2", "图片3", "图片4", "图片5" };
		new AlertDialog.Builder(this).setTitle("请选择图片")
				.setSingleChoiceItems(items, 1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(), items[which], 1)
								.show();
						// dialog.dismiss();
						if (which == 0) {
							circle.setImageResource(R.drawable.p1);
						} else if (which == 1) {
							circle.setImageResource(R.drawable.p2);
						} else if (which == 2) {
							circle.setImageResource(R.drawable.p3);
						} else if (which == 3) {
							circle.setImageResource(R.drawable.p4);
						} else if (which == 4) {
							circle.setImageResource(R.drawable.p5);
						}
					}
				}).setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setNegativeButton("取消", null)

				.show();
	}

	public void registButton(View v) {
		et_regist_username = (EditText) findViewById(R.id.et_regist_username);
		et_regist_password = (EditText) findViewById(R.id.et_regist_password);
		et_regist_admitpwd = (EditText) findViewById(R.id.et_regist_admitpwd);
		et_regist_age = (EditText) findViewById(R.id.et_regist_age);
		et_regist_sex = (EditText) findViewById(R.id.et_regist_sex);
		et_regist_company = (EditText) findViewById(R.id.et_regist_company);
		et_regist_phonenum = (EditText) findViewById(R.id.et_regist_phonenum);
		et_regist_email = (EditText) findViewById(R.id.et_regist_email);
		et_regist_job = (EditText) findViewById(R.id.et_regist_job);
		et_regist_city = (EditText) findViewById(R.id.et_regist_city);
		et_regist_address = (EditText) findViewById(R.id.et_regist_address);
		getto();
	}

	private void getto() {
		String name = et_regist_username.getText().toString();
		String pwd = et_regist_password.getText().toString();
		Log.i("password", pwd);
		String apwd = et_regist_admitpwd.getText().toString();
		Log.i("password1", apwd);
		int age = et_regist_age.getText().length();
		String sex = et_regist_sex.getText().toString();
		String type = "投资";
		String company = et_regist_company.getText().toString();
		String phonenum = et_regist_phonenum.getText().toString();
		String email = et_regist_email.getText().toString();
		String job = et_regist_job.getText().toString();
		String city = et_regist_city.getText().toString();
		String address = et_regist_address.getText().toString();

		// 校验输入的用户名和密码
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(getApplicationContext(), "输入的用户名或密码不能为空", 1).show();
		} else if (!pwd.equals(apwd)) {
			Toast.makeText(getApplicationContext(), "输入的确认密码不正确", 1).show();
		} else {

			mysqlite = new MySqlite(this);

			UserDao dao = new UserDao(mysqlite);
			dao.insert1User(new User(name, pwd, age, sex, type, company,
					phonenum, email, job, city, address));
			List<User> lists = dao.getAllUser();
			if (lists.size() == 0)
				Toast.makeText(getApplicationContext(), "........", 1).show();
			;
			for (User list : lists) {
				Toast.makeText(getApplicationContext(), list.toString(), 1)
						.show();
				Toast.makeText(getApplicationContext(), list.toString(), 1)
						.show();
			}
			Toast.makeText(getApplicationContext(), "注册成功", 1).show();
			Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		

	}

}
