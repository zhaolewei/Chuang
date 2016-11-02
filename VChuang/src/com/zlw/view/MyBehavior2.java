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
public class MyBehavior2 extends CoordinatorLayout.Behavior {

	public MyBehavior2(Context context, AttributeSet attrs) {
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
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
		if (dyConsumed > 0) {// 往下滑
			animatOut(child);

		} else if (dyConsumed < 0) {// 往上滑
			animatIn(child);
		}

		// 分发机智
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
	}

	private void animatIn(View child) {
		ViewCompat.animate(child).translationY(0).start();
		View parent = (View) child.getParent();
		View rv = parent.findViewById(R.id.rv);
		ViewCompat.animate(rv).translationY(child.getHeight()).start();

	}

	private void animatOut(View child) {
		ViewCompat.animate(child).translationY(-child.getHeight() * 2).start();
		View parent = (View) child.getParent();
		View rv = parent.findViewById(R.id.rv);
		ViewCompat.animate(rv).translationY(0).start();
	}
}
