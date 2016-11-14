package com.xj;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.threegroup.vchuang.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AdapterShouCang extends BaseAdapter{
	
	private ImageLoader imageLoader;
	private LayoutInflater lyf;
	private List<MyShouCang> lists;
	
	public AdapterShouCang(Context context,List<MyShouCang> lists){
		lyf = LayoutInflater.from(context);
		this.lists = lists ;
		imageLoader = new ImageLoader(context);
		
		Log.i("lists", lists.size()+"");

	}
	

	
	class Item {
		
		public ImageView pic_shoucang;
	
	}
	
	

	@Override
	public int getCount() {
		
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = null;
		if(convertView == null){
			convertView = lyf.inflate(R.layout.listview_scitem, null);
			item = new Item();
			item.pic_shoucang =  (ImageView) convertView.findViewById(R.id.iv_scitem);
			convertView.setTag(item);
		}
		else {
			item = (Item) convertView.getTag();
		}
		String path = "http://10.134.55.186"+lists.get(position).getPicPath();
		item.pic_shoucang.setTag(path);
		
		Log.i("path", path);
		
		imageLoader.LoadImage(path, item.pic_shoucang);
		return convertView;
	}
	
	
}
