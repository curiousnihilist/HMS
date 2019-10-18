package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;

public interface CustomerService {
	
	int addUser(User user) throws UserNotFoundException;
	List<Hotel> findHotel(int hotelId, String city, double minPrice, double maxPrice ) throws HotelNotFoundException;
	Booking bookRoom(Booking booking) throws Exception;
	Booking viewStatus(int userId) throws BookingNotFoundException;
	//boolean isAvailable(LocalDate checkIn, int roomId);
}
