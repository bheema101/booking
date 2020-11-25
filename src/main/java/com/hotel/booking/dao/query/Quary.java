package com.hotel.booking.dao.query;
public class Quary {
 public static final String availability = "SELECT * FROM hotel WHERE checkinTime BETWEEN ? AND ? "
 		                                                       + "AND checkoutTime BETWEEN ? AND ? "
 		                                                       + "AND hotelName =?";
}
