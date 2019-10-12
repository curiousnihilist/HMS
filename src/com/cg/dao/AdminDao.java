package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public interface AdminDao {
	public Hotel addHotel(Hotel hotel);
	public int deleteHotel(int hotelId);
	public int modifyHotel(int hotelId);

	public Room addRoom(Room room);
	public int deleteRoom(int roomId);
	public int modifyRoom(int roomId);

	public List<Hotel> listHotels(int hotelId);
	public List<Booking> viewBookings(int hotelId);
	public List<User> viewGuestList(int hotelId); 
	public List<Booking> bookingByDate(LocalDate date);
}
