package com.cg.dao;
import java.sql.*;

public class HotelDaoImpl {
	private static String url ="";
	private static String username ="";
	private static String password ="";
	
	public static Connection getConnection() throws Exception{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = (Connection) DriverManager.getConnection(url,username,password);
			return conn;
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Database not found");
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
