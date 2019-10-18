package com.cg.exception;

public class RoomNotFoundException extends Exception{

	public RoomNotFoundException() {
		super();
	}

	public RoomNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoomNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomNotFoundException(String message) {
		super(message);
	}

	public RoomNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
