package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.User;
import com.cg.dao.CustomerDao;
import com.cg.dao.CustomerDaoImpl;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;

public class CustomerServiceImpl implements CustomerService{

	CustomerDao cdao = new CustomerDaoImpl();
	
	@Override
	public int addUser(User user) throws UserNotFoundException {
		return cdao.saveUser(user);
	}

	@Override
	public List<Hotel> findHotelByCity( String city, double minPrice, double maxPrice)
			throws HotelNotFoundException {
		return cdao.searchHotelByCity(city, minPrice, maxPrice);
	}

	@Override
	public Booking bookRoom(Booking booking) throws Exception {
		return cdao.bookRoom(booking);
	}

	@Override
	public List<Booking> viewStatus(int userId) throws BookingNotFoundException {
		return cdao.viewStatus(userId);
	}

	@Override
	public boolean isRoomAvailable(LocalDate checkIn, int roomId) throws BookingNotFoundException {
		List<LocalDate> dates = cdao.getBookdDates(roomId);
		if(checkIn.isAfter(dates.get(0)) && checkIn.isBefore(dates.get(1)))
			return false;
		return true;
	}
	
	@Override
	public boolean validateLogin(int userId, String password) throws UserNotFoundException {
		return cdao.validateLogin(userId, password);
	}

	@Override
	public List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException {
		return cdao.searchHotelByName(hotelName);
	}

}
