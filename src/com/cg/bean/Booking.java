package com.cg.bean;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
	private int bookingId;
	private int roomId;
	private int userId;
	private Date bookedFrom;
	private Date bookedTo;
	private int adults;
	private int children;
	private double amount;
	private int hotelId;
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	private LocalDate bookingDate;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getBookedFrom() {
		return bookedFrom;
	}
	public void setBookedFrom(Date bookedFrom2) {
		this.bookedFrom = bookedFrom2;
	}
	public Date getBookedTo() {
		return bookedTo;
	}
	public void setBookedTo(Date bookedTo2) {
		this.bookedTo = bookedTo2;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking ID: " + bookingId + "\t Room ID: " + roomId + "\t Hotel ID: " + hotelId + "\t User ID: " + userId + "\t Booked from date: " + bookedFrom + "\t Booked till date: " + bookedTo + "\t No. of Adults: " + adults + "\t No. of children: " + children + "\t Amount to be paid: " + amount;
	}
	
}
