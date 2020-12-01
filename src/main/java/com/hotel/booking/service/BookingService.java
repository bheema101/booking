package com.hotel.booking.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.booking.model.BookingReq;
import com.hotel.booking.model.Hotel;

public interface BookingService {
   public void saveBooking(Hotel hotel);
   List<Hotel> getAvialbility(Hotel hotel);
   List<Hotel> getbookings(String hotelName);
   void bookHotel(BookingReq bookreq);
   void cancelBooking(int bookingid);
}
