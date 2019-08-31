package com.schd.uxfulinterview.contactsapp.repositories;

import com.schd.uxfulinterview.contactsapp.models.Contacts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactsRepository extends MongoRepository<Contacts, String> {


    Contacts findBy_id(ObjectId _id);

    Contacts findByName(String name);

    Contacts findByPhone(String number);

    Contacts findByEmail(String email);


}
