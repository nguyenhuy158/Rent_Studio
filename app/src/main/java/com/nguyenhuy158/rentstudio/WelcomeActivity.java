/*
 * Copyright (C) 12/18/22, 5:18 PM Nguyen Huy
 *
 * MainActivity.java [lastModified: 12/18/22, 5:10 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity
		implements View.OnClickListener {
	
	private static final String TAG = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		firebase();
		
		Button button = findViewById(R.id.buttonSignIn);
		button.setOnClickListener(this);
		
		Button buttonSignUp = findViewById(R.id.buttonSignUp);
		buttonSignUp.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonSignIn:
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
			case R.id.buttonSignUp:
				Intent intentSignUp = new Intent(this, SignUpActivity.class);
				startActivity(intentSignUp);
				break;
		}
	}
	
	private void firebase() {
		// Write a message to the database
		FirebaseDatabase  database = FirebaseDatabase.getInstance();
		DatabaseReference myRef    = database.getReference("message");
		
		myRef.setValue("Hello, World!");
		
		// Read from the database
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				// This method is called once with the initial value and again
				// whenever data at this location is updated.
				String value = dataSnapshot.getValue(String.class);
				Log.d(TAG, "Value is: " + value);
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// Failed to read value
				Log.w(TAG, "Failed to read value.", error.toException());
			}
		});
	}
	
	
}