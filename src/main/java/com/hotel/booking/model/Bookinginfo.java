package com.hotel.booking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Bookinginfo  {
	private int bookingid;
	private String hotelName;
	private String userName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkinTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkoutTime;
	

	public Bookinginfo(int bookingid, String hotelName, String userName) {
		super();
		this.bookingid = bookingid;
		this.hotelName = hotelName;
		this.userName = userName;
	}

	public Bookinginfo() {
		super();
		
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(LocalDateTime checkinTime) {
		this.checkinTime = checkinTime;
	}

	public LocalDateTime getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(LocalDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	

}
