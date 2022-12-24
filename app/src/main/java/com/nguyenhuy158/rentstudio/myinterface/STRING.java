/*
 * Copyright (C) 12/21/22, 12:50 PM Nguyen Huy
 *
 * STRING.java [lastModified: 12/21/22, 12:50 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.myinterface;

public interface STRING {
	//          locate code java
	//      url: https://www.localeplanet.com/java/vi-VN/index.html
	String language_code  = "vi";
	String country_code   = "VN";
	String pattern_date   = "dd/MM/yyyy";
	String USER_TABLE     = "User";
	String CATEGORY_TABLE = "Category";
	String STUDIO_TABLE   = "Studio";
	String REQUEST_TABLE  = "Request";
	
	String KEY_CATEGORY_ID = "CategoryId";
	String KEY_NAME        = "name";
	String KEY_STUDIO_ID   = "StudioId";
	String KEY_PHONE       = "phone";
	
	
	int ORDER_CANCELLED = -1;
	int ORDER_WAITING   = 0;
	int ORDER_DONE      = 2;
	int ORDER_SUCCESS   = 1;
	
	//	debug
	String       TAG                     = "ContentValues";
	String       fragment                = "fragmentt";
	String       message_exit            = "Please click BACK again to exit";
	long         delayMillis             = 2000;
	long         delayMillisSplashScreen = 500;
	CharSequence hint_search             = "Search studio";
	int          REPEAT_COUNT            = 2;
	CharSequence SIGNUP_FAIL             = "Fail! Phone or Username exists";
	CharSequence SIGNUP_SUCCESS          = "Success";
}
