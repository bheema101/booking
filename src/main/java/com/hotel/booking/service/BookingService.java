package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.model.Hotel;

public interface BookingService {
   public void saveBooking(Hotel hotel);
   List<Hotel> getAvialbility(Hotel hotel);
}
