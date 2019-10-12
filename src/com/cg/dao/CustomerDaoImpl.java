package com.cg.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public class CustomerDaoImpl implements CustomerDao{
	

	@Override
	public User saveUser(User user) {
		return null;
	}

	@Override
	public List<Hotel> searchHotel(int hotelId, String city, double avgPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> searchRoom(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking bookRoom(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking viewStatus(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
