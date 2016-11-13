package com.wzy;

import java.util.List;

import com.threegroup.vchuang.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectListViewAdapter extends BaseAdapter{
	
	private LayoutInflater lyf;
	private List<Project> lists;
	private ImageLoader imageLoader;
	
	public ProjectListViewAdapter(Context context,List<Project> lists){
		lyf = LayoutInflater.from(context);
		this.lists = lists ;
		imageLoader = new ImageLoader(context);
	}
	
	class Item{
		
		public ImageView imageview;
		public TextView name;
		public TextView introduction;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	

        Item item = null;
        if(convertView == null){
        	convertView = lyf.inflate(R.layout.myproject_item, null);
        	item = new Item();
        	item.imageview = (ImageView) convertView.findViewById(R.id.imageView);
        	item.name = (TextView) convertView.findViewById(R.id.name);
        	item.introduction = (TextView) convertView.findViewById(R.id.introduction);
        	convertView.setTag(item);
        }else{
        	item = (Item) convertView.getTag();
        }
        
        item.name.setText(lists.get(position).getName());
        item.introduction.setText(lists.get(position).getIntroduction());
        byte[] imagebyte = lists.get(position).getPicPath();
        Bitmap bmp = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
        item.imageview.setImageBitmap(bmp);
        
		return convertView;
	}
	
	

}
