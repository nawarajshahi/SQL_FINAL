package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {
	static Scanner input = new Scanner(System.in);
	private static String database = "volunteers";
	private final static String url = "jdbc:mysql://localhost:3306/" + database;
	private final static String userName = "root";

<<<<<<< HEAD
	
=======

>>>>>>> dev
	private static Connection connection;
	private static DBConnection instance;
	
	private DBConnection(Connection connection) {
		DBConnection.connection = connection;
	}
	
	public static Connection getConnection() {
		if(instance == null) {
			try {
				System.out.println("Please enter your database password:");
				String pass = input.nextLine();
				connection = DriverManager.getConnection(url, userName, pass);
				instance = new DBConnection(connection);
				System.out.println("Successfully connected.");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return DBConnection.connection;
	}

}


