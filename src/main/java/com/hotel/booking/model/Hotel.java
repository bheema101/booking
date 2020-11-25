package com.hotel.booking.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Hotel implements Serializable {
	private String hotelName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkinTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkoutTime;
	@OneToOne
    @JoinColumn(name="id")
	Address address;
	Aminites aminites;
	
	
	public Hotel() {
		
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Aminites getAminites() {
		return aminites;
	}
	public void setAminites(Aminites aminites) {
		this.aminites = aminites;
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
	@Override
	public String toString() {
		return "Hotel [hotelName=" + hotelName + ", address=" + address + ", aminites=" + aminites + "]";
	}
	
	
	
}
