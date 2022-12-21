/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * HomeActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.fragment.RewardFragment;
import com.nguyenhuy158.rentstudio.fragment.UserFragment;
import com.nguyenhuy158.rentstudio.fragment.AccountFragment;
import com.nguyenhuy158.rentstudio.myinterface.STRING;

public class HomeActivity extends AppCompatActivity {
	boolean doubleBackToExitPressedOnce = false;
	private ActionBar toolbar;
	
	
	private NavigationBarView.OnItemSelectedListener onItemSelectedListener
			= new NavigationBarView.OnItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			switch (item.getItemId()) {
				case R.id.accountFragment:
					toolbar.setTitle("Account");
					fragment = new AccountFragment();
					loadFragment(fragment);
					return true;
				case R.id.rewardFragment:
					toolbar.setTitle("Reward");
					fragment = new RewardFragment();
					loadFragment(fragment);
					return true;
				case R.id.userFragment:
					toolbar.setTitle("Profile");
					fragment = new UserFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};
	private BottomNavigationView.OnNavigationItemSelectedListener
			onNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			switch (item.getItemId()) {
				case R.id.accountFragment:
					toolbar.setTitle("Account");
					fragment = new AccountFragment();
					loadFragment(fragment);
					return true;
				case R.id.rewardFragment:
					toolbar.setTitle("Reward");
					fragment = new RewardFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		toolbar = getSupportActionBar();
		
		BottomNavigationView navigation = (BottomNavigationView) findViewById(
				R.id.navigation);
		
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration
				= new AppBarConfiguration.Builder(R.id.accountFragment,
				                                  R.id.rewardFragment,
				                                  R.id.cartFragment,
				                                  R.id.userFragment).build();
		NavController navController = Navigation.findNavController(this,
		                                                           R.id.frame_container);
		NavigationUI.setupActionBarWithNavController(this, navController,
		                                             appBarConfiguration);
		NavigationUI.setupWithNavController(navigation, navController);
	}
	
	private void loadFragment(Fragment fragment) {
		// load fragment
		FragmentTransaction transaction
				= getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, fragment);
		transaction.addToBackStack(null);
		Log.d(STRING.TAG, "loadFragment: Home");
		transaction.commit();
	}
	
	@Override
	public void onBackPressed() {
		// if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
		// 	return;
		// }
		//
		// this.doubleBackToExitPressedOnce = true;
		// FancyToast.makeText(this, STRING.message_exit, FancyToast.LENGTH_SHORT,
		//                     FancyToast.INFO, false).show();
		//
		// new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
		//
		// 	@Override
		// 	public void run() {
		// 		doubleBackToExitPressedOnce = false;
		// 	}
		// }, STRING.delayMillis);
	}
}