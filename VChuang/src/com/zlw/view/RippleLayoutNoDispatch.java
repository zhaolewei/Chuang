package com.zlw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不对子空间进行传递
 * 
 * @author zlw
 *
 */
public class RippleLayoutNoDispatch extends RippleLayout {

	public RippleLayoutNoDispatch(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}

}
