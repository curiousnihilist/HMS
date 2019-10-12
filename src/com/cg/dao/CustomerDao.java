package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public interface CustomerDao {
	
	User saveUser(User user);
	List<Hotel> searchHotel(int hotelId, String city, double avgPrice);
	List<Room> searchRoom(int roomId);
	Booking bookRoom(int roomId);
	Booking viewStatus(int userId);
	
}
