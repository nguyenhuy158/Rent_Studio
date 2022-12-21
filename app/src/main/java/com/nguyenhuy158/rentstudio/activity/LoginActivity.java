/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * LoginActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

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
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.model.Common;
import com.nguyenhuy158.rentstudio.model.User;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity
		implements View.OnClickListener {
	
	
	FirebaseDatabase  firebaseDatabase = FirebaseDatabase.getInstance();
	DatabaseReference databaseReference;
	EditText          editTextUsernameOrPhone;
	EditText          editTextPassword;
	// EditTextPicker
	Button            button;
	ProgressBar       circularProgress;
	User              currentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		databaseReference = firebaseDatabase.getReference(STRING.USER_TABLE);
		
		editTextUsernameOrPhone = findViewById(R.id.editTextUsername);
		editTextPassword        = findViewById(R.id.editTextPassword);
		button                  = findViewById(R.id.buttonSignIn);
		circularProgress        = findViewById(R.id.circularProgress);
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
		Common.setUser(currentUser);
		Common.setPhone(editTextUsernameOrPhone.getText().toString());
		Intent intent = new Intent(this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		this.finish();
	}
	
	private void logInProcess() {
		
		circularProgress.setVisibility(View.VISIBLE);
		
		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (snapshot.child(editTextUsernameOrPhone.getText().toString())
				            .exists()) {
					User user = snapshot.child(
							                    editTextUsernameOrPhone.getText().toString())
					                    .getValue(User.class);
					if (user.getPassword().equals(
							editTextPassword.getText().toString())) {
						FancyToast.makeText(LoginActivity.this, "Login Success",
						                    FancyToast.LENGTH_LONG,
						                    FancyToast.SUCCESS, true).show();
						
						// save currentUser
						currentUser = user;
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