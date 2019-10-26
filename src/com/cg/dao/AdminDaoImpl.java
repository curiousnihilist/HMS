package com.cg.dao;

import java.sql.Connection;
import java.sql.Date;
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
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;

public class AdminDaoImpl implements AdminDao{

	DBSetup db = new DBSetup();
	
	
	@Override
	public int addHotel(Hotel hotel) throws HotelNotFoundException {
		Connection conn = null;
		String sql = "insert into hotel values(hseq.nextval,?,?,?,?,?,?,?,?,?,?)";
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
			st.setString(8,hotel.getRating());
			st.setString(9,hotel.getEmail());
			st.setString(10,hotel.getFax());
			
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			
			if(rs.next()) 
				return rs.getInt(1);
			return 0;
		} catch (SQLException e) {
			throw new HotelNotFoundException("exception occured!");
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
	public int deleteHotel(int hotelId) throws HotelNotFoundException {
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
			throw new HotelNotFoundException("Hotel not deleted"); 
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
	public Hotel findHotelById(int hotelId) throws HotelNotFoundException {
		Connection conn = null;
		String sql = "select * from hotel where hotel_id=?";
		Hotel hotel = null;
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
				hotel.setRating(rs.getString(9));
				hotel.setEmail(rs.getString(10));
				hotel.setFax(rs.getString(11));
			}
			if(hotel == null)
				throw new HotelNotFoundException("No Hotel Found by id: "+hotelId);
			return hotel;
		} catch (SQLException e) {
			throw new HotelNotFoundException("No Hotel Found by id: "+hotelId);
		} finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void modifyHotel(Hotel hotel) throws HotelNotFoundException {
		Connection conn = null;
		String sql = "update Hotel set "
				+ "city=?"
				+ "hotel_name=?"
				+ "address=?"
				+ "description=?"
				+ "avg_rate_per_night=?"
				+ "phone_no1=?"
				+ "phone_no2=?"
				+ "rating=?"
				+ "email=?"
				+ "fax=?"
				+ "where hotel_id=?";
		
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
			st.setString(8,hotel.getRating());
			st.setString(9,hotel.getEmail());
			st.setString(10,hotel.getFax());
			st.setInt(11, hotel.getHotelId());
			int rows = st.executeUpdate();
			if(rows == 0)
				throw new HotelNotFoundException("Hotel Not Modified!");
		} catch (SQLException e) {
			throw new HotelNotFoundException("Hotel Not Modified!");
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
	public int addRoom(Room room) throws RoomNotFoundException {
		Connection conn = null;
		String check = "select * from hotel where hotel_id="+room.getHotelId();
		String sql = "insert into roomdetails values(rseq.nextval,?,?,?,?,?)";
		String seq = "select rseq.currval from dual";
		try {
			conn = db.getConnection();
			ResultSet rs1 = conn.createStatement().executeQuery(check);
			
			if(rs1.next() == false)
				throw new RoomNotFoundException("Hotel not found with this room id!");

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1,room.getHotelId()); // foreign key check for exception
			st.setString(2,room.getRoomNo());
			st.setString(3,room.getRoomType());
			st.setDouble(4,room.getRatePerNight());
			st.setInt(5,room.getAvailability());
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			
			if(rs.next()) 
				return rs.getInt(1);
			return 0;
		
			
		} catch (SQLException e) {
			throw new RoomNotFoundException("exception occured!");
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
	public int deleteRoom(int roomId) throws RoomNotFoundException {
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
			throw new RoomNotFoundException("Room not deleted"); 
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
	public Room findRoomById(int roomId) throws RoomNotFoundException {
		Connection conn=null;
		String sql = "select * from room where room_id=?";
		Room room = null;
		try {
			db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, roomId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				room = new Room();
				room.setRoomId(rs.getInt(1));
				room.setHotelId(rs.getInt(2));
				room.setRoomNo(rs.getString(3));
				room.setRoomType(rs.getString(4));
				room.setRatePerNight(rs.getDouble(5));
				room.setAvailability(rs.getInt(6));
			}
			if (room==null)
				throw new RoomNotFoundException("No Room Found by id: "+ roomId);
			return room;
		} catch (SQLException e) {
			throw new RoomNotFoundException("No Room Found by id: "+ roomId);
		} finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modifyRoom(Room room) throws RoomNotFoundException {
		Connection conn = null;
		String sql = "update roomdetails set "
				+ "hotel_id=?"
				+ "room_no=?"
				+ "room_type=?"
				+ "per_night_rate=?"
				+ "availibility=?"
				+ "where room_id=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,room.getHotelId());
			st.setString(2,room.getRoomNo());
			st.setString(3,room.getRoomType());
			st.setDouble(4,room.getRatePerNight());
			st.setInt(5, room.getAvailability());
			st.setInt(6, room.getRoomId());
			int rows = st.executeUpdate();
			if(rows == 0)
				throw new RoomNotFoundException("Hotel Not Modified!");
		} catch (SQLException e) {
			throw new RoomNotFoundException("Hotel Not Modified!");
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
	public List<Hotel> listHotels() throws HotelNotFoundException {
		Connection conn=null;
		Hotel hotel = null;
		List<Hotel> hotels= new ArrayList<Hotel>();
		String sql = "select * from hotel";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
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
				hotel.setRating(rs.getString(9));
				hotel.setEmail(rs.getString(10));
				hotel.setFax(rs.getString(11));
			
				hotels.add(hotel);
			}
			if(hotels.isEmpty())
				throw new HotelNotFoundException("No hotels found!");
			return hotels;
		} catch (SQLException e) {
			throw new HotelNotFoundException("Sql Exception");
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
	public List<Booking> viewBookings(int hotelId) throws BookingNotFoundException {
		Connection conn = null;
		Booking booking = null;
		List<Booking> bookings = new ArrayList<>();
		String sql = "select * from bookingdetails where hotel_id=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hotelId);
			ResultSet rs = st.executeQuery();
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
				booking.setBookingDate(rs.getDate(9).toLocalDate());
				bookings.add(booking);
			}
			if(bookings.isEmpty())
				throw new BookingNotFoundException("No booking found!");
			return bookings;
		} catch (Exception e) {
			throw new BookingNotFoundException("Sql Exception");
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
	public List<User> viewGuestList(int hotelId) throws UserNotFoundException {
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
			if(users.isEmpty())
				throw new UserNotFoundException("No user found");
			return users;
		} catch (Exception e) {
			throw new UserNotFoundException("Sql Exception");
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
	public List<Booking> bookingByDate(Date date) throws BookingNotFoundException {
		Connection conn = null;
		String sql = "select * from bookingdetails where bookingdate=?";
		List<Booking> bookings = new ArrayList<Booking>();
		Booking booking = null;
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, date);
			ResultSet rs = st.executeQuery();
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
				booking.setBookingDate(rs.getDate(9).toLocalDate());
				bookings.add(booking);
			}
			if(bookings.isEmpty())
				throw new BookingNotFoundException("No booking found!");
			return bookings;
		} catch (Exception e) {
			throw new BookingNotFoundException("No booking found!");
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
	public boolean validateLogin(String username, String password) throws UserNotFoundException {
		Connection conn = null;
		String sql = "select password from users where role=? and user_name=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "admin");
			st.setString(2, username);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString(1).equals(password);
			}else
				throw new UserNotFoundException("User Not Found!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserNotFoundException("User Not Found-sql");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
