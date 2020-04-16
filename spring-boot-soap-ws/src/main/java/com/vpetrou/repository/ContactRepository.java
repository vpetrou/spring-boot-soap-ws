package com.vpetrou.repository;

import com.vpetrou.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByContactId(long contactId);

    List<Contact> findByNameAndCategory(String bane, String category);

    List<Contact> findByNameIgnoreCaseContaining(String name);
}
