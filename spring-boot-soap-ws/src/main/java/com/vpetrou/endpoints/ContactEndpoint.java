package com.vpetrou.endpoints;

import com.vpetrou.entity.Contact;
import com.vpetrou.gs_ws.*;
import com.vpetrou.service.IContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ContactEndpoint {
    private static final String NAMESPACE_URI = "http://www.vpetrou.com/contact-ws";
    @Autowired
    private IContactService contactService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContactByIdRequest")
    @ResponsePayload
    public GetContactByIdResponse getContact(@RequestPayload GetContactByIdRequest request) {
        GetContactByIdResponse response = new GetContactByIdResponse();
        ContactInfo contactInfo = new ContactInfo();
        BeanUtils.copyProperties(contactService.getContactById(request.getContactId()), contactInfo);
        response.setContactInfo(contactInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContactByNameRequest")
    @ResponsePayload
    public GetContactByNameResponse getContactByName(@RequestPayload GetContactByNameRequest request) {
        GetContactByNameResponse response = new GetContactByNameResponse();
        List<ContactInfo> contactInfoList = new ArrayList<>();
        List<Contact> contactList = contactService.getContactByName(request.getName());
        for (int i = 0; i < contactList.size(); i++) {
            ContactInfo ob = new ContactInfo();
            BeanUtils.copyProperties(contactList.get(i), ob);
            contactInfoList.add(ob);
        }
        response.getContactInfo().addAll(contactInfoList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllContactsRequest")
    @ResponsePayload
    public GetAllContactsResponse getAllContacts() {
        GetAllContactsResponse response = new GetAllContactsResponse();
        List<ContactInfo> contactInfoList = new ArrayList<>();
        List<Contact> contactList = contactService.getAllContacts();
        for (int i = 0; i < contactList.size(); i++) {
            ContactInfo ob = new ContactInfo();
            BeanUtils.copyProperties(contactList.get(i), ob);
            contactInfoList.add(ob);
        }
        response.getContactInfo().addAll(contactInfoList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addContactRequest")
    @ResponsePayload
    public AddContactResponse addContact(@RequestPayload AddContactRequest request) {
        AddContactResponse response = new AddContactResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setNickName(request.getNickName());
        contact.setCategory(request.getCategory());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setCity(request.getCity());
        contact.setCountry(request.getCountry());
        boolean flag = contactService.addContact(contact);
        if (flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            ContactInfo contactInfo = new ContactInfo();
            BeanUtils.copyProperties(contact, contactInfo);
            response.setContactInfo(contactInfo);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateContactRequest")
    @ResponsePayload
    public UpdateContactResponse updateContact(@RequestPayload UpdateContactRequest request) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(request.getContactInfo(), contact);
        contactService.updateContact(contact);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateContactResponse response = new UpdateContactResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteContactRequest")
    @ResponsePayload
    public DeleteContactResponse deleteContact(@RequestPayload DeleteContactRequest request) {
        Contact contact = contactService.getContactById(request.getContactId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (contact == null) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            contactService.deleteContact(contact);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeleteContactResponse response = new DeleteContactResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
