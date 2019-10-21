package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;

public interface CustomerService {
	
	int addUser(User user) throws UserNotFoundException;
	List<Hotel> findHotelByCity( String city, double minPrice, double maxPrice ) throws HotelNotFoundException;
	Booking bookRoom(Booking booking) throws Exception;
	List<Booking> viewStatus(int userId) throws BookingNotFoundException;
	boolean isRoomAvailable(LocalDate checkIn, int roomId) throws BookingNotFoundException;
	public boolean validateLogin(int userId, String password) throws UserNotFoundException;
	List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException;

}
