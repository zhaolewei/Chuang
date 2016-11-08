package com.zlw.view;

import com.threegroup.vchuang.R;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zlw on 2016/10/31 0031.
 */
public class MyBehavior3 extends CoordinatorLayout.Behavior {

	public MyBehavior3(Context context, AttributeSet attrs) {
		super();
	}

	/**
	 * 观察RecycleView滑动时回调的方法
	 *
	 * @param coordinatorLayout
	 *            父类
	 * @param child
	 * @param directTargetChild
	 * @param target
	 * @param nestedScrollAxes
	 * @return
	 */
	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild,
			View target, int nestedScrollAxes) {

		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
				|| super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
	}

	@Override
	public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final View child, final View target,
			final int dxConsumed, final int dyConsumed, final int dxUnconsumed, final int dyUnconsumed) {
		if (dyConsumed > 0) {// LV往下滑隐藏
			animatOut(child, dyConsumed);
		} else if (dyConsumed < 0) {// 往上滑
			animatIn(child, -dyConsumed);
		}

		if (dyConsumed > 20) {

		}
		// 分发机智
	}

	private void animatIn(View child, int dy) {
		ViewCompat.animate(child).translationY(0).start();

		View parent = (View) child.getParent();
		View rv = parent.findViewById(R.id.rv);
		ViewCompat.animate(rv).translationY(child.getHeight()).start();
		tabAnimat(parent, 0);
	}

	private void animatOut(View child, int dy) {
		ViewCompat.animate(child).translationY(-child.getHeight() * 2).start();
		// ViewCompat.animate(child).alpha(100 - dy).start(); // 消失
		View parent = (View) child.getParent();
		View rv = parent.findViewById(R.id.rv);
		ViewCompat.animate(rv).translationY(0).start();

		tabAnimat(parent, child.getHeight());
	}

	private void tabAnimat(View v, int dy) {
		View root = (View) v.getParent();
		View main_tabLayout = root.findViewById(R.id.main_tabLayout);
		while (main_tabLayout == null) {
			root = (View) root.getParent();
			main_tabLayout = root.findViewById(R.id.main_tabLayout);
		}
		ViewCompat.animate(main_tabLayout).translationY(dy).start();
	}
}
