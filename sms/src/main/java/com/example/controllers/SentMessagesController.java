package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.SentMessagesEntity;
import com.example.repositories.SentMessagesRepository;

@RestController
public class SentMessagesController {

	@Autowired
	SentMessagesRepository smRep;
	
	/**
	 * 
	 * @return
	 * 
	 * returns track of all the sent messages data from database to front-end.
	 */
	@RequestMapping("getSentMessages")
	public List<SentMessagesEntity> getSentMessages(){
		return smRep.findAll();
	}
	
}
