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
	
	private boolean isAdmin = false;
	private String  password;
	private String  phone;
	private String  useName = "username";
	
	public User(boolean isAdmin, String password, String phone,
	            String useName) {
		this.isAdmin  = isAdmin;
		this.password = password;
		this.phone    = phone;
		this.useName  = useName;
	}
	
	public User(String password, String phone) {
		this.password = password;
		this.phone    = phone;
	}
	
	public User() {
	}
	
	public User(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getUseName() {
		return useName;
	}
	
	public void setUseName(String useName) {
		this.useName = useName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
