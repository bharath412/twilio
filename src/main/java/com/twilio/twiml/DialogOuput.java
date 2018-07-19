package com.twilio.twiml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.srishti.dao.ResturantDBQuery;
import com.srishti.utility.CheckNull;
import com.srishtibiz.model.OrderEntity;
import com.srishtibiz.model.OrderInformationEntity;

public class DialogOuput {

	private static final String targetURL = "https://api.dialogflow.com/v1/query?v=20150910";
	String message = null;

	public String queryinput(String selectedOption, String sessionId)
			throws JsonParseException, JsonMappingException, IOException, ParseException, JSONException, SQLException {
		{

			System.out.println("query from user " + selectedOption + "query from user " + sessionId);

			List<OrderInformationEntity> testSet = new ArrayList<OrderInformationEntity>();
			List<OrderEntity> testSet1 = new ArrayList<OrderEntity>();
			try {
				System.out.println(" step 1");
				String query = null;
				String sessionId1=null;
				URL targetUrl = new URL(targetURL);

				System.out.println(" step 2");
				HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setRequestMethod("POST");

				httpConnection.setRequestProperty("Content-Type", "application/json");
				httpConnection.setRequestProperty("Authorization", "Bearer 60f688eff6f04933ad8993839ef0b745");

				System.out.println(" step 3");
				String input1 = "{\'lang\':\'en\',\'query\':\'";
				String input2 = selectedOption + "',";
				String input3 = "\'sessionId\':\'" + sessionId + "\',";
				String input4 = "'timezone\':\'America/New_York\'}";

				System.out.println(" step 4");
				String input = input1 + input2 + input3 + input4;
				System.out.println("Dilogflow query" + input + ":");
				System.out.println(" step 5 input " + input);
				OutputStream outputStream = httpConnection.getOutputStream();
				outputStream.write(input.getBytes());
				outputStream.flush();

				System.out.println(" step 5");
				/*
				 * System.out.println(input2); Object obj1 = new
				 * JSONParser().parse(selectedOption); // typecasting obj to
				 * JSONObject System.out.println(" step 6"); JSONObject jo1 =
				 * (JSONObject) obj1; query = (String) jo1.get("query");
				 * System.out.println(" step 7");
				 * System.out.println(query.toString());
				 */

				if (httpConnection.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
				}

				BufferedReader responseBuffer = new BufferedReader(
						new InputStreamReader((httpConnection.getInputStream())));

				String output = "";
				String abc = "";
				StringBuffer s = new StringBuffer();
				// System.out.println("Output from Server:\n");
				while ((output = responseBuffer.readLine()) != null) {

					s.append(output);

					System.out.println(output.toString());
				}

				boolean confirmationCheck = false;

				Object obj = new JSONParser().parse(s.toString());
				// typecasting obj to JSONObject
				JSONObject jo = (JSONObject) obj;

				 System.out.println(" \n S "+s +"\n");
				// String confirmCheck = "\"confirmation\": \"Yes\"";
				String confirmCheck = "elivery";
				if (s != null && s.toString().indexOf(confirmCheck) > -1)
					confirmationCheck = true;
				//sessionId = (String) jo.get("sessionId");
				String id = (String) jo.get("id");
				String stampstamp = (String) jo.get("timestamp");
				String languag = (String) jo.get("lang");

				// System.out.println(id);
				// System.out.println(stampstamp);
				// System.out.println(languag);
				// if (query.equals("Yes")) {
				System.out.println(" confirmationCheck " + confirmationCheck);
				if (confirmationCheck) {
					Map address = ((Map) jo.get("result"));
					Iterator<Map.Entry> itr1 = address.entrySet().iterator();
					Iterator<Map.Entry> itr66 = null;
					Iterator<Map.Entry> itr77 = null;
					Iterator<Map.Entry> itr88 = null;
					Iterator<Map.Entry> itr99 = null;
					Iterator<Map.Entry> itr33 = null;
					Iterator<Map.Entry> itr44 = null;
					while (itr1.hasNext()) {
						Map.Entry pair = itr1.next();
						// System.out.println(pair.getKey() + " : " +
						// pair.getValue());
						String strKey = pair.getKey().toString();
						if (strKey.equals("source")) {
							String source = pair.getValue().toString();
							// System.out.println(" source "+source);
						}
						if (strKey.equals("parameters")) {
							String newaddress = pair.getValue().toString();
							// System.out.println(newaddress);
							JSONObject firspara = (JSONObject) pair.getValue();
							String order_type = (String) firspara.get("order_type");
							// System.out.println(" order_type " + order_type);

						}
						if (strKey.equals("contexts")) {

							JSONArray contexts = (JSONArray) pair.getValue();
							itr66 = contexts.iterator();
							itr77 = ((Map) itr66.next()).entrySet().iterator();
							while (itr77.hasNext()) {
								Map.Entry pair3 = itr77.next();
								// System.out.println(" in while Key is:" +
								// pair3.getKey() + " value 77 is : " +
								// pair3.getValue());
								String jasonKey = pair3.getKey().toString();
								if (jasonKey.equals("parameters")) {
									JSONObject newjason1 = (JSONObject) pair3.getValue();
									// System.out.println(" jason object
									// newjason1 "
									// +
									// newjason1);

									OrderInformationEntity order = new OrderInformationEntity();

									String phonenumber2 = (String) newjason1.get("phone-number");
									// String item = (String)
									// newjason1.get("item");
									String order_type = (String) newjason1.get("order_type");
									// String number = (String)
									// newjason1.get("number");
									String given_name = (String) newjason1.get("given-name");
									String registered_restaurant = (String) newjason1.get("registered_restaurant");
									CheckNull verifi=new CheckNull();
									String restaurentOrderNo=verifi.generateresID(registered_restaurant);
									
									System.out.println("RestaurentId :"+restaurentOrderNo+":");
									
									String confirmation = (String) newjason1.get("confirmation");
									// System.out.println("" + given_name);
									// System.out.println("" +
									// registered_restaurant);
									// System.out.println("" + number);
									// System.out.println("" + order_type);
									// System.out.println("" + item);
									// System.out.println("" + phonenumber2);
									order.setOrderNumber(restaurentOrderNo);
									order.setOrderStatus(confirmation);
									order.setCustomerName(given_name);
									order.setRestarentName(registered_restaurant);
									order.setOrder_session_id(sessionId);
									// order.setMenuItemName(item);
									// int result = Integer.parseInt(number);
									// order.setQuantity(result);
									order.setCustomerPhone(phonenumber2);
									testSet.add(order);
									// getting phoneNumbers
									JSONArray newja100 = (JSONArray) newjason1.get("item");
									JSONArray newja101 = (JSONArray) newjason1.get("number");
									// itr33 = newja100.iterator();
									System.out.println(" itr33 after " + itr33);
									// itr44 = ((Map)
									// itr33.next()).entrySet().iterator();
									for (int i = 0; i < newja100.size(); i++) {
										OrderEntity order1 = new OrderEntity();
										Object objectInArray = newja100.get(i);
										System.out.println(objectInArray + "//////////////");
										String kk = (String) newja100.get(i);
										long x = (Long) newja101.get(i);

										// System.out.println( " kk "+kk);
										order1.setMenuItemName(kk);
										order1.setQuantity(x);
										testSet1.add(order1);
									}

								}
								if (strKey.equals("metadata")) {

									String newmetadata = pair.getValue().toString();
									JSONObject firspara = (JSONObject) pair.getValue();
									// String intentName = (String)
									// firspara.get("intentName");
									// System.out.println(intentName+":::::::::::::");
								}
								if (strKey.equals("fulfillment")) {

									// System.out.println(" intentName 3");
								}
							}
							for (OrderInformationEntity orderinfo : testSet) {
								System.out.println(orderinfo.getCustomerName());
								System.out.println(orderinfo.getCustomerPhone());
								// System.out.println(orderinfo.getQuantity());
								// System.out.println(orderinfo.getMenuItemName());
								System.out.println(orderinfo.getRestarentName());
								System.out.println(orderinfo.getOrderStatus());

							}

						}
					}

					ResturantDBQuery db = new ResturantDBQuery();
					message = db.orderINFO2(testSet, testSet1,sessionId);
					System.out.println(message);
				}

				httpConnection.disconnect();
			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		return "sucess";
	}
}
