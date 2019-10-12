package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Hotel addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteHotel(int hotelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyHotel(int hotelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Room addRoom(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteRoom(int roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyRoom(int roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Hotel> listHotels(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> viewBookings(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewGuestList(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> bookingByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
