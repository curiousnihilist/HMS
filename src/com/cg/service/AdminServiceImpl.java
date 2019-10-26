package com.cg.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.dao.AdminDao;
import com.cg.dao.AdminDaoImpl;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;

public class AdminServiceImpl implements AdminService{
	
	AdminDao adao = new AdminDaoImpl();
	
	@Override
	public int addHotel(Hotel hotel) throws Exception {
		return adao.addHotel(hotel);
	}

	@Override
	public int deleteHotel(int hotelId) throws Exception {
		return adao.deleteHotel(hotelId);
	}

	@Override
	public void modifyHotel(Hotel hotel) throws HotelNotFoundException {
		adao.modifyHotel(hotel);
	}

	@Override
	public int addRoom(Room room) throws Exception {
		return adao.addRoom(room);
	}

	@Override
	public int deleteRoom(int roomId) throws Exception {
		return adao.deleteRoom(roomId);
	}

	@Override
	public void modifyRoom(Room room) throws RoomNotFoundException {
		adao.modifyRoom(room);
	}

	@Override
	public List<Hotel> listHotels() throws Exception {
		return adao.listHotels();
	}

	@Override
	public List<Booking> viewBookings(int hotelId) throws Exception {
		return adao.viewBookings(hotelId);
	}

	@Override
	public List<User> viewGuestList(int hotelId) throws Exception {
		return adao.viewGuestList(hotelId);
	}

	@Override
	public List<Booking> bookingByDate(Date date) throws BookingNotFoundException {
		return adao.bookingByDate(date);
	}

	@Override
	public boolean validateLogin(String username, String password) throws UserNotFoundException {
		return adao.validateLogin(username,password);
	}

	@Override
	public Hotel findByHotelId(int hotelId) throws HotelNotFoundException {
		return adao.findHotelById(hotelId);
	}

	@Override
	public Room findByRoomId(int roomId) throws RoomNotFoundException {
		return adao.findRoomById(roomId);
	}
	

}
