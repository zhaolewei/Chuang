package com.xj;

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
import android.widget.ListView;

public class MymsgActivity extends Activity {
 
	private ListView listView_msg;

	private List<MyInfo> lists;

	private ListViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mymsg);
		
		listView_msg = (ListView) findViewById(R.id.listview_msg);

		new ToServer().execute("http://localhost:8080/Server/AndroidServlet");

	}
	class ToServer extends AsyncTask<String, Void, String>{

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params) {
			HttpClient httpClient = new DefaultHttpClient() ; 
			HttpGet get = new HttpGet(params[0]);
			HttpParams httpParams = new BasicHttpParams();
			ConnManagerParams.setTimeout(httpParams, 5000);
			get.setParams(httpParams);
			try {
				HttpResponse response= httpClient.execute(get);
				Log.e("connect", "code:"+response.getStatusLine().getStatusCode());
				if(response.getStatusLine().getStatusCode() == 200){
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
				lists = gson.fromJson(result, new TypeToken<List<MyInfo>>(){}.getType());
				Log.i("kh", lists.toString());
				
				adapter = new ListViewAdapter(MymsgActivity.this, lists);
				listView_msg.setAdapter(adapter);
				
			}
			super.onPostExecute(result);
		}
		
	}
}
