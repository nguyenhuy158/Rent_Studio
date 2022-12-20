/*
 * Copyright (C) 12/18/22, 5:29 PM Nguyen Huy
 *
 * User.java [lastModified: 12/18/22, 5:29 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.model;

public class User {
	
	private String password;
	
	public User() {
	}
	
	public User(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
