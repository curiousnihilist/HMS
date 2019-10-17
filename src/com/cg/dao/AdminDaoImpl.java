package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;

public class AdminDaoImpl implements AdminDao{

	DBSetup db;
	
	
	@Override
	public int addHotel(Hotel hotel) throws Exception {
		Connection conn = null;
		String sql = "insert into hotel values(?,?,?,?,?,?,?,?,?,?)";
		String seq = "select hseq.currval from dual";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,hotel.getCity());
			st.setString(2,hotel.getHotelName());
			st.setString(3,hotel.getAddress());
			st.setString(4,hotel.getDescription());
			st.setDouble(5,hotel.getPrice());
			st.setString(6,hotel.getPhoneNo1());
			st.setString(7,hotel.getPhoneNo2());
			st.setDouble(8,hotel.getRating());
			st.setString(9,hotel.getEmail());
			st.setString(10,hotel.getFax());
			
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			
			if(rs.next()) 
				return rs.getInt("hotel_id");
			return 0;
		} catch (SQLException e) {
			throw new Exception("exception occured!");
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public int deleteHotel(int hotelId) throws Exception {
		Connection conn = null;
		String sql = "delete from hotel where hotel_id=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hotelId);
			int row = st.executeUpdate();
			if(row==1)
				return hotelId;
			return 0;
		} catch (SQLException e) {
			throw new Exception("Hotel not deleted"); 
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int modifyHotel(int hotelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRoom(Room room) throws Exception {
		Connection conn = null;
		String sql = "insert into roomdetails values(?,?,?,?,?,?)";
		String seq = "select rseq.currval from dual";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,room.getHotelId()); // foreign key check for exception
			st.setString(2,room.getRoomNo());
			st.setString(3,room.getRoomType());
			st.setDouble(4,room.getRatePerNight());
			st.setInt(5,room.isAvailability());
			
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			
			if(rs.next()) 
				return rs.getInt("room_id");
			return 0;
		} catch (SQLException e) {
			throw new Exception("exception occured!");
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int deleteRoom(int roomId) throws Exception {
		Connection conn = null;
		String sql = "delete from roomdetails where room_id=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, roomId);
			int row = st.executeUpdate();
			if(row==1)
				return roomId;
			return 0;
		} catch (SQLException e) {
			throw new Exception("Room not deleted"); 
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int modifyRoom(int roomId) {
		return 0;
	}

	@Override
	public List<Hotel> listHotels(int hotelId) throws Exception {
		Connection conn=null;
		Hotel hotel = null;
		List<Hotel> hotels= new ArrayList<Hotel>();
		String sql = "select * from hotel where hotel_id=?";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hotelId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				hotel = new Hotel();
				hotel.setHotelId(rs.getInt(1));
				hotel.setCity(rs.getString(2));
				hotel.setHotelName(rs.getString(3));
				hotel.setAddress(rs.getString(4));
				hotel.setDescription(rs.getString(5));
				hotel.setPrice(rs.getDouble(6));
				hotel.setPhoneNo1(rs.getString(7));
				hotel.setPhoneNo2(rs.getString(8));
				hotel.setRating(rs.getDouble(9));
				hotel.setEmail(rs.getString(10));
				hotel.setFax(rs.getString(11));
			
				hotels.add(hotel);
			}
			if(hotels.size()==0)
				throw new Exception("No hotels found!");
			return hotels;
		} catch (SQLException e) {
			throw new Exception("Sql Exception");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Booking> viewBookings(int hotelId) throws Exception {
		Connection conn = null;
		Booking booking = null;
		List<Booking> bookings = new ArrayList<Booking>();
		String sql = "select * from bookingdetails where hotel_id=?";
		conn = db.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, hotelId);
		ResultSet rs = st.executeQuery();
		try {
			while(rs.next()) {
				booking = new Booking();
				booking.setBookingId(rs.getInt(1));
				booking.setRoomId(rs.getInt(2));
				booking.setUserId(rs.getInt(3));
				booking.setBookedFrom(rs.getDate(4).toLocalDate());
				booking.setBookedTo(rs.getDate(5).toLocalDate());
				booking.setAdults(rs.getInt(6));
				booking.setChildren(rs.getInt(7));
				booking.setAmount(rs.getDouble(8));
				bookings.add(booking);
			}
			if(bookings.size()==0)
				throw new Exception("No booking found!");
			return bookings;
		} catch (Exception e) {
			throw new Exception("Sql Exception");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> viewGuestList(int hotelId) throws Exception {
		Connection conn = null;
		User user;
		List<User> users = new ArrayList<User>();
		String sql = "select * from users where user_id = all(select user_id from bookingdetails where hotel_id=?)";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hotelId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setPassword(rs.getString(2));
				user.setRole(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setMobileNo(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setEmail(rs.getString(8));
				users.add(user);
			}
			if(users.size()==0)
				throw new Exception("No user found");
			return users;
		} catch (Exception e) {
			throw new Exception("Sql Exception");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Booking> bookingByDate(LocalDate date) {
		
		return null;
	}
	
}
