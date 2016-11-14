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
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;

	private List<MyInfo> lists;

	public ListViewAdapter(Context context, List<MyInfo> lists) {
		layoutInflater = LayoutInflater.from(context);
		this.lists = lists;
	}

	class Item {
		public TextView tv_getusername;
		public TextView tv_getage;
		public TextView tv_getsex;
		public TextView tv_gettype;
		public TextView tv_getcompany;
		public TextView tv_getphonenum;
		public TextView tv_getemail;
		public TextView tv_getjob;
		public TextView tv_getcity;
		public TextView tv_getaddress;
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
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.listview_msgitem,null);
			item = new Item();
			item.tv_getusername = (TextView) convertView.findViewById(R.id.tv_getusername);
			item.tv_getage = (TextView) convertView.findViewById(R.id.tv_getage);
			item.tv_getsex = (TextView) convertView.findViewById(R.id.tv_getsex);
			item.tv_gettype = (TextView) convertView.findViewById(R.id.tv_gettype);
			item.tv_getcompany = (TextView) convertView.findViewById(R.id.tv_getcompany);
			item.tv_getphonenum = (TextView) convertView.findViewById(R.id.tv_getphonenum);
			item.tv_getemail = (TextView) convertView.findViewById(R.id.tv_getemail);
			item.tv_getjob = (TextView) convertView.findViewById(R.id.tv_getjob);
			item.tv_getcity = (TextView) convertView.findViewById(R.id.tv_getcity);
			item.tv_getaddress = (TextView) convertView.findViewById(R.id.tv_getaddress);
			convertView.setTag(item);
		}else {
			item = (Item) convertView.getTag();
		}
		
		item.tv_getusername.setText(lists.get(position).getUsername());
		item.tv_getage.setText(String.valueOf(lists.get(position).getAge()));
		item.tv_getsex.setText(lists.get(position).getSex());
		item.tv_gettype.setText(lists.get(position).getType());
		item.tv_getcompany.setText(lists.get(position).getCompany());
		item.tv_getphonenum.setText(lists.get(position).getPhonenum());
		item.tv_getemail.setText(lists.get(position).getEmail());
		item.tv_getjob.setText(lists.get(position).getJob());
		item.tv_getcity.setText(lists.get(position).getCity());
		item.tv_getaddress.setText(lists.get(position).getAddress());
		return convertView;
	}		
}
