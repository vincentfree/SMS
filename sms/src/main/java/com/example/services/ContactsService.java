package com.example.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.ContactsEntity;
import com.example.repositories.ContactsRepository;
//twilio
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

@org.springframework.stereotype.Service
public class ContactsService {

	@Autowired
	ContactsRepository cRep;
	
	/**
	 * creating test cases
	 */
	public void createDefaults() {

		List<ContactsEntity> ce = cRep.findAll();
		if (ce.size() == 0) {

			//one
			ContactsEntity one= new ContactsEntity();
			one.setFirstName("Hidetoshi");
			one.setLastName("Dekisugi");
			one.setNumber("+919971792703");

			cRep.save(one);
			one = null;
			System.gc();

			//two
			one= new ContactsEntity();
			one.setFirstName("Nobita");
			one.setLastName("Nobi");
			one.setNumber("+919407554539");

			cRep.save(one);
			one = null;
			System.gc();
			
			//three
			one= new ContactsEntity();
			one.setFirstName("Shizuka");
			one.setLastName("Minamoto");
			one.setNumber("+917097056200");

			cRep.save(one);
			one = null;
			System.gc();

			//four
			one= new ContactsEntity();
			one.setFirstName("Suneo");
			one.setLastName("Honekawa");
			one.setNumber("+918982591416");

			cRep.save(one);
			one = null;
			System.gc();
			
			//five
			one= new ContactsEntity();
			one.setFirstName("Takashi");
			one.setLastName("Goda");
			one.setNumber("3333333333");

			cRep.save(one);
			one = null;
			System.gc();
			
		}
		
	}

	/**
	 * 
	 * @param ACCOUNT_SID
	 * @param AUTH_TOKEN
	 * @param TWILIO_NUMBER
	 * @param TO
	 * @param MESSAGE
	 * @return
	 * 
	 * call to twilio api to send the message
	 */
	public String sendSMS(String ACCOUNT_SID, String AUTH_TOKEN, String TWILIO_NUMBER, String TO, String MESSAGE) {
	    try {
	        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	 
	        // Build a filter for the MessageList
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("Body", MESSAGE));
	        params.add(new BasicNameValuePair("To", TO)); //Add real number here
	        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
	 
	        MessageFactory messageFactory = client.getAccount().getMessageFactory();
	        Message message = messageFactory.create(params);
	        System.out.println(message.getSid());
	        return message.getSid();
	    } 
	    catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	        return e.getErrorMessage();
	    }
	}

	/**
	 * 
	 * @return
	 * 
	 * function to generate OTP
	 */
	public String getOtp() {
		int length = 6;
		String charset = "0123456789";
		String retVal = "";
		for (int i = 0, n = charset.length(); i < length; i++) {
			retVal += charset.charAt((int) Math.floor(Math.random() * n));
    	}
    	return ("Hi. Your OTP is: "+retVal);
	}
	
}
