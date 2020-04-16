package com.vpetrou.service;

import java.util.List;

import com.vpetrou.entity.Contact;

public interface IContactService {
     List<Contact> getAllContacts();
     Contact getContactById(long contactId);
     List<Contact> getContactByName(String name);
     boolean addContact(Contact contact);
     void updateContact(Contact contact);
     void deleteContact(Contact contact);
}
