/*
 * Copyright (C) 12/20/22, 10:06 AM Nguyen Huy
 *
 * HomeActivity.java [lastModified: 12/20/22, 10:06 AM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
	private ActionBar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		toolbar = getSupportActionBar();
		
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		// navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
		navigation.setOnItemSelectedListener(onItemSelectedListener);
		// attaching bottom sheet behaviour - hide / show on scroll
		CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
		layoutParams.setBehavior(new BottomNavigationBehavior());
		
		// load the store fragment by default
		toolbar.setTitle("Account");
		loadFragment(new AccountFragment());
	}
	
	NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			switch (item.getItemId()) {
				case R.id.navigation_account:
					toolbar.setTitle("Account");
					fragment = new AccountFragment();
					loadFragment(fragment);
					return true;
				case R.id.navigation_reward:
					toolbar.setTitle("Reward");
					fragment = new RewardFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};
	private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			switch (item.getItemId()) {
				case R.id.navigation_account:
					toolbar.setTitle("Account");
					fragment = new AccountFragment();
					loadFragment(fragment);
					return true;
				case R.id.navigation_reward:
					toolbar.setTitle("Reward");
					fragment = new RewardFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};
	
	private void loadFragment(Fragment fragment) {
		// load fragment
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}