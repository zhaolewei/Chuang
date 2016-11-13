package com.wzy;

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

public class InvestListViewAdapter extends BaseAdapter{
	
	private LayoutInflater lyf;
	private List<Invest> lists;
	private ImageLoader imageLoader;
	
	public InvestListViewAdapter(Context context,List<Invest> lists){
		lyf = LayoutInflater.from(context);
		this.lists = lists ;
		imageLoader = new ImageLoader(context);
	}
	
	
	class Item{
		public ImageView imageView;
		public TextView title;
		public TextView end_time;
		public TextView master;
		public TextView city;
		public TextView introduction;
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
        	convertView = lyf.inflate(R.layout.myinvest_item, null);
        	item = new Item();
        	item.imageView = (ImageView) convertView.findViewById(R.id.imageView);
        	item.title = (TextView) convertView.findViewById(R.id.title);
        	item.end_time = (TextView) convertView.findViewById(R.id.end_time);
        	item.master = (TextView) convertView.findViewById(R.id.master);
        	item.city = (TextView) convertView.findViewById(R.id.city);
        	item.introduction = (TextView) convertView.findViewById(R.id.introduction);
        	item.imageView = (ImageView) convertView.findViewById(R.id.image);
        	convertView.setTag(item);	
		}else {
			item = (Item) convertView.getTag();
		}
        
        item.title.setText(lists.get(position).getTitle());
        item.end_time.setText(lists.get(position).getEnd_time());
        item.master.setText(lists.get(position).getMaster());
        item.city.setText(lists.get(position).getCity());
        item.introduction.setText(lists.get(position).getIntroduction());
        
        
        String path = "http://172.31.1.25"+lists.get(position).getImgurl();
        
        Log.e("imaurl", path);
        item.imageView.setTag(path);
		imageLoader.LoadImage(path, item.imageView);

		return convertView;
        
        
	}
	
	


}
