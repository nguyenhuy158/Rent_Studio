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

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailStudioActivity extends AppCompatActivity
		implements View.OnClickListener {
	// custom date time picker
	public DatePickerDialog.OnDateSetListener date
			= new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			Common.myCalendar.set(Calendar.YEAR, year);
			Common.myCalendar.set(Calendar.MONTH, month);
			Common.myCalendar.set(Calendar.DAY_OF_MONTH, day);
			updateDate(textViewOrderStartDate, Common.myCalendar);
		}
	};
	public TimePickerDialog.OnTimeSetListener time
			= new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			Common.myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			Common.myCalendar.set(Calendar.MINUTE, minute);
			updateTime(textViewOrderTime, Common.myCalendar);
		}
	};
	String StudioId = "";
	Studio currentStudio;
	FloatingActionButton buttonBookNow;
	TextView             textViewOrderTime;
	TextView             textViewOrderStartDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_studio);
		
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
		
		bindUi();
		handleEvent();
	}
	
	private void handleEvent() {
		textViewOrderTime.setOnClickListener(this);
		textViewOrderStartDate.setOnClickListener(this);
		
	}
	
	private void bindUi() {
		textViewOrderTime      = findViewById(R.id.textViewOrderTime);
		textViewOrderStartDate = findViewById(R.id.textViewOrderStartDate);
		updateTime(textViewOrderTime, Common.myCalendar);
		updateDate(textViewOrderStartDate, Common.myCalendar);
	}
	
	private void loadStudio() {
		TextView textViewStudioPrice = findViewById(R.id.textViewStudioPrice);
		TextView textViewStudioDescription = findViewById(
				R.id.textViewStudioDescription);
		Toolbar toolbarStudioName = findViewById(R.id.toolbarStudioName);
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
				textViewStudioDescription.setText(
						currentStudio.getDescription());
				setSupportActionBar(toolbarStudioName);
				getSupportActionBar().setTitle(currentStudio.getName());
				// toolbarStudioName.setTitle();
				
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
			case R.id.textViewOrderStartDate:
				new DatePickerDialog(DetailStudioActivity.this, date,
				                     Common.myCalendar.get(Calendar.YEAR),
				                     Common.myCalendar.get(Calendar.MONTH),
				                     Common.myCalendar.get(
						                     Calendar.DAY_OF_MONTH)).show();
				break;
			case R.id.textViewOrderTime:
				new TimePickerDialog(DetailStudioActivity.this, time,
				                     Common.myCalendar.get(Calendar.YEAR),
				                     Common.myCalendar.get(Calendar.MONTH),
				                     true).show();
				break;
		}
	}
	
	private void book() {
		
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormat = new SimpleDateFormat(STRING.pattern_date,
		                                             new Locale(
				                                             STRING.language_code,
				                                             STRING.country_code));
		
		DateFormat timeFormat = new SimpleDateFormat(STRING.pattern_time,
		                                             new Locale(
				                                             STRING.language_code,
				                                             STRING.country_code));
		
		Request request = new Request(Common.getPhone(), StudioId,
		                              timeFormat.format(date).toString(),
		                              dateFormat.format(date).toString(),
		                              dateFormat.format(date).toString(), 1,
		                              currentStudio.getPrice());
		
		FirebaseDatabase.getInstance().getReference(STRING.REQUEST_TABLE).push()
		                .setValue(request);
	}
	
	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new Common.TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new Common.DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void updateDate(TextView editTextDate, Calendar myCalendar) {
		java.text.DateFormat dateFormat = new SimpleDateFormat(STRING.pattern_date);
		editTextDate.setText(dateFormat.format(myCalendar.getTime()));
	}
	
	public void updateTime(TextView editTextTime, Calendar myCalendar) {
		java.text.DateFormat dateFormat =
				new SimpleDateFormat(STRING.pattern_time);
		editTextTime.setText(dateFormat.format(myCalendar.getTime()));
	}
	
}