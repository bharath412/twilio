package com.twilio.twiml;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Redirect;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.TwiMLException;
import java.util.Arrays;
import java.util.Date;


public class WelcomeServlet extends WebAppServlet {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

	Say say = new Say
	        .Builder("Hi , You called to Restaurant Order Management, how can i help for you ?").voice(Say.Voice.WOMAN)
            .language(Language.EN_US).build();
	Date date = new Date();
	String str = String.format("Current Date/Time : %tc", date );

    System.out.printf("started here user need to query step1"+ str);
    
    
    
	Gather gather = new Gather.Builder().inputs(Gather.Input.SPEECH)
	            .action("/menu/show").say(say).build();
	        
    VoiceResponse voiceResponse = new VoiceResponse.Builder().gather(gather).build();

    respondTwiML(response, voiceResponse);
  }
}