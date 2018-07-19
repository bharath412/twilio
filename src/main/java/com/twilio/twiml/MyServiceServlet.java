package com.twilio.twiml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;

import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;

import ai.api.AIServiceException;
import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;

@WebServlet(urlPatterns = {"/extensions/connect"},
    initParams = {
        @WebInitParam(name = AIServiceServlet.PARAM_API_AI_KEY,
        value = "60f688eff6f04933ad8993839ef0b745")
    })
public class MyServiceServlet extends AIServiceServlet {
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
	private static final String targetURL = "https://api.dialogflow.com/v1/query?v=20150910";

  protected void service (HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
    	String selectedOption = request.getParameter("SpeechResult");
    	
      AIResponse aiResponse = request(request.getParameter("SpeechResult"), request.getSession());
      response.setContentType("text/plain");
      
      //response.getWriter().append(aiResponse.getResult().getFulfillment().getSpeech());
      
    //  System.out.println(aiResponse + "<<<<<<-------->>>>>>>" + aiResponse.getResult().getFulfillment().getSpeech());
      System.out.println(aiResponse + " --- test speech" + selectedOption);

		
      DialogOuput jsonoutput = new DialogOuput();
		String queryspeech = jsonoutput.queryinput(selectedOption , aiResponse.getSessionId());
		
		
    /*  URL targetUrl = new URL(targetURL);
		
		HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
	
		httpConnection.setRequestProperty("Content-Type", "application/json");
		httpConnection.setRequestProperty("Authorization", "Bearer 60f688eff6f04933ad8993839ef0b745");
		
		String input1 = "{\'lang\':\'en\',\'query\':\'";
		String input2 = selectedOption + "'," ;
		String input3 ="\'sessionId\':\'"
				+ aiResponse.getSessionId()
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

		
		
		httpConnection.disconnect();*/
		
      
      Say firstPhrase = new Say.Builder(aiResponse.getResult().getFulfillment().getSpeech()).voice(Say.Voice.WOMAN)
              .language(Language.EN_US).build();

		Gather gather = new Gather.Builder().inputs(Gather.Input.SPEECH)
	            .action("/menu/show").say(firstPhrase).build();
	        
		VoiceResponse voiceResponse = new VoiceResponse.Builder().gather(gather)
				.build();

		respondTwiML(response, voiceResponse); 
    } catch (AIServiceException e) {
      e.printStackTrace();
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  protected void respondTwiML(HttpServletResponse response, VoiceResponse twiMLResponse)
	      throws IOException {
	    response.setContentType("text/xml");
	    try {
	      response.getWriter().write(twiMLResponse.toXml());
	    } catch (TwiMLException e) {
	      System.out.println("Unable to create TwiML");
	    }
	  }

	  protected void respondContent(HttpServletResponse response, String content) throws IOException {
	    response.getWriter().write(content);
	  }
}