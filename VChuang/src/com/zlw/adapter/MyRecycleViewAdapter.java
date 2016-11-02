package com.zlw.adapter;

import java.util.List;

import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zlw on 2016/10/31 0031.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter {

	private List<String> list;
	private Context context;

	public MyRecycleViewAdapter(List<String> list) {
		this.list = list;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final MyViewHolder mHolder = (MyViewHolder) holder;

		String str = list.get(position);

		((View) mHolder.tv_zan.getParent()).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int isFocus = (int) mHolder.tv_zan.getTag();
				Log.i("zlw", "" + isFocus);
				if (isFocus == 1) {
					mHolder.iv_zan.setImageResource(R.drawable.feed_zan_pressed);
					mHolder.tv_zan.setTag(0);
				} else {
					mHolder.iv_zan.setImageResource(R.drawable.feed_zan_normal);
					mHolder.tv_zan.setTag(1);
				}

			}
		});

		((View) mHolder.tv_conversation.getParent()).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int isFocus = (int) mHolder.tv_conversation.getTag();
				Log.i("zlw", "" + isFocus);
				if (isFocus == 1) {
					mHolder.iv_conversation.setImageResource(R.drawable.tabs_conversation_pressed);
					mHolder.tv_conversation.setTag(0);
				} else {
					mHolder.iv_conversation.setImageResource(R.drawable.tabs_conversation_normal);
					mHolder.tv_conversation.setTag(1);
				}

			}
		});
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		public com.zlw.view.CircleImageView user_photo;
		public TextView user_name;
		public TextView user_info;
		public TextView content;

		public ImageView img1;
		public ImageView img2;
		public ImageView img3;

		public ImageView iv_zan;
		public ImageView iv_conversation;
		public ImageView iv_share;

		public TextView tv_zan;
		public TextView tv_conversation;

		public MyViewHolder(View itemView) {
			super(itemView);
			tv_zan = (TextView) itemView.findViewById(R.id.item_zan_text);
			iv_zan = (ImageView) itemView.findViewById(R.id.item_zan_img);
			tv_zan.setTag(1);

			tv_conversation = (TextView) itemView.findViewById(R.id.item_conversation_text);
			tv_conversation.setTag(1);
			iv_conversation = (ImageView) itemView.findViewById(R.id.item_conversation_img);
		}
	}

	@Override
	public int getItemCount() {
		return list.size();
	}
}
