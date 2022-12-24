/*
 * Copyright (C) 12/21/22, 12:14 PM Nguyen Huy
 *
 * Common.java [lastModified: 12/21/22, 12:14 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.model;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.nguyenhuy158.rentstudio.myinterface.STRING;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Common {
	private static Common INSTANCE = null;
	private static User   user     = null;
	private static String phone    = null;
	private static Locale locale   = new Locale(STRING.language_code,
	                                            STRING.country_code);
	
	private Common() {}
	
	public static String getPhone() {
		return phone;
	}
	
	
	public static void setPhone(String phone) {
		Common.phone = phone;
	}
	
	;
	
	public static synchronized Common getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Common();
		}
		return (INSTANCE);
	}
	
	public static synchronized User getUser() {
		if (user == null) {throw new RuntimeException("User is null");}
		return user;
	}
	
	public static synchronized void setUser(User user) {
		Common.user = user;
	}
	
	public final static Calendar                           myCalendar =
			Calendar.getInstance();
	
	

	
	//	date time picker
	public static class TimePickerFragment extends DialogFragment
			implements TimePickerDialog.OnTimeSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c      = Calendar.getInstance();
			int            hour   = c.get(Calendar.HOUR_OF_DAY);
			int            minute = c.get(Calendar.MINUTE);
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
			                            DateFormat.is24HourFormat(
					                            getActivity()));
		}
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user
		}
		
		
	}
	
	//	time picker
	public static class DatePickerFragment extends DialogFragment
			implements DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c     = Calendar.getInstance();
			int            year  = c.get(Calendar.YEAR);
			int            month = c.get(Calendar.MONTH);
			int            day   = c.get(Calendar.DAY_OF_MONTH);
			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(requireContext(), this, year, month,
			                            day);
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
		}
	}
}
