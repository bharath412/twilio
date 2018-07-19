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

@WebServlet(urlPatterns = { "/menu/show" }, initParams = {
		@WebInitParam(name = AIServiceServlet.PARAM_API_AI_KEY, value = "60f688eff6f04933ad8993839ef0b745") })
public class ShowServlet extends AIServiceServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String targetURL = "https://api.dialogflow.com/v1/query?v=20150910";

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

			AIResponse aiResponse = request(request.getParameter("SpeechResult"), request.getSession());
			response.setContentType("text/plain");

			System.out.println("selectedOption:" + selectedOption + ":");

			DialogOuput jsonoutput = new DialogOuput();
			
			try {
				String queryspeech = jsonoutput.queryinput(selectedOption, aiResponse.getSessionId());
				System.out.println(queryspeech);
			} catch (ParseException | JSONException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(aiResponse + " --- test speech " + selectedOption);

			Say firstPhrase = new Say.Builder(aiResponse.getResult().getFulfillment().getSpeech())
					.voice(Say.Voice.WOMAN)

					.language(Language.EN_US).build();
			System.out.printf("getting response from request and passing to twilio step3" + str);
			Gather gather = new Gather.Builder().say(firstPhrase).action("/menu/show").inputs(Gather.Input.SPEECH)
					.build();
			
			

			VoiceResponse voiceResponse = new VoiceResponse.Builder().gather(gather).build();

			respondTwiML(response, voiceResponse);

		} catch (AIServiceException e) {
			e.printStackTrace();

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