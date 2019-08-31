package com.schd.uxfulinterview.contactsapp.controller;

import com.schd.uxfulinterview.contactsapp.models.Contacts;
import com.schd.uxfulinterview.contactsapp.repositories.ContactsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
@RequestMapping("/contacts")
public class ViewController
{

    @Autowired
    private ContactsRepository repository;

    @GetMapping("")
    public String redirect(){
        return "redirect:/contacts/";
    }

    @GetMapping("/")
    public String listContacts(Model model){
        List<Contacts> listOfContactsByRest;
        List<Contacts> listOfContactsByRepo;

        // Two methods of getting the correct list
        // 1) Consuming the REST API result for providing a List<> instead of a single 'Contacts' object
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Contacts>> response = restTemplate.exchange(
                ContactsController.REST_SERVICE_URI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Contacts>>() {});
        listOfContactsByRest = response.getBody();
        Collections.sort(listOfContactsByRest);

        // 2) Directly calling the repository, only usable internally
        // Here we return all of the Contact elements of the repository, Sorted by the name field
        listOfContactsByRepo = repository.findAll(Sort.by(Sort.DEFAULT_DIRECTION.ASC, "name"));


        model.addAttribute("contacts", listOfContactsByRest);
        return "list-contacts";
    }

    @GetMapping("/addcontact")
    public String addContact(Model model){
        model.addAttribute("contacts", new Contacts());
        return "add-contact";
    }

    @RequestMapping(value = "/addcontact", method = RequestMethod.POST)
    public String addContact(@ModelAttribute(value = "contacts") Contacts contact){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        // If we don't set the Id, it will be null and resttemplate exchange won't work, as Entity is <Contacts>
        contact.set_id(ObjectId.get());

        HttpEntity<Contacts> entity = new HttpEntity<>(contact, header);

        if(contact.getName() .isEmpty() || contact.getPhone().isEmpty())
            System.out.println("Contact not saved, fields can't be empty!");
        else
            //repository.save(contact);
           // restTemplate.put(ContactsController.REST_SERVICE_URI, entity);
            restTemplate.exchange(ContactsController.REST_SERVICE_URI, HttpMethod.POST, entity, Contacts.class);
        return "redirect:/contacts/";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") String id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(ContactsController.REST_SERVICE_URI + "/" + id);

        return "redirect:/contacts";
    }

    @RequestMapping(value = "editContact/{id}", method = RequestMethod.GET)
    public String editContact(@PathVariable("id") String id, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("_id", id);

        Contacts contact = restTemplate.getForObject(ContactsController.REST_SERVICE_URI + "/" + id, Contacts.class, params);
        model.addAttribute("contact", contact);
        return "edit-contact";
    }

    @RequestMapping(value = "/editContact/{id}", method = RequestMethod.POST)
    public String editContact(@PathVariable("id") String id,
                              @ModelAttribute("contact")Contacts contact){

        // I wasn't able to return the ObjectId as part of the Contacts modelattribute,
        // so I needed to set it again for the new object
        contact.set_id(new ObjectId(id));
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(ContactsController.REST_SERVICE_URI + "/" + id, contact);
        return "redirect:/contacts";
    }
}
