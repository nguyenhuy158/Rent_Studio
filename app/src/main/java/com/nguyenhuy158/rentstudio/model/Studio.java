/*
 * Copyright (C) 12/21/22, 12:14 AM Nguyen Huy
 *
 * Studio.java [lastModified: 12/21/22, 12:14 AM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.model;

public class Studio {
	private String CategoryId;
	private String name;
	private int    price;
	
	public Studio() {
	}
	
	public Studio(String categoryId, String name, int price) {
		CategoryId = categoryId;
		this.name  = name;
		this.price = price;
	}
	
	public String getCategoryId() {
		return CategoryId;
	}
	
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
