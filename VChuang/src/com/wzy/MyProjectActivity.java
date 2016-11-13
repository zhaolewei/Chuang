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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MyProjectActivity extends Activity {
	
	private ListView myprojectlistView;
	
	private MySQLite mysqlite;
	
	private ImageView nothing;
	
	private ProjectListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_project);
		
		nothing = (ImageView) findViewById(R.id.nothing);
		
		SQlInit();
		
		
	}
	
	
	public void SQlInit(){
		 mysqlite = new MySQLite(this);	
		
		ProjectDao dao = new ProjectDao(mysqlite);

		List<Project> lists = dao.getAllProject();
		
		
		if(lists.size()==0){
			nothing.setVisibility(View.VISIBLE);
		}else{

			Log.i("listssize", lists.size()+"");
			myprojectlistView = (ListView) findViewById(R.id.myprojectlistView);
			adapter = new ProjectListViewAdapter(MyProjectActivity.this,lists);
			myprojectlistView.setAdapter(adapter);
		}
		

	}
	
	
	
	public void createProject(View v){
		Intent intent = new Intent(MyProjectActivity.this,CreateProjectActivity.class);
		startActivity(intent);
	}
	
	
	
	

	
	
}
