package com.hotel.booking.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Bookinginfo  {
	private String bookingid;
	private String hotelName;
	private String userName;
	

	public Bookinginfo(String bookingid, String hotelName, String userName) {
		super();
		this.bookingid = bookingid;
		this.hotelName = hotelName;
		this.userName = userName;
	}

	public Bookinginfo() {
		super();
		
	}

	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
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

}
