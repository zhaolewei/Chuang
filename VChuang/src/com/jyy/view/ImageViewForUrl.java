package com.jyy.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
/**
 * 
 * @author Jyy
 *
 */
public class ImageViewForUrl extends ImageView {

	//△△△△△自定义控件都必须实现三个构造方法
	public ImageViewForUrl(Context context) {
		super(context);
	}
	public ImageViewForUrl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public ImageViewForUrl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			Bitmap bitmap = (Bitmap) msg.obj; 
			
			ImageViewForUrl.this.setImageBitmap(bitmap);
		};
	};
	
	public void setImageUrl(final String url_str){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				try{
					//判断url是否正确
					String new_path = (String) getTag();
					if(new_path != url_str){
						return ;
					}
					
					URL url = new URL(url_str);
					HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
					openConnection.setRequestMethod("GET");
					openConnection.setConnectTimeout(1000*10);
					int code = openConnection.getResponseCode();
					if(code == 200){
						InputStream inputStream = openConnection.getInputStream();
						
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						
						Message msg = Message.obtain();
						msg.obj = bitmap;
						handler.sendMessage(msg);	
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
