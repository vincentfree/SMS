package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SentMessagesEntity;
import com.example.repositories.SentMessagesRepository;

@Service
public class SentMessagesService {

	@Autowired
	SentMessagesRepository smRep;
	
	/**
	 * 
	 * @param to
	 * @param toNumber
	 * @param message
	 * 
	 * to keep track of sent messages, called from SentMessagesController
	 */
	public void saveSms(String to, String toNumber, String message) {

		SentMessagesEntity sme = new SentMessagesEntity();
		sme.setMessage(message);
		sme.setReceiver(to);
		sme.setToNumber(toNumber);
		
		smRep.save(sme);
		
	}

}
