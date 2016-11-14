package com.xj;

import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.MainActivity;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

//登录页面
public class LoginActivity extends Activity {
	private EditText et_login_name;
	private EditText et_login_password;
	private Button bt_login_regist;
	private Button bt_login_login;
	private TextView tv_login_type;
	private Spinner spinner01;

	private MySqlite mysqlite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 初始化控件
		initView();

	}

	private void initView() {
		tv_login_type = (TextView) findViewById(R.id.tv_regist_type);
		spinner01 = (Spinner) findViewById(R.id.spinner01);
		final List<String> list = new ArrayList<String>();
		list.add("用户");
		list.add("管理员");
		// 将可选内容list与ArrayAdapter相连接
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将Adapter添加到Spinner
		spinner01.setAdapter(adapter);
	}

	public void loginButton(View v) {
		et_login_name = (EditText) findViewById(R.id.et_login_username);
		et_login_password = (EditText) findViewById(R.id.et_login_password);

		String loginName = et_login_name.getText().toString();
		String loginPassword = et_login_password.getText().toString();

		if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPassword)) {
			Toast.makeText(LoginActivity.this, "输入的用户名或密码不能为空", 1).show();
			return;
		} else {

			mysqlite = new MySqlite(this);
			UserDao dao = new UserDao(mysqlite);
			if (dao.getUserByUserNameAndPassWord(loginName, loginPassword) != null) {
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(getApplicationContext(), "输入的用户名或密码不正确", 1).show();
			}
		}
	}

	public void registButton(View v) {
		Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
		startActivity(intent);
		finish();
	}
}
