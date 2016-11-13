package com.wzy;

import java.io.IOException;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.threegroup.vchuang.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MyInvestActivity extends Activity {
	
	private ListView myinvestlistview;
    private List<Invest> lists;
	private InvestListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_invest);
		
		myinvestlistview = (ListView) findViewById(R.id.myinvestlistview);

		new ToServer().execute("http://172.31.1.25/VchuangServer/InvestServer");
	}
	
	
	
	class ToServer extends AsyncTask<String, Void, String>{

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}
		  
		
		@Override
		protected String doInBackground(String... params) {
			HttpClient httpClient = new DefaultHttpClient() ; 
			//http://172.31.1.25/VchuangServer/InvestServer
			HttpGet get = new HttpGet(params[0]);
			HttpParams httpParams = new BasicHttpParams();			
			ConnManagerParams.setTimeout(httpParams, 5000);
			get.setParams(httpParams);
			try {
				Log.e("print", "zhixingjieguo");
				HttpResponse response= httpClient.execute(get);
				if(response.getStatusLine().getStatusCode() == 200){
					Log.e("error", response.getStatusLine().getStatusCode()+"");
					String jsonString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					return jsonString;
				}
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}finally{
				//httpClient.
			}
			return  null;
		}
		@Override
		protected void onPostExecute(String result) {
			
			if(result != null){
				Gson gson = new Gson();
				lists = gson.fromJson(result, new TypeToken<List<Invest>>(){}.getType());
				adapter = new InvestListViewAdapter(MyInvestActivity.this, lists);
				myinvestlistview.setAdapter(adapter);
			}
			
			
			
			super.onPostExecute(result);
		}
		
	}
}
