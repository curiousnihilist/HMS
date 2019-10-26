package com.cg.dao;

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

public interface AdminDao {
	public int addHotel(Hotel hotel) throws HotelNotFoundException;
	public int deleteHotel(int hotelId) throws HotelNotFoundException;
	public Hotel findHotelById(int hotelId) throws HotelNotFoundException;
	public void modifyHotel(Hotel hotel) throws HotelNotFoundException;

	public int addRoom(Room room) throws RoomNotFoundException;
	public int deleteRoom(int roomId) throws RoomNotFoundException;
	public Room findRoomById(int roomId) throws RoomNotFoundException;
	public void modifyRoom(Room room) throws RoomNotFoundException;

	public List<Hotel> listHotels() throws HotelNotFoundException;
	public List<Booking> viewBookings(int hotelId) throws BookingNotFoundException;
	public List<User> viewGuestList(int hotelId) throws UserNotFoundException; 
	public List<Booking> bookingByDate(Date date) throws BookingNotFoundException;

	public boolean validateLogin(String username, String password) throws UserNotFoundException;
}
