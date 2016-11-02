package com.threegroup.vchuang.ourfragment;

import java.util.ArrayList;
import java.util.List;

import com.threegroup.vchuang.R;
import com.zlw.adapter.MyRecycleViewAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 赵乐玮
 *
 */
public class VQuanFragment extends Fragment {
	private RecyclerView rv;
	private FloatingActionButton fab;
	private Toolbar toolbar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_vquan, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		rv = (RecyclerView) view.findViewById(R.id.rv);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));

		fab = (FloatingActionButton) view.findViewById(R.id.fab);
		// fab.attachToRecyclerView(rv);

		toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.WHITE);
		((AppCompatActivity) getActivity()).setTitle("V创平台");

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("数据:  " + i);
		}

		MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(list);
		rv.setAdapter(adapter);
		

	}
}
