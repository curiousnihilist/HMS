package com.cg.ui;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.User;
import com.cg.bean.Hotel;
import com.cg.bean.Booking;
import com.cg.bean.Room;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;
import com.cg.service.CustomerService;
import com.cg.service.AdminService;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.AdminServiceImpl;

public class UserInterface {
	private static Scanner console;

	static {
		console = new Scanner(System.in);
	}

	public static void main(String[] args) {

		int option = 0;
		while (true) {
			System.out.println("Specify Role: ");
			System.out.println("1-New User");
			System.out.println("2-Customer / Hotel Staff");
			System.out.println("3-Hotel Admin");
			System.out.println("4-Exit");
			System.out.println("Enter Option");
			option = console.nextInt();

			switch (option) {
			case 1:
				registerUser();
				break;
			case 2:
				loginUser();
				break;
			case 3:
				try {
					loginAdmin();
				} catch (UserNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Please enter correct option");
			}
		}
	}

	private static void loginUser() {
		CustomerService userService = new CustomerServiceImpl();

		String password, username;
		int userId ;
		
		do {
			System.out.print("Enter Username: ");
			username = console.next();

			System.out.print("Enter Password: ");
			password = console.next();
		} while (!userService.validateLogin(username, password));
		
			int option = 0;
			while (true) {
				System.out.println("Choose an action: ");
				System.out.println("1-Search for Hotel Rooms");
				System.out.println("2-View Booking Status");
				System.out.println("3-Exit");
				System.out.println("Enter Option");
				option = console.nextInt();

				switch (option) {
				case 1:
					findHotel();
					break;
				case 2:
					viewBookingStatus();
					break;
				case 3:
					return;
				default:
					System.out.println("Please enter correct option");
				}
			}
	}

	private static void loginAdmin() throws Exception{
		AdminService adminService= new AdminServiceImpl();

		String password, username;
	
		do{
		do {
			System.out.print("Enter Username: ");
			username = console.next();
		} while(!adminService.validateUsername(username));

		do {
			System.out.print("Enter Password: ");
			password = console.next();
		} while(!adminService.validatePassword(password));

		} while(!adminService.validateLogin(username, password));
		
		int option = 0;
		while(true){
				System.out.println("Choose an action: ");
				System.out.println("1-Add Hotel");
				System.out.println("2-Modify Hotel");
				System.out.println("3-Add Hotel Room");
				System.out.println("4-Modify Hotel Room");
				System.out.println("5-Delete Hotel");
				System.out.println("6-Delete Hotel Room");
				System.out.println("7-View Hotel List");
				System.out.println("8-View Hotel specific Bookings");
				System.out.println("9-View Guest List of a Hotel");
				System.out.println("10-View Date specific Bookings");
				System.out.println("11-Exit");
				System.out.println("Enter Option");
				option = console.nextInt();
			
				switch(option){
				case 1: addHotel(); break;
				case 2: modifyHotel(); break;
				case 3: addRoom(); break;
				case 4: modifyRoom(); break;
				case 5: deleteHotel(); break;
				case 6: deleteRoom(); break;
				case 7: listHotels(); break;
				case 8: listBookings(); break;
				case 9: listGuestList(); break;
				case 10: listBookingByDate(); break;
				case 11: return;
				default: System.out.println("Please enter correct option");
				}
			} 
		
	}

	private static void registerUser() {
		CustomerService userService = new CustomerServiceImpl();

		String userName, password, mobileNo, phone, role, address, email;
		int userId;

		do {
			System.out.print("Enter User Name: ");
			userName = console.next();
		} while (!userService.validateUsername(userName));

//		do {
//			System.out.print("Enter User ID: ");
//			userId = console.nextInt();
//		} while (!userService.validateUserId(userId));

		do {
			System.out.print("Enter Password: ");
			password = console.next();
		} while (!userService.validatePassword(password));

		do {
			System.out.print("Enter Role: ");
			role = console.next();
		} while (!userService.validateRole(role));

		do {
			System.out.print("Enter Mobile No.: ");
			mobileNo = console.next();
		} while (!userService.validateMobileNo(mobileNo));

		do {
			System.out.print("Enter Phone No.: ");
			phone = console.next();
		} while (!userService.validateMobileNo(phone));

		do {
			System.out.print("Enter Email ID: ");
			email = console.next();
		} while (!userService.validateEmail(email));

		System.out.print("Enter Address: ");
		address = console.next();

		User u = new User();
		u.setUserName(userName);
		u.setPassword(password);
		u.setRole(role);
		u.setMobileNo(mobileNo);
		u.setPhone(phone);
		u.setAddress(address);
		u.setEmail(email);

		try {
			 userId = userService.addUser(u);
			System.out.println("Registration successful with User ID: " + userId);
			loginUser();
		} catch (UserNotFoundException e1) {
			System.out.println(e1.getMessage());
			registerUser();
		}

	}

	private static void findHotel(){

		CustomerService userService = new CustomerServiceImpl();

		int flag=0, option = 0;
		double minPrice, maxPrice;
		String city, hotelName;

		System.out.print("Search by:	0-By City	1-By Hotel");
		do{
			flag = console.nextInt();
		}while((flag != 0) && (flag != 1));

		
		if(flag == 0){  
			do {
				System.out.print("Enter city to look for hotels in: ");
				city = console.next();
			} while(!userService.validateCity(city));
		
			System.out.print("Enter minimum price: ");
			minPrice = console.nextDouble();

			System.out.print("Enter maximum price: ");
			maxPrice = console.nextDouble();

			try {
				List<Hotel> result = userService.findHotelByCity(city, minPrice, maxPrice);
				for (Hotel hotel : result) {
					System.out.println(hotel.toString());
				}
			} catch (HotelNotFoundException e2) {
				System.out.println("No hotels found with those criterion!! Try again");
			}
			
			while(true){
				System.out.println("Choose an action: ");
				System.out.println("1-Proceed with bookings");
				System.out.println("2-Exit");
				option = console.nextInt();

				switch(option){
				case 1: bookRoom(); break;
				case 2: System.exit(0);
				default: System.out.println("Please enter correct option");
				}
			}
		}

		else if(flag == 1){
			do {
				System.out.print("Enter hotel name: ");
				hotelName = console.next();
			} while(!userService.validateHotelName(hotelName));
		
			try {
				List<Hotel> result = userService.findHotelByName(hotelName);
				for (Hotel hotel : result) {
					System.out.println(hotel.toString());
				}
			} catch (HotelNotFoundException e2) {
				System.out.println("No such hotel found!! Try again");
			}

			while(true){
				System.out.println("Choose an action: ");
				System.out.println("1-Proceed with bookings");
				System.out.println("2-Exit");
				option = console.nextInt();

				switch(option){
				case 1: bookRoom(); break;
				case 2: System.exit(0);
				default: System.out.println("Please enter correct option");
				}
			}
		}
	}

	private static void bookRoom() {

		CustomerService userService = new CustomerServiceImpl();

		int roomId, adults, children, hotelId, userId=0;
		String userName;
		double amount=0.0;
		
		DateTimeFormatter formatter = null;
		 
		LocalDate bookedFrom = null, bookedTo = null;
		String dateFrom, dateTo;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
		do {
			System.out.print("Enter Username: ");
			userName = console.next();
		} while (!userService.validateUsername(userName));

		System.out.print("Enter Hotel ID: ");
		hotelId = console.nextInt();

		System.out.print("Enter Room Id: ");
		roomId = console.nextInt();

		System.out.print("Enter no. of adults (max-2): ");
		adults = console.nextInt();

		System.out.print("Enter no. of children (max-2): ");
		children = console.nextInt();

		try {
			do {
			do {
				System.out.print("Enter staring date ('dd/mm/yyyy') : ");
				dateFrom = console.next();
				bookedFrom = LocalDate.parse(dateFrom, formatter);
			} while (!userService.validateDate(bookedFrom));

			do {
				System.out.print("Enter ending date ('dd/mm/yyyy') : ");
				dateTo = console.next();
				bookedTo = LocalDate.parse(dateTo, formatter);
			} while (!userService.validateDate(bookedTo));
			}while(userService.isRoomAvailable(bookedFrom, bookedTo, roomId));
		} catch (BookingNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			userId = userService.getCustomerUserId(userName);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Booking b = new Booking();
//		b.setBookingId(bookingId);
		b.setUserId(userId); 	//change to username, take input as username and map it to it's userid and send
		b.setHotelId(hotelId);
		b.setRoomId(roomId);
		b.setBookedFrom(bookedFrom);
		b.setBookedTo(bookedTo);
		b.setAmount(amount);
		b.setAdults(adults);
		b.setChildren(children);

		try {
			Booking booking = userService.bookRoom(b);
			System.out.println("Booking successful with Booking ID: " + booking.getBookingId());
			viewBookingStatus();
		} catch (HotelNotFoundException e2) {
			System.out.println("Booking unsuccessful!! Try again");
			bookRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void viewBookingStatus() {

		CustomerService userService = new CustomerServiceImpl();

		String userName;

		System.out.print("Enter Username: ");
		userName = console.next();

		try {
			List<Booking> result = userService.viewStatus(userName);
			for (Booking booking : result) {
				System.out.println(booking.toString());
			}
		} catch (BookingNotFoundException e3) {
			System.out.println("No bookings found!! Try again");
		}
	}

	public static void addHotel() {

		AdminService adminService = new AdminServiceImpl();

		int hotelId;
		String city, hotelName, address, description, phoneNo1, phoneNo2, rating, email, fax;
		double avgRatePerNight;

		//do {
			System.out.print("Enter Hotel Name: ");
			hotelName = console.nextLine();
		//} while (!adminService.validateHotelName(hotelName));
		
		//do {
			System.out.print("Enter City: ");
			city = console.nextLine();
		//} while (!adminService.validateCity(city));

		System.out.print("Enter Address: ");
		address = console.nextLine();

		System.out.print("Add a description: ");
		description = console.nextLine();

		System.out.print("Enter average rate per night: ");
		avgRatePerNight = console.nextDouble();

		do {
			System.out.print("Enter Phone No. 1: ");
			phoneNo1 = console.next();
		} while (!adminService.validateMobileNo(phoneNo1));	//complete validation in adminservice

		do {
			System.out.print("Enter Phone No. 2: ");
			phoneNo2 = console.next();
		} while (!adminService.validateMobileNo(phoneNo2));

		do {
			System.out.print("Enter Email ID: ");
			email = console.next();
		} while (!adminService.validateEmail(email));	//complete validation in adminservice

		System.out.print("Enter fax no.: ");
		fax = console.next();

		System.out.print("Enter hotel rating: ");
		rating = console.next();

		Hotel h = new Hotel();
		h.setCity(city);
		h.setHotelName(hotelName);
		h.setDescription(description);
		h.setAddress(address);
		h.setRating(rating);
		h.setPrice(avgRatePerNight);
		h.setPhoneNo1(phoneNo1);
		h.setPhoneNo2(phoneNo2);
		h.setEmail(email);
		h.setFax(fax);

		try {
			hotelId = adminService.addHotel(h);
			System.out.println("New hotel added successfully with Hotel ID: " + hotelId);
			listHotels();
		} catch (HotelNotFoundException e2) {
			System.out.println("Hotel could not be added!! Try again");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void modifyHotel() {
		AdminService adminService = new AdminServiceImpl();
		int hotelId;
		String city, hotelName, address, description, phoneNo1, phoneNo2, rating, email, fax;
		double avgRatePerNight;
		System.out.println("Enter hotel Id of hotel to be modified:");
		hotelId = console.nextInt();
		
		Hotel newHotel, oldHotel = null;
		try {
			oldHotel = adminService.findByHotelId(hotelId);
		} catch (HotelNotFoundException e) {
			System.out.println("Hotel not found with this ID!");
			modifyHotel();
		}
		System.out.println("Enter details to be modified: ");
		System.out.println("For fields that need not be modified, enter 'NA' or 'na' : ");
		System.out.println("If average rate per night does not need to be changed enter '0' :");
		
		do {
			System.out.print("Enter Hotel Name: ");
			hotelName = console.next();
			if(hotelName.equalsIgnoreCase("na"))
				hotelName = oldHotel.getHotelName();
		} while (!adminService.validateHotelName(hotelName));
		
		do {
			System.out.print("Enter City: ");
			city = console.next();
			if(city.equalsIgnoreCase("na"))
				city = oldHotel.getCity();
		} while (!adminService.validateCity(city));

		System.out.print("Enter Address: ");
		address = console.next();
		if(address.equalsIgnoreCase("na"))
			address = oldHotel.getAddress();

		System.out.print("Add a description: ");
		description = console.next();
		if(description.equalsIgnoreCase("na"))
			description = oldHotel.getDescription();

		System.out.print("Enter average rate per night: ");
		avgRatePerNight = console.nextInt();
		if(avgRatePerNight==0)
			avgRatePerNight = oldHotel.getPrice();

		do {
			System.out.print("Enter Phone No. 1: ");
			phoneNo1 = console.next();
			if(phoneNo1.equalsIgnoreCase("na"))
				phoneNo1 = oldHotel.getPhoneNo1();
		} while (!adminService.validateMobileNo(phoneNo1));	//complete validation in adminservice

		do {
			System.out.print("Enter Phone No. 2: ");
			phoneNo2 = console.next();
			if(phoneNo2.equalsIgnoreCase("na"))
				phoneNo2 = oldHotel.getPhoneNo2();
		} while (!adminService.validateMobileNo(phoneNo2));

		do {
			System.out.print("Enter Email ID: ");
			email = console.next();
			if(email.equalsIgnoreCase("na"))
				email = oldHotel.getEmail();
		} while (!adminService.validateEmail(email));	//complete validation in adminservice

		System.out.print("Enter fax no.: ");
		fax = console.next();
		if(fax.equalsIgnoreCase("na"))
			fax = oldHotel.getFax();

		System.out.print("Enter hotel rating: ");
		rating = console.next();
		if(rating.equalsIgnoreCase("na"))
			rating = oldHotel.getRating();
		
		newHotel = new Hotel();
		newHotel.setHotelId(hotelId);
		newHotel.setCity(city);
		newHotel.setHotelName(hotelName);
		newHotel.setDescription(description);
		newHotel.setAddress(address);
		newHotel.setRating(rating);
		newHotel.setPrice(avgRatePerNight);
		newHotel.setPhoneNo1(phoneNo1);
		newHotel.setPhoneNo2(phoneNo2);
		newHotel.setEmail(email);
		newHotel.setFax(fax);
		
		try {
			adminService.modifyHotel(newHotel);
			System.out.println("Hotel modified successfully with hotel ID: " + hotelId);
		}catch(HotelNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteHotel() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

		System.out.println("Enter hotel ID of the hotel to be deleted: ");
		hotelId = console.nextInt();
		
		try {
			adminService.deleteHotel(hotelId);
			System.out.println("Hotel with ID: " + hotelId + " deleted successfully!");
			listHotels();
		} catch (HotelNotFoundException e2) {
			System.out.println(e2.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addRoom() {

		AdminService adminService = new AdminServiceImpl();
		
		int roomId, hotelId, availability;
		String roomType, roomNo;
		double ratePerNight;

		System.out.print("Enter Hotel ID: ");
		hotelId = console.nextInt();
		
		Hotel hotel = null;
		try {
			hotel = adminService.findByHotelId(hotelId);
		} catch (HotelNotFoundException e) {
			System.out.println("Hotel not found with this ID!");
			addRoom();
		}

		System.out.println("Enter Availability ('1' for 'yes', '0' for 'no'): ");
		availability = console.nextInt();

		System.out.print("Enter Room No.: ");
		roomNo = console.next();

		System.out.print("Enter Room Type: ");
		roomType = console.next();

		System.out.print("Enter the rate per night: ");
		ratePerNight = console.nextDouble();

		Room r = new Room();
//		r.setRoomId(roomId);
		r.setHotelId(hotelId);
		r.setRoomNo(roomNo);
		r.setRoomType(roomType);
		r.setRatePerNight(ratePerNight);

		try {
			roomId = adminService.addRoom(r);
			System.out.println("New room added successfully with Room ID: " + roomId);
		} catch (HotelNotFoundException e2) {
			System.out.println("Room could not be added!! Try again");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void modifyRoom() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId, roomId, availability;
		String roomType, roomNo;
		double ratePerNight;
		System.out.println("Enter hotel Id of hotel to be modified:");
		hotelId = console.nextInt();
		System.out.println("Enter room ID of room to be modified: ");
		roomId = console.nextInt();
		
		Hotel oldHotel=null;
		Room newRoom, oldRoom = null;
		try {
			oldHotel = adminService.findByHotelId(hotelId);
			oldRoom = adminService.findByRoomId(roomId);
		} catch (HotelNotFoundException e) {
			System.out.println("Hotel or specified room not found with this ID!");
			modifyRoom();
		} catch (RoomNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter details to be modified: ");
		System.out.println("For fields that need not be modified, enter 'NA' or 'na' : ");
		System.out.println("If availability does not need to be changed enter '9' :");
		System.out.println("If rate per night does not need to be changed enter '0' :");
		
		System.out.println("Enter Availability ('1' for 'yes', '0' for 'no'): ");
		availability = console.nextInt();
		if(availability==9)
			availability = oldRoom.getAvailability();

		System.out.print("Enter Room No.: ");
		roomNo = console.next();
		if(roomNo.equalsIgnoreCase("na"))
			roomNo = oldRoom.getRoomNo();

		System.out.print("Enter Room Type: ");
		roomType = console.next();
		if(roomType.equalsIgnoreCase("na"))
			roomType = oldRoom.getRoomType();

		System.out.print("Enter the rate per night: ");
		ratePerNight = console.nextDouble();
		if(ratePerNight==0)
			ratePerNight = oldRoom.getRatePerNight();
		
		newRoom = new Room();
		newRoom.setRoomId(roomId);
		newRoom.setHotelId(hotelId);
		newRoom.setAvailability(availability);
		newRoom.setRoomNo(roomNo);
		newRoom.setRoomType(roomType);
		newRoom.setRatePerNight(ratePerNight);
		
		try {
			adminService.modifyRoom(newRoom);
			System.out.println("Room modified successfully with hotel ID: " + hotelId);
		}catch(RoomNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteRoom() {
		AdminService adminService = new AdminServiceImpl();

		int roomId, hotelId;

		System.out.println("Enter the room ID and hotel ID of the room to be deleted: ");

		System.out.print("Hotel ID: ");
		hotelId = console.nextInt();

		System.out.print("Room ID: ");
		roomId = console.nextInt();

		try {
			adminService.deleteRoom(roomId);
			System.out.println("Room with ID: " + roomId + " deleted successfully!");
			listHotels();
		} catch (RoomNotFoundException e4) {
			System.out.println(e4.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listHotels(){
		AdminService adminService = new AdminServiceImpl();

		try {
			List<Hotel> result = adminService.listHotels();
			for (Hotel hotel : result) {
				System.out.println(hotel.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println(e2.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listBookings() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

			System.out.print("Hotel ID: ");
			hotelId = console.nextInt();

		try {
			List<Booking> result = adminService.viewBookings(hotelId);
			for (Booking booking : result) {
				System.out.println(booking.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println(e2.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listGuestList() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

		System.out.print("Hotel ID: ");
		hotelId = console.nextInt();

		try {
			List<User> result = adminService.viewGuestList(hotelId);
			for (User user : result) {
				System.out.println(user.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println(e2.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listBookingByDate() {
		AdminService adminService = new AdminServiceImpl();

		java.sql.Date sqlDate;
		java.util.Date utilDate=null;
		String date;

		System.out.print("Booked from date ('dd MM yyyy') : ");
		date = console.next();
		try {
			utilDate = new SimpleDateFormat("dd MM yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sqlDate = new java.sql.Date(utilDate.getTime());

		List<Booking> result = null;
		try {
			result = adminService.bookingByDate(sqlDate);
		} catch (BookingNotFoundException e) {
			System.out.println(e.getMessage());
		}	
		for (Booking booking : result) {
			System.out.println(booking.toString());
		}
	}
}