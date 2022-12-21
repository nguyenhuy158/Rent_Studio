/*
 * Copyright (C) 12/21/22, 2:35 PM Nguyen Huy
 *
 * UserFragment.java [lastModified: 12/21/22, 2:34 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment implements View.OnClickListener {
	
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	private String mParam1;
	private String mParam2;
	
	public UserFragment() {
	}
	
	public static UserFragment newInstance(String param1, String param2) {
		UserFragment fragment = new UserFragment();
		Bundle       args     = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		Button buttonOrderHistory = view.findViewById(R.id.buttonOrderHistory);
		Button buttonLogout = view.findViewById(R.id.buttonLogout);
		buttonOrderHistory.setOnClickListener(this);
		buttonLogout.setOnClickListener(this);
		// Inflate the layout for this fragment
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonOrderHistory:
				Intent intent = new Intent(getContext(), OrderStatusActivity.class);
				startActivity(intent);
				break;
			case R.id.buttonLogout:
					intent = new Intent(getContext(), WelcomeActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				break;
		}
	}
}