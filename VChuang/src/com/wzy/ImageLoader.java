package com.wzy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.threegroup.vchuang.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


public class ImageLoader {
	
	private Context context ;

	private Map<String,Bitmap> cacheMap = new HashMap<>();
	
	public ImageLoader(Context context){
		this.context = context ;
	}
	
	public void LoadImage(String path,ImageView imageView){
		Bitmap bitmap = null;
		//从一级缓存中加载图片
		bitmap = getImageFromFirstCache(path);
		//如果从一级缓存中加载到了资源
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
			return;
		}
		bitmap = getImageFromSecondCache(path);
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
			setImageToFirstCahce(path, bitmap);
			return;
		}
		getImageFromThirdCache(path,imageView);
	}
	
	
	
	public Bitmap getImageFromFirstCache(String path){
		return cacheMap.get(path);
	}
	public void setImageToFirstCahce(String path,Bitmap bitmap){
		
		
		cacheMap.put(path, bitmap);
	}


	
	public void setImageToSecondCache(String path,Bitmap bitmap){
		String files_path = context.getExternalFilesDir(null).getAbsolutePath();
		String file_name = path.substring(path.lastIndexOf("/")+1);
			
		try {
			String file_path = files_path+"/"+file_name;
			FileOutputStream fileOutputStream = new FileOutputStream(file_path);
			bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Toast.makeText(context, files_path, 1).show();
		
	}
	public Bitmap getImageFromSecondCache(String path){
		String files_path = context.getExternalFilesDir(null).getAbsolutePath();
		String file_name = path.substring(path.lastIndexOf("/")+1);
		String file_path = files_path+"/"+file_name;
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(file_path);
			return bitmap;

		} catch (Exception e) {
			return null;
		}

		
	}

	
	
	public void getImageFromThirdCache(final String path,final ImageView imageView){
		
		new AsyncTask<Void, Void, Bitmap>(){
			
			protected void onPreExecute() {
				
				imageView.setImageResource(R.drawable.ic_launcher);
				
			};
			
			@Override
			protected Bitmap doInBackground(Void... params) {
				
				String new_path = (String) imageView.getTag();
				Log.e("path", new_path);
				if(path != new_path){
					return null;
				}
				HttpClient httpClient = new DefaultHttpClient() ; 
				//http://172.31.1.25/VchuangServer/InvestSrver
				HttpGet get = new HttpGet(path);
				HttpParams httpParams = new BasicHttpParams();
				ConnManagerParams.setTimeout(httpParams, 5000);
				get.setParams(httpParams);
				try {
					
					HttpResponse response= httpClient.execute(get);
					if(response.getStatusLine().getStatusCode() == 200){
						
						InputStream in = response.getEntity().getContent();
						Bitmap bitmap = BitmapFactory.decodeStream(in);
						in.close();
						return bitmap;
					}
				} catch (ClientProtocolException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}finally{
					//httpClient
				}
				return  null;
			}
			@Override
			protected void onPostExecute(Bitmap result) {
				if(result != null){
					String new_path = (String) imageView.getTag();
					if(path != new_path){
						imageView.setImageBitmap(result);
					}
					setImageToFirstCahce(path, result);
					setImageToSecondCache(path, result);
				}else {
					//加载一张错误的图片或者提示错误之类的
				}
				super.onPostExecute(result);
			}
			
		}.execute();
	}
}
