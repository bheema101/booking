package com.hotel.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.hotel.booking.dao.query.Quary;
import com.hotel.booking.model.Address;
import com.hotel.booking.model.Hotel;

@Repository
public class BookingDaoImpl implements BookingDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	
	public String  ADDRES_INSERT_QUERY = "insert into address(houseNo,street,city,state,country,pincode) values (?,?,?,?,?,?)";

	public String insert_query = "insert into hotel (hotelName,checkinTime,checkoutTime,address_id) values (?,?,?,?) ";



	private String writeValueAsString;
	
	@Override
	public void saveBooking(Hotel hotel) {
	
	ObjectMapper objectMapper = new ObjectMapper();
	objectMapper.registerModule(new JSR310Module());
	objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
 
	try {
		writeValueAsString = objectMapper.writeValueAsString(hotel);
		System.err.println("------------");
		System.err.println(writeValueAsString);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	Address address = hotel.getAddress();
	KeyHolder addressKey = new GeneratedKeyHolder();
	jdbcTemplate.update(connection -> {
		PreparedStatement ps = connection
				.prepareStatement(ADDRES_INSERT_QUERY ,new String[] {"address_id"});
		ps.setString(1, address.getHouseNo());
		ps.setString(2, address.getStreet());
		ps.setString(3, address.getCity());
		ps.setString(4, address.getState());
		ps.setString(5, address.getCountry());
		ps.setString(6, address.getPincode());
		return ps;
	}, addressKey);
	System.out.println(addressKey.getKey());
	
	
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(connection -> {
		PreparedStatement ps = connection
				.prepareStatement(insert_query ,new String[] {"id"});
		ps.setString(1, hotel.getHotelName());
		ps.setTimestamp(2, Timestamp.valueOf(hotel.getCheckinTime()));
		ps.setTimestamp(3, Timestamp.valueOf(hotel.getCheckoutTime()));
		ps.setInt(4, (int)addressKey.getKey().intValue());
		return ps;
	}, keyHolder);
	System.out.println(keyHolder.getKey());
        //return (int) keyHolder.getKey();
	// System.out.println(updatedRows);
    }
	  
	
	public List<Hotel> getAvialbility(Hotel hotel) {
		Object[] args = new Object[] 
				{hotel.getCheckinTime(),hotel.getCheckoutTime(),hotel.getCheckinTime(),hotel.getCheckoutTime(),hotel.getHotelName()};
		List<Hotel> hotels = jdbcTemplate.query(Quary.availability,args , 
				new ResultSetExtractor<List<Hotel>>() {

					@Override
					public List<Hotel> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Hotel> hotels = new ArrayList<>();
						 while(rs.next()){  
						Hotel avaiHotel = new Hotel();
					    avaiHotel.setHotelName(rs.getString("hotelName"));
					    avaiHotel.setCheckinTime(rs.getTimestamp("checkinTime").toLocalDateTime());
					    hotels.add(avaiHotel);
						 }
						return hotels;
					}
			
		});
		return hotels;
	}
	
	
}
