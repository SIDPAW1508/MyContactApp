package com.seveneleven.mycontactapp.contact.service;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import java.util.UUID;

import java.util.Set;

import java.util.HashSet;

import java.util.function.Predicate;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.search.SearchCriteria;

public class ContactService {

    // In-memory storage

    private final List<Contact> contacts = new ArrayList<>();

    // =========================

    // UC-04 : CREATE CONTACT

    // =========================

    public void addContact(Contact contact) {

        contacts.add(contact);

    }

    // =========================

    // UC-05 : VIEW CONTACT (BY ID)

    // =========================

    public Optional<Contact> getContactById(UUID id) {

        return contacts.stream()

                .filter(contact -> contact.getId().equals(id))

                .findFirst();

    }

    // =========================

    // UC-06 : FIND CONTACT (BY NAME)

    // =========================

    public Optional<Contact> getContactByName(String name) {

        return contacts.stream()

                .filter(contact -> contact.getName().equalsIgnoreCase(name))

                .findFirst();

    }

    // =========================

    // UC-06 : EDIT CONTACT

    // =========================

    public void replaceContact(Contact oldContact, Contact newContact) {

        int index = contacts.indexOf(oldContact);

        if (index != -1) {

            contacts.set(index, newContact);

        }

    }

    // =========================

    // UC-07 : DELETE CONTACT (BY ID)

    // =========================

    public boolean deleteContactById(UUID id) {

        return contacts.removeIf(

                contact -> contact.getId().equals(id)

        );

    }

    // =========================

    // UC-07 : DELETE CONTACT (BY NAME)

    // =========================

    public boolean deleteContactByName(String name) {

        return contacts.removeIf(

                contact -> contact.getName().equalsIgnoreCase(name)

        );

    }

    // =========================

    // UC-08 : BULK DELETE

    // =========================

    public int bulkDelete(Predicate<Contact> condition) {

        int before = contacts.size();

        contacts.removeIf(condition);

        return before - contacts.size();

    }

    // =========================

    // UC-08 : BULK TAG

    // =========================

    public void bulkAddTag(Predicate<Contact> condition, String tag) {

        contacts.stream()

                .filter(condition)

                .forEach(contact -> contact.addTag(tag));

    }

    // =========================

    // UC-08 : BULK EXPORT

    // =========================

    public List<String> bulkExport(Predicate<Contact> condition) {

        return contacts.stream()

                .filter(condition)

                .map(Contact::toString)

                .toList();

    }
 // ================= UC-09 : SEARCH CONTACTS =================
    public List<Contact> search(SearchCriteria criteria) {
       return contacts.stream()
               .filter(criteria.toPredicate())
               .toList();
    }

    // =========================

    // EXTRA : LIST ALL CONTACTS

    // =========================

    public List<Contact> getAllContacts() {

        return List.copyOf(contacts); // immutable view

    }

}
 