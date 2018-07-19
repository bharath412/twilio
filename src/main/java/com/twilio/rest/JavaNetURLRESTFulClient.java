package com.twilio.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;

public class JavaNetURLRESTFulClient {
	
	private static final String targetURL = "https://api.dialogflow.com/v1/query?v=20150910";
	
	public static void main(String[] args) {
		
		
		 
		try {
			 
			URL targetUrl = new URL(targetURL);
			
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
		
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Authorization", "Bearer 60f688eff6f04933ad8993839ef0b745");
	 

					String input1 = "{\'lang\':\'en\',\'query\':\'";
					String input2 = "order" + "'," ;
					String input3 ="\'sessionId\':\'"
							+ "e6e91952-0bb9-4909-b7d9-ac115a24468e"
							+ "\',";
					String input4="'timezone\':\'America/New_York\'}";
							
					
			 
					String input = input1 + input2 + input3 + input4;
			
					
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();
	 
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ httpConnection.getResponseCode());
			}
	 
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
					(httpConnection.getInputStream())));
	 
			String output;
			System.out.println("Output from Server:\n");
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println(output);
			}
	 
			httpConnection.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		 }
	 
		}
	 
}
