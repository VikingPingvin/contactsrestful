package com.schd.uxfulinterview.contactsapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Contacts implements Comparable<Contacts> {

    @Id
    private ObjectId _id;

    private String name;
    private String phone;
    private String email;

    public Contacts(ObjectId _id, String name, String phone, String email) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Contacts() {
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    // Custom comparison override, so contacts can be sorted based on name
    // which is a String object, where compareTo is implemented already.
    @Override
    public int compareTo(Contacts cont) {
        return this.name.compareTo(cont.getName());
    }

}
