package edu.northeastern.cs5200;
import java.sql.*;

public class Connection {
	private static final String DRIVER =  "com.mysql.jdbc.Driver"; 
	private static final String URL = "jdbc:mysql:// cs5200-fall2019-niu.ctazkosnvcmg.us-east-2.rds.amazonaws.com"
			+ "/cs5200_Assignment3_jdbc";
	private static final String USER= "ZehuaNiu";
	private static final String PASSWORD = "Cc710613!";
	public static java.sql.Connection dbConnection = null;
	
	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException { 
		Class.forName(DRIVER); 
		if(dbConnection == null)
		{
			dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
			return dbConnection;
		}
		else return dbConnection;
	}
	
	public static void closeConnection(java.sql.Connection conn) { 
		try {
			conn.close();
			} catch (SQLException e) {// TODO Auto-generated catch block 
				e.printStackTrace();
				} 
		}
	
	}
	
	

