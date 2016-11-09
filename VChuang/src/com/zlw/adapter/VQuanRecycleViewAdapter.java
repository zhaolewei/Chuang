package com.zlw.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyy.bean.VQuanBean;
import com.threegroup.vchuang.R;
import com.zlw.mymodel.base.MyApplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zlw on 2016/10/31 0031.
 */
public class VQuanRecycleViewAdapter extends RecyclerView.Adapter {

	private List<VQuanBean> list;
	private Context context;

	private OnItemClickListener clickListener;

	public void setList(List<VQuanBean> list) {
		this.list = list;
	}

	public void setClickListener(OnItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public VQuanRecycleViewAdapter(List<VQuanBean> list) {
		this.list = list;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
		MyViewHolder mHolder = new MyViewHolder(view);

		return mHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final MyViewHolder mHolder = (MyViewHolder) holder;
		VQuanBean data = list.get(position);
		// 填充数据
		mHolder.tv_zan.setText("" + data.getZan_count());
		mHolder.tv_conversation.setText("" + data.getComment_count());

		Log.i("zlw", "data.getContent():" + data.getContent());
		mHolder.content.setText("  " + data.getContent());
		// 加载图片
		String imgs = data.getImgs_url();
		if (!TextUtils.isEmpty(imgs)) {
			// imgs =
			// "http://photocdn.sohu.com/20150626/mp20309286_1435316835474_4.jpeg;http://img03.3dmgame.com/uploads/allimg/150626/296_150626171747_1_lit.jpg";
			String images[] = imgs.split(";");
			switch (images.length) {
			case 3:
				mHolder.img3.setTag(R.id.item_img3, images[2]);
				mHolder.img2.setTag(R.id.item_img2, images[1]);
				mHolder.img1.setTag(R.id.item_img1, images[0]);
				loadImage(3, images, mHolder);
				loadImage(2, images, mHolder);
				loadImage(1, images, mHolder);
				break;
			case 2:
				mHolder.img2.setTag(R.id.item_img2, images[1]);
				mHolder.img1.setTag(R.id.item_img1, images[0]);
				mHolder.img3.setVisibility(View.GONE);
				loadImage(2, images, mHolder);
				loadImage(1, images, mHolder);
				break;
			case 1:
				mHolder.img0.setTag(R.id.item_img0, images[0]);
				mHolder.img1.setVisibility(View.GONE);
				mHolder.img3.setVisibility(View.GONE);
				mHolder.img2.setVisibility(View.GONE);
				loadImage(0, images, mHolder);
				break;
			}

		} else {
			mHolder.img3.setVisibility(View.GONE);
			mHolder.img2.setVisibility(View.GONE);
			mHolder.img1.setVisibility(View.GONE);
			mHolder.img0.setVisibility(View.GONE);
		}

		// 赞
		int isFouse = (int) mHolder.tv_zan.getTag();
		// if (isFouse == 1) {
		// mHolder.iv_zan.setImageResource(R.drawable.feed_zan_normal);
		// } else {
		// mHolder.iv_zan.setImageResource(R.drawable.feed_zan_pressed);
		// }

		// 绑定事件
		bindEvent(mHolder, position);

	}

	private void loadImage(int index, String[] images, MyViewHolder mHolder) {

		switch (index) {
		case 0:
			if (images[0].equals((String) mHolder.img0.getTag(R.id.item_img0))) {
				Glide.with(MyApplication.getContext()).load(images[0]).diskCacheStrategy(DiskCacheStrategy.ALL)
						.placeholder(R.drawable.icon_default).into(mHolder.img0);
				mHolder.img0.setVisibility(View.VISIBLE);
			} else {
				mHolder.img0.setVisibility(View.GONE);
			}
			break;
		case 1:
			if (images[0].equals((String) mHolder.img1.getTag(R.id.item_img1))) {
				Glide.with(MyApplication.getContext()).load(images[0]).diskCacheStrategy(DiskCacheStrategy.ALL)
						.placeholder(R.drawable.icon_default).into(mHolder.img1);
				mHolder.img1.setVisibility(View.VISIBLE);
			} else {
				mHolder.img1.setVisibility(View.GONE);
			}
			break;
		case 2:

			if (images[1].equals((String) mHolder.img2.getTag(R.id.item_img2))) {
				Glide.with(MyApplication.getContext()).load(images[1]).diskCacheStrategy(DiskCacheStrategy.ALL)
						.placeholder(R.drawable.icon_default).into(mHolder.img2);
				mHolder.img2.setVisibility(View.VISIBLE);
			} else {
				mHolder.img2.setVisibility(View.GONE);
			}
			break;
		case 3:

			if (images[2].equals((String) mHolder.img3.getTag(R.id.item_img3))) {
				Glide.with(MyApplication.getContext()).load(images[2]).diskCacheStrategy(DiskCacheStrategy.ALL)
						.placeholder(R.drawable.icon_default).into(mHolder.img3);
				mHolder.img3.setVisibility(View.VISIBLE);
			} else {
				mHolder.img3.setVisibility(View.GONE);
			}
			break;

		default:
			break;
		}

	}

	/**
	 * 绑定事件监听
	 * 
	 * @param mHolder
	 * @param position
	 */
	private void bindEvent(final MyViewHolder mHolder, final int position) {

		mHolder.view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (clickListener != null) {
					clickListener.onItemClick(v, position);
				}
			}
		});

		((View) mHolder.tv_zan.getParent()).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int isFocus = (int) mHolder.tv_zan.getTag(); // 1:正常； 0:已赞
				Log.i("zlw", "" + isFocus);

				// TODO:iv_zan 数量+1;
				int zan_count = list.get(position).getZan_count();
				if (isFocus == 1) {
					mHolder.iv_zan.setImageResource(R.drawable.feed_zan_pressed);
					mHolder.tv_zan.setTag(0);
					list.get(position).setZan_count(zan_count + 1);
					mHolder.tv_zan.setText("" + (1 + Integer.parseInt(mHolder.tv_zan.getText().toString())));

				} else {
					mHolder.iv_zan.setImageResource(R.drawable.feed_zan_normal);
					mHolder.tv_zan.setText("" + (-1 + Integer.parseInt(mHolder.tv_zan.getText().toString())));
					mHolder.tv_zan.setTag(1);
					list.get(position).setZan_count(zan_count - 1);
				}
			}
		});

		// ((View) mHolder.tv_conversation.getParent()).setOnClickListener(new
		// OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// int isFocus = (int) mHolder.tv_conversation.getTag();
		// Log.i("zlw", "" + isFocus);
		// if (isFocus == 1) {
		// mHolder.iv_conversation.setImageResource(R.drawable.tabs_conversation_pressed);
		// mHolder.tv_conversation.setTag(0);
		// } else {
		// mHolder.iv_conversation.setImageResource(R.drawable.tabs_conversation_normal);
		// mHolder.tv_conversation.setTag(1);
		// }
		// }
		// });
		mHolder.iv_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showShare(position);
			}
		});

	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		public View view;
		public com.zlw.view.CircleImageView user_photo;
		public TextView user_name;
		public TextView user_info;
		public TextView content;

		public ImageView img0;
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
			view = itemView;

			iv_share = (ImageView) itemView.findViewById(R.id.item_share_img);

			content = (TextView) itemView.findViewById(R.id.item_content);
			img1 = (ImageView) itemView.findViewById(R.id.item_img1);
			img2 = (ImageView) itemView.findViewById(R.id.item_img2);
			img3 = (ImageView) itemView.findViewById(R.id.item_img3);
			img0 = (ImageView) itemView.findViewById(R.id.item_img0);

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

	public interface OnItemClickListener {
		public void onItemClick(View v, int position);
	}

	/**
	 * 显示分享功能
	 */
	private void showShare(int position) {
		ShareSDK.initSDK(MyApplication.getContext());
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("【V创】分享：");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://139.129.22.46:8080/vchuang_service/GetActivityServlet");
		// text是分享文本，所有平台都需要这个字段
		oks.setText(list.get(position).getContent());
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://139.129.22.46:8080/vchuang_service/GetActivityServlet");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment(" ");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("V创");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://139.129.22.46:8080/vchuang_service/GetActivityServlet");

		// 启动分享GUI
		oks.show(MyApplication.getContext());
	}

}
