package com.vpetrou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpetrou.entity.Contact;
import com.vpetrou.repository.ContactRepository;
@Service
public class ContactService implements IContactService {
	@Autowired
	private ContactRepository contactRepository;
	@Override
	public Contact getContactById(long contactId) {
		Contact obj = contactRepository.findByContactId(contactId);
		return obj;
	}
	@Override
	public List<Contact> getContactByName(String title) {
		List<Contact> list = new ArrayList<>();
		contactRepository.findByNameIgnoreCaseContaining(title).forEach(e -> list.add(e));
		return list;
	}
	@Override
	public List<Contact> getAllContacts(){
		List<Contact> list = new ArrayList<>();
		contactRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addContact(Contact contact){
	   List<Contact> list = contactRepository.findByNameAndCategory(contact.getName(), contact.getCategory());
       if (list.size() > 0) {
    	   return false;
       } else {
    	   contact = contactRepository.save(contact);
    	   return true;
       }
	}
	@Override
	public void updateContact(Contact contact) {
		contactRepository.save(contact);
	}
	@Override
	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}
}
