package com.ab.jdbcsample.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySqlConnection {
	private static Connection CONNECTION;
	private static String URL = "jdbc:mysql://localhost/bookshop";
	private static String USERNAME = "root";
	private static String PASSWORD = "";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//
	// Properties prop = new Properties();
	// ClassLoader classLoader = Thread.currentThread()
	// .getContextClassLoader();
	// try {
	// prop.load(classLoader.getResourceAsStream("database.properties"));
	// } catch (IOException e) {
	// System.out.println("Culdn't download " + "database properties.");
	// }
	// URL = prop.getProperty("jdbc.url");
	// USERNAME = prop.getProperty("jdbc.username");
	// PASSWORD = prop.getProperty("jdbc.password");
	// }

	private MySqlConnection() {

	}

	public static Connection getConnection() {
		try {
			if (CONNECTION == null || CONNECTION.isClosed()) {
				CONNECTION = DriverManager.getConnection(URL, USERNAME,
						PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CONNECTION;
	}
}
