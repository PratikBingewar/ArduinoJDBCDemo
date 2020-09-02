package com.java.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

	private static PreparedStatement stmt;
	public static Connection conn ;

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arduino","Pratik-PC","pratik007");
			//conn = DriverManager.getConnection(MetadataDetails.CONN_URL, MetadataDetails.USERNAME, MetadataDetails.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean getADDStatement(String ds, String as) {
		try {
			System.out.println(ds+"-"+as);
			String sql = "INSERT INTO ReedSwitch(DigitalStatus,AnalogStatus) VALUES(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ds);
			stmt.setString(2, as);
			
			int i = stmt.executeUpdate();
			if(i != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
