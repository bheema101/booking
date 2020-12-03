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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.hotel.booking.dao.query.Query;
import com.hotel.booking.model.Address;
import com.hotel.booking.model.BookingReq;
import com.hotel.booking.model.Bookinginfo;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.model.MailDto;
import com.hotel.booking.model.User;
import com.hotel.booking.rest.BookingController;
import com.hotel.booking.service.BookingService;

@Repository
public class BookingDaoImpl implements BookingDao{
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	
	
	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
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
		List<Hotel> hotels = jdbcTemplate.query(Query.AVAILABILITY,args , 
				new ResultSetExtractor<List<Hotel>>() {

					@Override
					public List<Hotel> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Hotel> hotels = new ArrayList<>();
						 while(rs.next()){  
						Hotel avaiHotel = new Hotel();
					    avaiHotel.setHotelName(rs.getString("hotelName"));
					    avaiHotel.setCheckinTime(rs.getTimestamp("checkinTime").toLocalDateTime());
					    avaiHotel.setId(rs.getInt("id"));
					    hotels.add(avaiHotel);
					    
						 }
						return hotels;
					}
			
		});
		return hotels;
	}


	@Override
	public List<Bookinginfo> getBookings(String hotelName) {
		
		List<Bookinginfo> bookinginfos = jdbcTemplate.query(Query.FETCHBOOKINGS_BY_HOTEL,new Object[] {true,hotelName} , 
				new ResultSetExtractor<List<Bookinginfo>>() {

					@Override
					public List<Bookinginfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Bookinginfo> bookinginfos = new ArrayList<>();
						 while(rs.next()){  
						Bookinginfo bookinginfo = new Bookinginfo();
						bookinginfo.setBookingid(rs.getInt("id"));
						bookinginfo.setHotelName(rs.getString("hotelName"));
						bookinginfo.setUserName(rs.getString("fullName"));
						bookinginfo.setCheckinTime(rs.getTimestamp("checkinTime").toLocalDateTime());
						bookinginfo.setCheckoutTime(rs.getTimestamp("checkouttime").toLocalDateTime());
						bookinginfos.add(bookinginfo);
						 }
						return bookinginfos;
					}
			
		});
		return bookinginfos;

	}
	
	public void bookHotel(BookingReq bookreq) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(Query.INSERT_BOOKING);
				ps.setInt(1, bookreq.getId());
				ps.setString(2, bookreq.getHotelName());
				ps.setTimestamp(3, Timestamp.valueOf(bookreq.getCheckinTime()));
				ps.setTimestamp(4, Timestamp.valueOf(bookreq.getCheckoutTime()));
				ps.setInt(5,bookreq.getCheckinpersons());
				ps.setDouble(6, bookreq.getPrice());
				ps.setBoolean(7,true);
				return ps;
			}
		});
		
		User user = bookreq.getUser();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(Query.INSERT_USER);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3,user.getEmail());
				ps.setString(4, user.getMobile());
				ps.setInt(5,bookreq.getId());
				return ps;
			}
		});
					
		    MailDto mailDto = new MailDto();
		    mailDto.setMailSubject("Hotel Booking Confirmation");
			mailDto.setBookingMessage("your hotel booking with id "+ bookreq.getId() +"Successfully completed");
			mailDto.setToEmailAddress(user.getEmail());
			SendMail(mailDto);
		
		
	}


	@Override
	public void cancelBooking(int bookingid) {
		jdbcTemplate.update("update  booking set booking_status = ? where id = ?",new Object[] {false,bookingid});
			String emailAddress = jdbcTemplate.queryForObject("select email from user where hotelid = "+bookingid,String.class);
			MailDto mailDto = new MailDto();
			mailDto.setMailSubject("Hotel Booking Canelation");
			mailDto.setBookingMessage("your hotel booking id "+ bookingid +" was Cancelled succesfully");
			mailDto.setToEmailAddress(emailAddress);
			SendMail(mailDto);
		
	}
	
	
	public void SendMail(MailDto mailDto) {
		try {
			HttpEntity<MailDto> entity = new HttpEntity<MailDto>(mailDto);
			ResponseEntity<String> response = restTemplate.exchange("http://email-app/sendMail", HttpMethod.POST, entity, String.class);//("", String.class);
		} catch (Exception e) {
			LOGGER.error("Exception occured while sendingmail",e);
		}

	}
	
	
	
}
