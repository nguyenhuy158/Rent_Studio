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

import com.nguyenhuy158.rentstudio.myinterface.STRING;

import java.util.Locale;

public class Common {
	private static Common INSTANCE = null;
	private static User   user     = null;
	private static String phone  = null;
	private static Locale locale = new Locale(STRING.language_code,
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
}
