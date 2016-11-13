package com.threegroup.vchuang.ourfragment;

import com.threegroup.vchuang.R;
import com.wzy.MyInvestActivity;
import com.wzy.MyProjectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class EventCenterFragment extends Fragment {
	
	private Button myproject,myinvest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_eventcenter, null);
		
		myproject = (Button) view.findViewById(R.id.MyProject);
		myinvest = (Button) view.findViewById(R.id.MyInvest);
		
		myproject.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),MyProjectActivity.class);
				startActivity(intent);
			}
		});
		myinvest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),MyInvestActivity.class);
				startActivity(intent);
				
			}
		});
		
		return view;
	}
	
}
