package com.cg.dao;

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

public interface CustomerDao {
	
	int saveUser(User user) throws UserNotFoundException;
	List<Hotel> searchHotel(int hotelId, String city, double minPrice, double maxPrice ) throws HotelNotFoundException;
	List<Room> searchRoom(int hotelId) throws RoomNotFoundException;
	Booking bookRoom(Booking booking) throws Exception;
	Booking viewStatus(int userId) throws BookingNotFoundException;
	List<LocalDate> getBookdDates(int roomId) throws BookingNotFoundException;
}
