package com.srishti.utility;


import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {

		Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:" + System.getenv("postgresql://ec2-54-83-59-120.compute-1.amazonaws.com:5432/d9j86bu1oio171?sslmode=require");
		String username = System.getenv("pnyftdluklkxan");
		String password = System.getenv("b8f1249b7c0cee7d2c297ef114517cc8cd0e99f1b8c80545deccbb8e447a10cf");
		return DriverManager.getConnection(dbUrl, username, password);
	}

	
}
