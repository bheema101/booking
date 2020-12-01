package com.hotel.booking.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingReq implements Serializable{
	private int id;
	private String hotelName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkinTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkoutTime;
	private int checkinpersons;
	private double price;
	Address address;
	User user;
	
	
	
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCheckinpersons() {
		return checkinpersons;
	}
	public void setCheckinpersons(int checkinpersons) {
		this.checkinpersons = checkinpersons;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
