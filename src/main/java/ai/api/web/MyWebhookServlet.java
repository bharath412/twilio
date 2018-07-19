package ai.api.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;

import ai.api.model.Fulfillment;

@WebServlet("/webhook")
public class MyWebhookServlet extends AIWebhookServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String skills;
  protected void doWebhook(AIWebhookRequest input, Fulfillment output) {

	  String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://talentdb.srishtibiz.com:55433/serviceportal2?sslmode=disable";

	
		String USER = "postgres";
		String PASS = "tufghbE#5fgds";
		try {

			Class.forName(JDBC_DRIVER);

		

			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt3 = conn.createStatement();
			String sql2 = "SELECT employ_id,employ_skills FROM candidate.emp where employ_id=1";
			ResultSet rs3 = stmt3.executeQuery(sql2);
	
System.out.println("before while");
			while (rs3.next()) {
				System.out.println("after while looop");	
             skills = rs3.getString("employ_skills");
				
System.out.println(skills+"11111111111111111111111111111111111");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
			
		}
		System.out.println("testttttttttttttttttttttttttttttt input" + input);
		System.out.println("testttttttttttttttttttttttttttttt output" + output);
		output.setSpeech(" "+skills+" " + input.getResult().getFulfillment().getSpeech());

	}

}