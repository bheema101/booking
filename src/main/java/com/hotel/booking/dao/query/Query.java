package com.hotel.booking.dao.query;
public class Query {
 public static final String AVAILABILITY = "SELECT * FROM hotel WHERE checkinTime BETWEEN ? AND ? "
 		                                                       + "AND checkoutTime BETWEEN ? AND ? "
 		                                                       + "AND hotelName =?";
 public static final String FETCHBOOKINGS = "select * from booking where hotelName = ?";
 
 public static final String INSERT_BOOKING = "insert into booking (id,hotelName, checkintime,checkouttime,allowedcapacity,price,booking_status)"
 		                                                +" values ( ?,?,?,?,?,?,?)";
 public static final String INSERT_USER = "insert into user (firstName,lastName,email,mobile,hotelid) values (?,?,?,?,?)";
}
