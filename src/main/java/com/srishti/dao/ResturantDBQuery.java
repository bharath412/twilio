package com.srishti.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.srishti.utility.DBConnection;
import com.srishtibiz.model.OrderEntity;
import com.srishtibiz.model.OrderInformationEntity;

public class ResturantDBQuery {
	Statement stmt = null;
	Connection c = null;
	DBConnection db = new DBConnection();

	void resturantDBQuery() {

	}

	public String orderINFO2(List<OrderInformationEntity> testSet, List<OrderEntity> testSet1, String sessionId)
			throws SQLException {
		String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://ec2-54-83-59-120.compute-1.amazonaws.com:5432/d9j86bu1oio171?sslmode=require";

		String USER = "pnyftdluklkxan";
		String PASS = "b8f1249b7c0cee7d2c297ef114517cc8cd0e99f1b8c80545deccbb8e447a10cf";
		String message = "Record is Not inserted into Order table and Record is inserted into Not Orderinfo table!";
		String message1 = null;
		ResultSet rs = null;
		String orderID=null;
		List<OrderInformationEntity> listorder = new ArrayList<OrderInformationEntity>();
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("sessionIdsessionIdsessionId" + "123");
			String sqlcheck = "Select count(*)from restaurant.orderinformation where order_session_id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcheck);
			preparedStatement.setString(1, sessionId);
			rs = preparedStatement.executeQuery();
			rs.next();
			int rowCount = rs.getInt(1);
			System.out.println("rowCount :" + rowCount + ":");
			if (rowCount == 0) {
				String insertTableSQL = "INSERT INTO restaurant.orderinformation(customer_name,ccustomer_phone,resturant_id,order_session_id,order_number)VALUES (?,?,?,?,?)";

				PreparedStatement preparedStmt = conn.prepareStatement(insertTableSQL);
				for (OrderInformationEntity orderinfo : testSet) {
					preparedStmt.setString(1, orderinfo.getCustomerName());

					preparedStmt.setString(2, orderinfo.getCustomerPhone());
					preparedStmt.setInt(3, 11);
					preparedStmt.setString(4, orderinfo.getOrder_session_id());
					preparedStmt.setString(5, orderinfo.getOrderNumber());
					orderID=orderinfo.getOrderNumber();
					preparedStmt.execute();
					message = "Record is inserted into Orderinfo table!";
				}
				// System.out.println("Record is inserted into DBUSER table!");
				String insertTableSQL1 = "INSERT INTO restaurant.orderitems (menu_item_name,quantity)VALUES (?, ?)";
				PreparedStatement preparedStmt1 = conn.prepareStatement(insertTableSQL1);
				for (OrderEntity orderinfo1 : testSet1) {
					preparedStmt1.setString(1, orderinfo1.getMenuItemName());

					preparedStmt1.setLong(2, orderinfo1.getQuantity());

					preparedStmt1.execute();
					message1 = "Record is inserted into Order table!";
				}
				
			}

		} catch (Exception e) {
			System.out.println("    :" + e);
		}

		return message + "   " + message1;
	}

}
