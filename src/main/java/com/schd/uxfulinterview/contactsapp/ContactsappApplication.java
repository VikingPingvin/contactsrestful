package com.schd.uxfulinterview.contactsapp;

import com.schd.uxfulinterview.contactsapp.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsappApplication {

	@Autowired
	private ContactsRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ContactsappApplication.class, args);
	}

}
