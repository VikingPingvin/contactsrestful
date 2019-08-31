package com.schd.uxfulinterview.contactsapp.controller;

import com.schd.uxfulinterview.contactsapp.models.Contacts;
import com.schd.uxfulinterview.contactsapp.repositories.ContactsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts/rest")
public class ContactsController {

    @Autowired
    private ContactsRepository repository;

    public static final String REST_SERVICE_URI = "http://localhost:8080/contacts/rest/";


    // REST API

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Contacts> getAllContacts(){
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Contacts getContactById(@PathVariable("id") ObjectId id){
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void modifyContactById(@PathVariable("id") ObjectId id,
                                  @RequestBody Contacts contact){
        contact.set_id(id);
        repository.save(contact);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void createContact(@RequestBody Contacts contact){
        //Check if name already exist

        if(repository.findByName(contact.getName()) == null) {
            contact.set_id(ObjectId.get());
            repository.save(contact);
            System.out.println("Saved contact to repo: " + contact.toString());
        } else
            System.out.println("Duplicate names not allowed.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable("id") ObjectId id){
        repository.deleteById(id.toHexString());
    }
}
