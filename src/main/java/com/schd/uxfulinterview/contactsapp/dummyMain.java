package com.schd.uxfulinterview.contactsapp;

import com.schd.uxfulinterview.contactsapp.models.Contacts;
import org.bson.types.ObjectId;

import java.util.*;

public class dummyMain {


    public static void main(String[] args) {
        Contacts con1 = new Contacts(new ObjectId(),"Name1", "0123456","aaa@gmail.com");
        Contacts con2 = new Contacts(new ObjectId(),"Name8", "0123456","aaa@gmail.com");
        Contacts con3 = new Contacts(new ObjectId(),"Name5", "0123456","aaa@gmail.com");
        Contacts con4 = new Contacts(new ObjectId(),"Asdas", "0123456","aaa@gmail.com");

        ArrayList<Contacts> contacts = new ArrayList<>();
        contacts.add(con1);
        contacts.add(con2);
        contacts.add(con3);
        contacts.add(con4);

        System.out.println("Unsorted: \n ");
        contacts.forEach(elem -> System.out.println(elem.getName()));


        Collections.sort(contacts);
        System.out.println("sorted: \n " + contacts);
        contacts.forEach(elem -> System.out.println(elem.getName()));

    }
}
