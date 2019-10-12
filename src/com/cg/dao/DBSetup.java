package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
	private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String username ="Akash";
	private static String password ="oracle";
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = (Connection) DriverManager.getConnection(url,username,password);
			return cn;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
			return null;
		} catch (SQLException e) {
			return null;
		}
		
	}
}
