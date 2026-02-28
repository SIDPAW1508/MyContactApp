package com.seveneleven.mycontactapp.contact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Service layer responsible for managing Contact objects.
 *
 * <p>
 * This class provides in-memory CRUD operations for contacts:
 * </p>
 *
 * <ul>
 *     <li>UC-04 : Create Contact</li>
 *     <li>UC-05 : View Contact (by ID)</li>
 *     <li>UC-06 : Edit/Replace Contact</li>
 *     <li>UC-07 : Delete Contact</li>
 * </ul>
 *
 * <p>
 * NOTE:
 * Contacts are stored in-memory using a List.
 * Data will be lost when the application stops.
 * </p>
 *
 * @author
 * MyContactApp Team
 */
public class ContactService {

    /**
     * In-memory list storing all contacts.
     */
    private final List<Contact> contacts = new ArrayList<>();

    // =====================================================
    // UC-04 : CREATE CONTACT
    // =====================================================

    /**
     * Adds a new contact to the system.
     *
     * @param contact the contact to be added
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // =====================================================
    // UC-05 : VIEW CONTACT (BY ID)
    // =====================================================

    /**
     * Retrieves a contact by its unique ID.
     *
     * @param id UUID of the contact
     * @return Optional containing the contact if found,
     *         otherwise Optional.empty()
     */
    public Optional<Contact> getContactById(UUID id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    // =====================================================
    // UC-06 : FIND CONTACT (BY NAME)
    // =====================================================

    /**
     * Searches for a contact by name (case-insensitive).
     *
     * @param name name of the contact
     * @return Optional containing the contact if found,
     *         otherwise Optional.empty()
     */
    public Optional<Contact> getContactByName(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // =====================================================
    // UC-06 : REPLACE CONTACT (EDIT)
    // =====================================================

    /**
     * Replaces an existing contact with a modified version.
     *
     * @param oldContact existing contact
     * @param newContact updated contact
     *
     * <p>
     * If the old contact exists in the list, it is replaced.
     * If not found, no action is taken.
     * </p>
     */
    public void replaceContact(Contact oldContact, Contact newContact) {
        int index = contacts.indexOf(oldContact);
        if (index != -1) {
            contacts.set(index, newContact);
        }
    }

    // =====================================================
    // UC-07 : DELETE CONTACT (BY ID)
    // =====================================================

    /**
     * Deletes a contact by its unique ID.
     *
     * @param id UUID of the contact
     * @return true if a contact was removed,
     *         false otherwise
     */
    public boolean deleteContactById(UUID id) {
        return contacts.removeIf(
                contact -> contact.getId().equals(id)
        );
    }

    // =====================================================
    // UC-07 : DELETE CONTACT (BY NAME)
    // =====================================================

    /**
     * Deletes contact(s) by name (case-insensitive).
     *
     * @param name name of the contact
     * @return true if at least one contact was removed,
     *         false otherwise
     */
    public boolean deleteContactByName(String name) {
        return contacts.removeIf(
                contact -> contact.getName().equalsIgnoreCase(name)
        );
    }

    // =====================================================
    // OPTIONAL : LIST ALL CONTACTS
    // =====================================================

    /**
     * Returns an immutable copy of all contacts.
     *
     * @return unmodifiable list of contacts
     */
    public List<Contact> getAllContacts() {
        return List.copyOf(contacts);
    }
}