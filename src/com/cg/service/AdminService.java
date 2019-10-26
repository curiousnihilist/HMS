package com.cg.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;

public interface AdminService {

	String usernameRule = "^[a-z0-9_-]{3,15}$";
	String mobileRule = "[7-9][0-9]{9}";
	String emailRule = "^[A-Za-z0-9+_.-]+@(.+)$";

	String passwordRule = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	String hotelnameRule = "[A-Z][a-z \\s] {4,}";
	String cityRule = "[A-Z][a-z] {4,}";

	public int addHotel(Hotel hotel) throws Exception;

	public int deleteHotel(int hotelId) throws Exception;

	public void modifyHotel(Hotel hotel) throws HotelNotFoundException;

	public int addRoom(Room room) throws Exception;

	public int deleteRoom(int roomId) throws Exception;

	public void modifyRoom(Room room) throws RoomNotFoundException;

	public List<Hotel> listHotels() throws Exception;

	public List<Booking> viewBookings(int hotelId) throws Exception;

	public List<User> viewGuestList(int hotelId) throws Exception;

	public List<Booking> bookingByDate(Date date) throws BookingNotFoundException;

	public boolean validateLogin(String username, String password) throws UserNotFoundException;

	default boolean validateUsername(String username) {
		return username.matches(usernameRule);
	}

	default boolean validatePassword(String password) {
		return password.matches(passwordRule);
	}

	default boolean validateHotelName(String hotelName) {
		return hotelName.matches(hotelnameRule);
	}
	
	default boolean validateCity(String city) {
		return city.matches(cityRule); 
	}

	default boolean validateMobileNo(String mobileNo) {
		return mobileNo.matches(mobileRule);
	}

	default boolean validateEmail(String email) {
		return email.matches(emailRule);
	}
	
	public Hotel findByHotelId(int hotelId) throws HotelNotFoundException;
	
	public Room findByRoomId(int roomId) throws RoomNotFoundException;
}
