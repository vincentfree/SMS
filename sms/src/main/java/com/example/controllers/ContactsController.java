package com.example.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.classes.OtpObject;
import com.example.classes.SmsObject;
import com.example.entities.ContactsEntity;
import com.example.repositories.ContactsRepository;
import com.example.services.ContactsService;
import com.example.services.SentMessagesService;

@RestController
public class ContactsController {

	@Autowired
	ContactsService cServ;
	
	@Autowired
	ContactsRepository cRep;
	
	@Autowired
	SentMessagesService smServ;
	
	/**
	 * pre defining twilio account details
	 */
    public static final String ACCOUNT_SID = "AC793e8ed7fb1329940ae82217b0dc5005";
    public static final String AUTH_TOKEN = "1db4518dbbd1d771c5338d87468f1303";
    public static final String TWILIO_NUMBER = "+17146772124";
    public static String otpString = "";
	
	/**
	 * creating test cases
	 */
	@Autowired 
	public void createDefaults(){
		cServ.createDefaults();	
	}
	
	/**
	 * 
	 * @return
	 * 
	 * returns all the contacts for contacts.html
	 */
	@RequestMapping("getAllContacts")
	public List<ContactsEntity> getAllContacts(){
		return cRep.findAll();
	}
	
	/**
	 * 
	 * @return
	 * 
	 * generates and stores OTP, since It only sends OTP for reference,
	 * Otp can not be tampered at client side as it stores here in variable otpString.
	 */
	@RequestMapping("getOtp")
	public OtpObject getOtp(){
		OtpObject oo = new OtpObject();
		oo.setOtp(cServ.getOtp());
		otpString = oo.getOtp();
		return oo;
	}
	
	/**
	 * 
	 * @param st
	 * @return
	 * 
	 * this function receives smsObject (helper class)
	 * parses it into desired format
	 * and calls twilio API to send the message.
	 * now catches the response returned by sendSMS function of twilio, 
	 * and returns it to client-side as boolean value.
	 */
	@RequestMapping("sendMessage")
	public boolean sendMessage(@RequestBody SmsObject st){
		String TO = st.getTo();
		String MESSAGE = st.getDate() + " | " + otpString + "." +st.getMessage();
		String TONUMBER = st.getToNumber();
		
		String response = cServ.sendSMS(ACCOUNT_SID, AUTH_TOKEN, TWILIO_NUMBER, TONUMBER, MESSAGE);
		smServ.saveSms(TO, TONUMBER, MESSAGE);
		
		boolean flag = false;
		if(response.length() <= 35)
			flag = true;
		return flag;
	}
	
}
