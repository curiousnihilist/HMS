package com.cg.ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
				loginAdmin();
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

		String password;
		int userId;

		do {
			System.out.print("Enter User ID: ");
			userId = console.nextInt();
		} while (!userService.validateUserId(userId));

		do {
			System.out.print("Enter Password: ");
			password = console.next();
		} while (!userService.validatePassword(password));

		do {
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
					System.exit(0);
				default:
					System.out.println("Please enter correct option");
				}
			}
		} while (!userService.validateLogin(userId, password));
	}

	private static void loginAdmin(){
		AdminService adminService= new AdminServiceImpl();

		String password;
		int userId;
	
		do {
			System.out.print("Enter User ID: ");
			userId = console.nextInt();
		} while(!adminService.validateUserId(userId));

		do {
			System.out.print("Enter Password: ");
			password = console.next();
		} while(!adminService.validatePassword(password));

		do{
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
				case 11: System.exit(0);
				default: System.out.println("Please enter correct option");
				}
			} 
		} while(!adminService.validateLogin(userId, password));
	}

	private static void registerUser() {
		CustomerService userService = new CustomerServiceImpl();

		String userName, password, mobileNo, phone, role, address, email;
		int userId;

		do {
			System.out.print("Enter User Name: ");
			userName = console.next();
		} while (!userService.validateName(userName));

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
		} while (!userService.validatePhone(phone));

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
			System.out.println("Registration unsuccessful!! Try again");
		}

	}

	private static void findHotel(){

		CustomerService userService = new CustomerServiceImpl();

		int flag=0, option = 0;
		double minPrice, maxPrice;
		String city, hotelName;

		System.out.print("Search by:	0-By City	1-By Hotel");
		flag = console.nextInt();

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

		int bookingId, roomId, userId, adults, children, hotelId;
		double amount;
		DateTimeFormatter formatter = null;
		 
		LocalDate bookedFrom, bookedTo;
		String dateFrom, dateTo;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
		do {
			System.out.print("Enter User ID: ");
			userId = console.nextInt();
		} while (!userService.validateUserId(userId));

		do {
			System.out.print("Enter Hotel ID: ");
			hotelId = console.nextInt();
		} while (!userService.validatehotelId(hotelId));

		do {
			System.out.print("Enter Booking ID: ");
			bookingId = console.nextInt();
		} while (!userService.validatebookingId(bookingId));

		do {
			System.out.print("Enter Room Id: ");
			roomId = console.nextInt();
		} while (!userService.validateRoomId(roomId));

		System.out.print("Enter no. of adults (max-2): ");
		adults = console.nextInt();

		System.out.print("Enter no. of children (max-2): ");
		children = console.nextInt();

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

		System.out.println("Enter the amount to be paid: ");
		amount = console.nextDouble();

		Booking b = new Booking();
		b.setBookingId(bookingId);
		b.setUserId(userId);
		b.setHotelId(hotelId);
		b.setRoomId(roomId);
		b.setBookedFrom(bookedFrom);
		b.setBookedTo(bookedTo);
		b.setAmount(amount);
		b.setAdults(adults);
		b.setChildren(children);

		try {
			Booking booking = userService.bookRoom(b);
			System.out.println("Booking successful with Booking ID: " + bookingId);
			viewBookingStatus();
		} catch (HotelNotFoundException e2) {
			System.out.println("Booking unsuccessful!! Try again");
		}
	}

	private static void viewBookingStatus() {

		CustomerService userService = new CustomerServiceImpl();

		int userId;

		System.out.print("Enter User ID: ");
		userId = console.nextInt();

		try {
			List<Booking> result = userService.viewStatus(userId);
			for (Booking booking : result) {
				System.out.println(booking.toString());
			}
		} catch (BookingNotFoundException e3) {
			System.out.println("No bookings found!! Try again");
		}
	}

	private static void addHotel() {

		AdminService adminService = new AdminServiceImpl();

		int hotelId;
		String city, hotelName, address, description, phoneNo1, phoneNo2, rating, email, fax;
		double avgRatePerNight;

		do {
			System.out.print("Enter Hotel Name: ");
			hotelName = console.next();
		} while (!adminService.validateHotelName(hotelName));
		
		do {
			System.out.print("Enter City: ");
			city = console.next();
		} while (!adminService.validateCity(city));

		System.out.print("Enter Address: ");
		address = console.next();

		System.out.print("Add a description: ");
		description = console.next();

		System.out.print("Enter average rate per night: ");
		avgRatePerNight = console.nextDouble();

		do {
			System.out.print("Enter Phone No. 1: ");
			phoneNo1 = console.next();
		} while (!adminService.validatePhoneNo1(phoneNo1));

		do {
			System.out.print("Enter Phone No. 2: ");
			phoneNo2 = console.next();
		} while (!adminService.validatePhoneNo2(phoneNo2));

		do {
			System.out.print("Enter Email ID: ");
			email = console.next();
		} while (!adminService.validateEmail(email));

		do {
			System.out.print("Enter fax no.: ");
			fax = console.next();
		} while (!adminService.validateFax(fax));

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
		}

	}

	private static void modifyHotel() {
		AdminService adminService = new AdminServiceImpl();

	}

	private static void deleteHotel() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

		do {
			System.out.println("Enter hotel ID of the hotel to be deleted: ");
			hotelId = console.nextInt();
		} while (!adminService.validateHotelId(hotelId));

		try {
			adminService.deleteHotel(hotelId);
			System.out.println("Hotel with ID: " + hotelId + " deleted successfully!");
			listHotels();
		} catch (HotelNotFoundException e2) {
			System.out.println("Hotel could not be deleted!! Try again");
		}
	}

	private static void addRoom() {

		AdminService adminService = new AdminServiceImpl();

		int roomId, hotelId;
		String roomType, roomNo, availability;
		double ratePerNight;

		do {
			System.out.print("Enter Hotel ID: ");
			hotelId = console.nextInt();
		} while (!adminService.validateHotelId(hotelId));

		do {
			System.out.println("Enter Availability ('y/Y' for 'yes', 'n/N' for 'no'): ");
			availability = console.next();
		} while (!adminService.validateAvailability(availability));

		System.out.print("Enter Room No.: ");
		roomNo = console.next();

		System.out.print("Enter Room Type: ");
		roomType = console.next();

		System.out.print("Enter the rate per night: ");
		ratePerNight = console.nextDouble();

		Room r = new Room();
		r.setRoomId(roomId);
		r.setHotelId(hotelId);
		r.setRoomNo(roomNo);
		r.setRoomType(roomType);
		r.setRatePerNight(ratePerNight);

		try {
			roomId = adminService.addRoom(r);
			System.out.println("New room added successfully with Room ID: " + roomId);
		} catch (HotelNotFoundException e2) {
			System.out.println("Room could not be added!! Try again");
		}
	}

	private static void modifyRoom() {
		AdminService adminService = new AdminServiceImpl();

	}

	private static void deleteRoom() {
		AdminService adminService = new AdminServiceImpl();

		int roomId, hotelId;

		System.out.println("Enter the room ID and hotel ID of the room to be deleted: ");

		do {
			System.out.print("Hotel ID: ");
			hotelId = console.nextInt();
		} while (!adminService.validateHotelId(hotelId));

		do {
			System.out.print("Room ID: ");
			roomId = console.nextInt();
		} while (!adminService.validateRoomId(roomId));

		try {
			adminService.deleteRoom(roomId);
			System.out.println("Room with ID: " + roomId + " deleted successfully!");
			listHotels();
		} catch (RoomNotFoundException e4) {
			System.out.println("Room could not be deleted!! Try again");
		}
	}

	private static void listHotels() throws Exception {
		AdminService adminService = new AdminServiceImpl();

		try {
			List<Hotel> result = adminService.listHotels();
			for (Hotel hotel : result) {
				System.out.println(hotel.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println("No hotels found!! Try again");
		}
	}

	private static void listBookings() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

		do {
			System.out.print("Hotel ID: ");
			hotelId = console.nextInt();
		} while (!adminService.validateHotelId(hotelId));

		try {
			List<Booking> result = adminService.viewBookings(hotelId);
			for (Booking booking : result) {
				System.out.println(booking.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println("No bookings found!! Try again");
		}
	}

	private static void listGuestList() {
		AdminService adminService = new AdminServiceImpl();

		int hotelId;

		do {
			System.out.print("Hotel ID: ");
			hotelId = console.nextInt();
		} while (!adminService.validateHotelId(hotelId));

		try {
			List<User> result = adminService.viewGuestList(hotelId);
			for (User user : result) {
				System.out.println(user.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println("Guest list could not be found!! Try again");
		}
	}

	private static void listBookingByDate() {
		AdminService adminService = new AdminServiceImpl();

		Date bookedFrom, bookedTo;
		String dateFrom, dateTo;
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

		do {
			System.out.print("Booked from date ('dd mm yyyy') : ");
			dateFrom = console.next();
			bookedFrom = myFormat.parse(dateFrom);
		} while (!adminService.validateDate(bookedFrom));

		do {
			System.out.print("Booked till date ('dd mm yyyy') : ");
			dateTo = console.next();
			bookedTo = myFormat.parse(dateTo);
		} while (!adminService.validateDate(bookedTo));

		try {
			List<Booking> result = adminService.viewBookingByDate(bookedFrom, bookedTo);
			for (Booking booking : result) {
				System.out.println(booking.toString());
			}
		} catch (HotelNotFoundException e2) {
			System.out.println("No bookings found!! Try again");
		}
	}
}