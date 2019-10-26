package com.cg.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.dao.AdminDao;
import com.cg.dao.AdminDaoImpl;
import com.cg.dao.CustomerDao;
import com.cg.dao.CustomerDaoImpl;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;
import com.cg.ui.UserInterface;

import junit.framework.Assert;

public class DaoTest {
	
	AdminDao adao;
	CustomerDao cdao;
	
	@Before
	public void init() {
		adao = new AdminDaoImpl();
		cdao = new CustomerDaoImpl();
	}
	
	@After
	public void destroy() {
		adao = null;
		cdao = null;
	}
	
	@Test
	public void daoHotelTest() throws Exception {
		Hotel h = new Hotel("Kolkata","Regent1","Salt Lake","best in town","7894561122"
							,"7903430071",2000.0,"go","reghelp@gmail.com","89-654");
		adao.addHotel(h);
		
		
	}
	
	@Test
	public void daoRoomTest() throws Exception {
		Room r = new Room(1023,"204","AC",5223.5,1);
		adao.addRoom(r);
	}
	@Test
	public void validateLoginTest() throws UserNotFoundException {
		boolean b;

//		List<Hotel> hotels = cdao.searchHotelByCity("Jamshedpur", 1000, 5000);
//		for (Hotel hotel: hotels) {
//			System.out.println(hotel.toString());
//		}
		
		b = adao.validateLogin("anchita123", "Anch@123");
		System.out.println(b);
	}
	
	@Test
	public void uiTesting() {
		UserInterface ui = null;
		ui.addHotel();
	}
}
