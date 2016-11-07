package com.threegroup.vchuang;

import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.ourfragment.DiscoverFragment;
import com.threegroup.vchuang.ourfragment.EventCenterFragment;
import com.threegroup.vchuang.ourfragment.MeFragment;
import com.threegroup.vchuang.ourfragment.MessageFragment;
import com.threegroup.vchuang.ourfragment.VQuanFragment;
import com.zlw.mymodel.adapter.MainViewPagerAdapter;
import com.zlw.mymodel.ui.fragment.ListFragment;
import com.zlw.mymodel.ui.fragment.TestFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

	// 控件
	private TabLayout tabLayout;
	private ViewPager vp;

	// TAB控件集
	private List<ImageView> ivs; // Tab中的Imageview集（用于修改透明度达到变色效果）
	private List<TextView> tvs; // Tab中的TextView集

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initData();
		bindEvent();
	}

	protected void initData() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new VQuanFragment());
		fragments.add(new DiscoverFragment());
		fragments.add(new EventCenterFragment());
		fragments.add(new MessageFragment());
		fragments.add(new MeFragment());
		vp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));

		tabLayout.setupWithViewPager(vp);

		String[] tabData = new String[] { "V圈", "发现", "事务中心", "消息", "我" };

		Log.i("zlw", "123initData===========");
		// 设置TabLayout中的Tab样式
		ivs = new ArrayList<ImageView>();
		tvs = new ArrayList<TextView>();
		for (int i = 0; i < fragments.size(); i++) {
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			tab.setCustomView(R.layout.item_main_tab); // 设置Tab item

			// Tab的Text
			TextView tv = (TextView) tab.getCustomView().findViewById(R.id.item_main_tab_text);
			tv.setText(tabData[i]);
			tv.setTextSize(10f);
			tv.setTextColor(Color.RED);
			tvs.add(tv);

			// 提取ImageView
			ImageView img = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
			ivs.add(img);
			if (i != 0) {
				img.setImageAlpha(0); // 设置为白色
				tv.setTextColor(Color.BLACK);
			}
		}
		// tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
		tabLayout.setTabMode(TabLayout.MODE_FIXED);// tab均分,适合少的tab
	}

	/**
	 * 绑定事件
	 */
	protected void bindEvent() {
		vp.addOnPageChangeListener(this);
	}

	protected void initView() {
		tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
		vp = (ViewPager) findViewById(R.id.main_vp);
	}

	// ViewPager.OnPageChangeListener
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		ImageView imgView = ivs.get(position);
		int offset = (int) (positionOffset * 255);

		// 左右两图渐变处理 255：白 0：红

		if (position < ivs.size() - 1) {
			ImageView imgView2 = ivs.get(position + 1);
			imgView.setImageAlpha(255 - offset);
			imgView2.setImageAlpha(offset);
		}
	}

	@Override
	public void onPageSelected(int position) {

		Log.i("zlw", "position:" + position);
		selectTabImage(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	/**
	 * 选中
	 * 
	 * @param position
	 */
	private void selectTabImage(int position) {
		ImageView img = null;
		TextView tv = null;
		for (int i = 0; i < ivs.size(); i++) {
			img = ivs.get(i);
			tv = tvs.get(i);
			if (i == position) {
				img.setImageAlpha(255);
				tv.setTextColor(Color.RED);
			} else {
				img.setImageAlpha(0);
				tv.setTextColor(Color.BLACK);
			}
		}
	}

}