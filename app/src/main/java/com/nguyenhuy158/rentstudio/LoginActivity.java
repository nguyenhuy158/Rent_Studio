/*
 * Copyright (C) 12/18/22, 5:18 PM Nguyen Huy
 *
 * LoginActivity.java [lastModified: 12/18/22, 5:06 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhuy158.rentstudio.model.User;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity
		implements View.OnClickListener {
	
	
	FirebaseDatabase  firebaseDatabase = FirebaseDatabase.getInstance();
	DatabaseReference databaseReference;
	EditText          editTextUsername;
	EditText          editTextPassword;
	// EditTextPicker
	Button            button;
	ProgressBar       circularProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		databaseReference = firebaseDatabase.getReference(
				getResources().getString(R.string.USER_TABLE));
		
		editTextUsername = findViewById(R.id.editTextUsername);
		editTextPassword = findViewById(R.id.editTextPassword);
		button           = findViewById(R.id.buttonSignIn);
		circularProgress = findViewById(R.id.circularProgress);
		button.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonSignIn:
				logInProcess();
				break;
		}
		
	}
	private void goToHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		this.finish();
	}
	private void logInProcess() {
		
		circularProgress.setVisibility(View.VISIBLE);
		
		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (snapshot.child(editTextUsername.getText().toString())
				            .exists()) {
					User user = snapshot.child(
							editTextUsername.getText().toString()).getValue(
							User.class);
					if (user.getPassword().equals(
							editTextPassword.getText().toString())) {
						FancyToast.makeText(LoginActivity.this, "Login Success",
						                    FancyToast.LENGTH_LONG,
						                    FancyToast.SUCCESS, true).show();
						goToHome();
					} else {
						FancyToast.makeText(LoginActivity.this, "Login Fail",
						                    FancyToast.LENGTH_LONG,
						                    FancyToast.WARNING, true).show();
					}
					
				} else {
					FancyToast.makeText(LoginActivity.this, "Login Fail",
					                    FancyToast.LENGTH_LONG,
					                    FancyToast.ERROR, true).show();
				}
				circularProgress.setVisibility(View.GONE);
			}
			
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
			
			}
		});
	}
}