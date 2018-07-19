package com.twilio.twiml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;

import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;

import ai.api.AIServiceException;
import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;

@WebServlet("/example/show")

public class ShowServlet2 extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Date date = new Date();
			String str = String.format("Current Date/Time : %tc", date);

			System.out.printf("getting request to twilio and passing in dialog flow step2" + str);

			String selectedOption = request.getParameter("SpeechResult");

	

			Say firstPhrase = new Say.Builder("Hey Bharath I am responding fast , Speak something i will back again")
					.voice(Say.Voice.WOMAN)

					.language(Language.EN_US).build();
			System.out.printf("getting response from request and passing to twilio step3" + str);
			Gather gather = new Gather.Builder().say(firstPhrase).action("/example/show").inputs(Gather.Input.SPEECH)
					.build();

			VoiceResponse voiceResponse = new VoiceResponse.Builder().gather(gather).build();

			respondTwiML(response, voiceResponse);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	protected void respondTwiML(HttpServletResponse response, VoiceResponse twiMLResponse) throws IOException {
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