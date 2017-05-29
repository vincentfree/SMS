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
public class SmsObject {

	private String to;
	private String toNumber;
	private String message;
	private String date;
	
	//getters and setters
	public String getTo() {
		return to;
	}
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
