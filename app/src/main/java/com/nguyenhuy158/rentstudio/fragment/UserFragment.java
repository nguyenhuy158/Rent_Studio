/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * UserFragment.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.activity.OrderStatusActivity;
import com.nguyenhuy158.rentstudio.activity.WelcomeActivity;

public class UserFragment extends Fragment implements View.OnClickListener {
	
	
	public UserFragment() {
	}
	
	public static UserFragment newInstance(String param1, String param2) {
		UserFragment fragment = new UserFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View   view               = inflater.inflate(R.layout.fragment_user,
		                                             container, false);
		Button buttonOrderHistory = view.findViewById(R.id.buttonOrderHistory);
		Button buttonLogout       = view.findViewById(R.id.buttonLogout);
		buttonOrderHistory.setOnClickListener(this);
		buttonLogout.setOnClickListener(this);
		// Inflate the layout for this fragment
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonOrderHistory:
				Intent intent = new Intent(getContext(),
				                           OrderStatusActivity.class);
				startActivity(intent);
				break;
			case R.id.buttonLogout:
				intent = new Intent(getContext(), WelcomeActivity.class);
				intent.setFlags(
						Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				break;
		}
	}
}