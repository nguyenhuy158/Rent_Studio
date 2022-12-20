/*
 * Copyright (C) 12/20/22, 10:44 PM Nguyen Huy
 *
 * Category.java [lastModified: 12/20/22, 10:44 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.model;

public class Category {
	
	private String name;
	private String thumbnailUrl;
	
	public Category() {
	}
	
	public Category(String name, String thumbnailUrl) {
		this.name         = name;
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}
