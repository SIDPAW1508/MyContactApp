package com.seveneleven.mycontactapp.contact.service;

import com.seveneleven.mycontactapp.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class responsible for managing Contact objects.
 *
 * Provides basic in-memory CRUD-style operations.
 */
public class ContactService {

    // In-memory storage for contacts
    private final List<Contact> contacts = new ArrayList<>();

    /**
     * Adds a new contact to the list.
     *
     * @param contact the contact to add
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Returns all stored contacts.
     *
     * @return list of contacts
     */
    public List<Contact> getAllContacts() {
        return contacts;
    }

    /**
     * Retrieves a contact by its unique ID.
     *
     * Uses Java Stream API to filter and find the first match.
     *
     * @param id the UUID of the contact
     * @return Optional containing the contact if found, otherwise empty
     */
    public Optional<Contact> getContactById(UUID id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id)) // fixed: getId() and contacts variable
                .findFirst();
    }
}