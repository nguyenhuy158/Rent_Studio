/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * SignUpActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.model.User;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity
		implements View.OnClickListener {
	FirebaseDatabase firebaseDatabase  = FirebaseDatabase.getInstance();
	DatabaseReference    databaseReference =
			firebaseDatabase.getReference(STRING.USER_TABLE);
	EditText             editTextUsername;
	EditText             editTextPassword;
	// EditTextPicker
	Button               button;
	ProgressBar          circularProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		editTextUsername = findViewById(R.id.editTextUsername);
		editTextPassword = findViewById(R.id.editTextPassword);
		button           = findViewById(R.id.buttonSignUp);
		circularProgress = findViewById(R.id.circularProgress);
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonSignUp:
				signUpProcess();
				break;
		}
	}
	
	private void signUpProcess() {
		circularProgress.setVisibility(View.VISIBLE);
		
		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (snapshot.child(editTextUsername.getText().toString())
				            .exists()) {
					FancyToast.makeText(SignUpActivity.this, "Sign Up Fail",
					                    FancyToast.LENGTH_LONG,
					                    FancyToast.ERROR, true).show();
					
					
				} else {
					
					User user = new User(editTextPassword.getText().toString());
					databaseReference.child(
							editTextUsername.getText().toString()).setValue(user);
					  
						FancyToast.makeText(SignUpActivity.this, "Sign Up Success",
						                    FancyToast.LENGTH_LONG,
						                    FancyToast.SUCCESS, true).show();
				}
				circularProgress.setVisibility(View.GONE);
			}
			
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
			
			}
		});
	}
}