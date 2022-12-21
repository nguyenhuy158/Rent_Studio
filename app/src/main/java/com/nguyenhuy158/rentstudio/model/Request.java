/*
 * Copyright (C) 12/21/22, 12:15 PM Nguyen Huy
 *
 * Request.java [lastModified: 12/21/22, 12:15 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.model;

import com.nguyenhuy158.rentstudio.myinterface.STRING;

public class Request {
	String phone;
	Studio studio;
	String bookTime;
	String startDate;
	String endDate;
	int    totalHour;
	int    total;
	int    status;
	
	public Request(String phone, Studio studio, String bookTime,
	               String startDate, String endDate, int totalHour, int total) {
		this.phone     = phone;
		this.studio    = studio;
		this.bookTime  = bookTime;
		this.startDate = startDate;
		this.endDate   = endDate;
		this.totalHour = totalHour;
		this.total     = total;
		this.status    = STRING.ORDER_WAITING;
	}
	
	public Request() {
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Studio getStudio() {
		return studio;
	}
	
	public void setStudio(Studio studio) {
		this.studio = studio;
	}
	
	public String getBookTime() {
		return bookTime;
	}
	
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getTotalHour() {
		return totalHour;
	}
	
	public void setTotalHour(int totalHour) {
		this.totalHour = totalHour;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
}
