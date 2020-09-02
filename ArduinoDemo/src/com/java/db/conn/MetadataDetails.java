package com.java.db.conn;

import java.util.Properties;

public class MetadataDetails {

	protected static String HOST ;
	protected static Integer PORT;
	protected static String DRIVER;
	protected static String DBNAME;
	public static String CONN_URL;
	public static String USERNAME;
	public static String PASSWORD;

	public static void initProperties(Properties prop) {
		HOST = prop.getProperty("HOST");
		PORT = Integer.parseInt(prop.getProperty("PORT"));
		DRIVER = prop.getProperty("DRIVER");
		DBNAME = prop.getProperty("DBNAME");
		USERNAME = prop.getProperty("USERNAME");
		PASSWORD = prop.getProperty("PASSWORD");
		String DBType = prop.getProperty("DBTYPE");
		if(DBType.equalsIgnoreCase("mysql"))
			CONN_URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DBNAME;
	}
	public static void main(String[] args) {
		System.out.println(MetadataDetails.CONN_URL);
	}
}
