/*
 * Copyright (C) 12/21/22, 10:20 PM Nguyen Huy
 *
 * DetailStudioActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.model.Common;
import com.nguyenhuy158.rentstudio.model.Request;
import com.nguyenhuy158.rentstudio.model.Studio;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailStudioActivity extends AppCompatActivity
		implements View.OnClickListener {
	String StudioId = "";
	Studio currentStudio;
	
	FloatingActionButton buttonBookNow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_studio);
		
		// new Handler().postDelayed(new Runnable() {
		// 	@Override
		// 	public void run() {
		// 		finish();
		// 	}
		// }, 2000);
		
		if (getIntent() != null) {
			StudioId = getIntent().getStringExtra(STRING.KEY_STUDIO_ID);
		}
		Log.i(TAG, "onCreate: " + StudioId);
		if (!StudioId.isEmpty() || StudioId != null) {
			Log.i(TAG, "onCreate: load list");
			loadStudio();
		}
		
		buttonBookNow = findViewById(R.id.buttonBookNow);
		buttonBookNow.setOnClickListener(this);
	}
	
	private void loadStudio() {
		TextView textViewStudioPrice = findViewById(R.id.textViewStudioPrice);
		Toolbar  toolbarStudioName   = findViewById(R.id.toolbarStudioName);
		ImageView imageViewStudioThumbnail = findViewById(
				R.id.imageViewStudioThumbnail);
		
		FirebaseDatabase.getInstance().getReference(STRING.STUDIO_TABLE).child(
				StudioId).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				currentStudio = snapshot.getValue(Studio.class);
				Log.d(TAG,
				      "onDataChange: detail ==>" + currentStudio + " " + currentStudio.getPrice() + " name " + currentStudio.getName());
				Locale locale = new Locale(STRING.language_code,
				                           STRING.country_code);
				NumberFormat numberFormat = NumberFormat.getCurrencyInstance(
						locale);
				textViewStudioPrice.setText(
						numberFormat.format(currentStudio.getPrice()));
				
				toolbarStudioName.setTitle(currentStudio.getName());
				Log.d(TAG,
				      "onDataChange: name " + toolbarStudioName.getTitle() + " " + " name " + currentStudio.getName());
				Picasso.get().load(currentStudio.getThumbnailUrl()).into(
						imageViewStudioThumbnail);
			}
			
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				
			}
		});
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonBookNow:
				book();
				break;
		}
	}
	
	private void book() {
		
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormat = new SimpleDateFormat(STRING.pattern_date,
		                                             new Locale(
				                                             STRING.language_code,
				                                             STRING.country_code));
		Request request = new Request(Common.getPhone(), currentStudio,
		                              dateFormat.format(date).toString(),
		                              dateFormat.format(date).toString(),
		                              dateFormat.format(date).toString(), 1,
		                              currentStudio.getPrice());
		
		FirebaseDatabase.getInstance().getReference(STRING.REQUEST_TABLE).push()
		                .setValue(request);
	}
}