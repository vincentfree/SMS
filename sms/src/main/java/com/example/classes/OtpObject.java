package com.example.classes;

/**
 * 
 * @author root
 *
 *	helper classes,
 *	since front-end to backend or back-end to front end communications must be done as objects
 *	or else, there would be JSON error.
 *	so these helper classes resemble to the objects that are used in front end. 
 */
public class OtpObject {

	String otp;

	//getter and setter
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}
