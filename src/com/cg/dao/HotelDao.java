package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public interface HotelDao {
	
	//User functionality
	
	User saveUser(User user);
	List<Hotel> searchHotel(int hotelId, String city, double avgRating, double avgPrice);
	List<Room> searchRoom(int roomId);
	Booking bookRoom(int roomId);
	Booking viewStatus(int userId);
	
	//Admin functionality
	Hotel addHotel();
	Hotel modifyHotel(int hotelId);
	boolean deleteHotel(int roomId);
	Room addRoom();
	Room modifyRoom(int roomId);
	boolean deleteRoom(int roomId);
	
	List<Hotel> viewHotels();
	List<Booking> viewBookingOfHotel(int hotelId);
	List<User> viewGuestOfHotel(int hotelId);
	List<Booking> bookingsAsPerDate(LocalDate date);
}
