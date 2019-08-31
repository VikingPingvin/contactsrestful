package com.schd.uxfulinterview.contactsapp.repositories;

import com.schd.uxfulinterview.contactsapp.models.Contacts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ContactsRepository extends MongoRepository<Contacts, String> {


    Contacts findBy_id(ObjectId _id);

    Contacts findByName(String name);

    Contacts findByPhone(String number);

    Contacts findByEmail(String email);


}
