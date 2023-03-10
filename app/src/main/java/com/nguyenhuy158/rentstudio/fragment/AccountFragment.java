/*
 * Copyright (C) 12/24/22, 12:53 PM Nguyen Huy
 *
 * AccountFragment.java [lastModified: 12/24/22, 12:53 PM]
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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.activity.OrderStatusActivity;
import com.nguyenhuy158.rentstudio.activity.WelcomeActivity;
import com.nguyenhuy158.rentstudio.model.Common;

public class AccountFragment extends Fragment implements View.OnClickListener {
	
	
	public AccountFragment() {
	}
	
	public static AccountFragment newInstance(String param1, String param2) {
		AccountFragment fragment = new AccountFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View   view               = inflater.inflate(R.layout.fragment_account,
		                                             container, false);
		TextView textViewUserPhone = view.findViewById(R.id.textViewUserPhone);
		TextView textViewUserName = view.findViewById(R.id.textViewUserName);
		textViewUserPhone.setText(Common.getUser().getPhone());
		textViewUserName.setText(Common.getUser().getUseName());
		
		
		TextView buttonOrderHistory = view.findViewById(R.id.buttonOrderHistory);
		TextView buttonLogout       = view.findViewById(R.id.buttonLogout);
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