package com.hotel.booking.dao;

import java.util.List;

import com.hotel.booking.model.Hotel;

public interface BookingDao {
		void saveBooking(Hotel hotel);
		List<Hotel> getAvialbility(Hotel hotel);
}
