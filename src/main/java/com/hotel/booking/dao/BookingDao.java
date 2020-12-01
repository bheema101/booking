package com.hotel.booking.dao;

import java.util.List;

import com.hotel.booking.model.BookingReq;
import com.hotel.booking.model.Hotel;

public interface BookingDao {
		void saveBooking(Hotel hotel);
		List<Hotel> getAvialbility(Hotel hotel);
		List<Hotel> getBookings(String hotelName);
		void bookHotel(BookingReq bookreq);
		void cancelBooking(int bookingid);
}
