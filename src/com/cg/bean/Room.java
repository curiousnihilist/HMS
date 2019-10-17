package com.cg.bean;

public class Room {
	public int hotelId;
	public int roomId;
	public String roomNo;
	public String roomType;
	public double ratePerNight;
	public int availability;	// please validate user can enter "yes","no","y","n",1,0
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getRatePerNight() {
		return ratePerNight;
	}
	public void setRatePerNight(double ratePerNight) {
		this.ratePerNight = ratePerNight;
	}
	public int isAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	public Room(int hotelId, String roomNo, String roomType, double ratePerNight, int availability) {
		super();
		this.hotelId = hotelId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.ratePerNight = ratePerNight;
		this.availability = availability;
	}
	
	

}
