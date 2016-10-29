package com.zlw.mymodel.ui.fragment;

import com.threegroup.vchuang.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 123 on 2016/6/28 0028.
 */
public class TestFragment extends Fragment {
	private String text;

	public TestFragment(String text) {
		this.text = text;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.pager_item, null);
		TextView tv = (TextView) view.findViewById(R.id.item_title);
		tv.setText(text);

		return view;
	}
}
