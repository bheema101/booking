package com.hotel.booking.dao.query;
public class Query {
 public static final String AVAILABILITY = "SELECT * FROM hotel WHERE checkinTime BETWEEN ? AND ? "
 		                                                       + "AND checkoutTime BETWEEN ? AND ? "
 		                                                       + "AND hotelName =?";
 public static final String FETCHBOOKINGS = "select * from booking where hotelName = ?";
 
 public static final String FETCHBOOKINGS_BY_HOTEL = "SELECT b.id,b.hotelName, concat(u.firstName,\" \",u.lastName) as fullName ,b.checkintime,b.checkouttime"+
  " FROM booking as b,user as u where u.hotelid = b.id and booking_status=? and b.hotelName=?";

 
 public static final String INSERT_BOOKING = "insert into booking (id,hotelName, checkintime,checkouttime,allowedcapacity,price,booking_status)"
 		                                                +" values ( ?,?,?,?,?,?,?)";
 public static final String INSERT_USER = "insert into user (firstName,lastName,email,mobile,hotelid) values (?,?,?,?,?)";
}
