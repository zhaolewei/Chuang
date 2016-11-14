package com.xj;


import com.threegroup.vchuang.MainActivity;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
//欢迎界面
public class SplashActivity extends Activity {
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			//如果当前activity已经退出，那么不处理handler中的消息
			if(isFinishing()){
				return;
			}
			
			//判断进入主页面还是进入登录页面
			toMainOrLogin();
		}
	};

		private void toMainOrLogin() {			
			new Thread(){
				public void run(){
					//用环形服务器判断当前账号是否已经登录过
					if(false){//登录过
						
						//获取到当前登录用户的信息
						
						//跳转到主页面
						Intent intent = new Intent(SplashActivity.this,MainActivity.class);
						startActivity(intent);
					}else{//没登录过
						//跳转到登录页面
						Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
						startActivity(intent);
					}
					
					//结束当前页面
					finish();					
				}
			}.start();
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		//发送2秒钟的延迟消息
		handler.sendMessageDelayed(Message.obtain(), 2000);
	}
	
	protected void onDestroy(){
		super.onDestroy();
		//销毁消息
		handler.removeCallbacksAndMessages(null);
	}
	
}
