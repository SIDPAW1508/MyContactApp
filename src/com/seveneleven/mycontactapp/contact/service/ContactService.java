package com.seveneleven.mycontactapp.contact.service;

import java.util.*;
import java.util.function.Predicate;

import com.seveneleven.mycontactapp.contact.filter.ContactFilter;
import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.search.SearchCriteria;

/**
 * Service layer responsible for managing Contact entities.
 *
 * Provides:
 *  - CRUD operations
 *  - Bulk operations
 *  - Search (Strategy Pattern)
 *  - Advanced filtering (Predicate-based)
 *
 * NOTE: In-memory storage only.
 */
public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    // ================= CREATE =================
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // ================= VIEW =================
    public Optional<Contact> getContactById(UUID id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Optional<Contact> getContactByName(String name) {
        return contacts.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // Track contact usage
    public Optional<Contact> getContactAndTrack(UUID id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> {
                    c.incrementContactCount();
                    return c;
                });
    }

    // ================= EDIT =================
    public void replaceContact(Contact oldContact, Contact newContact) {
        int index = contacts.indexOf(oldContact);
        if (index != -1) {
            contacts.set(index, newContact);
        }
    }

    // ================= DELETE =================
    public boolean deleteContactById(UUID id) {
        return contacts.removeIf(c -> c.getId().equals(id));
    }

    public boolean deleteContactByName(String name) {
        return contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }

    // ================= BULK =================
    public int bulkDelete(Predicate<Contact> condition) {
        int before = contacts.size();
        contacts.removeIf(condition);
        return before - contacts.size();
    }

    public void bulkAddTag(Predicate<Contact> condition, String tag) {
        contacts.stream()
                .filter(condition)
                .forEach(c -> c.addTag(tag));
    }

    public List<String> bulkExport(Predicate<Contact> condition) {
        return contacts.stream()
                .filter(condition)
                .map(Contact::toString)
                .toList();
    }

    // ================= SEARCH (Strategy) =================
    public List<Contact> search(SearchCriteria criteria) {
        return contacts.stream()
                .filter(criteria.toPredicate())
                .toList();
    }

    // ================= ADVANCED FILTER =================
    public List<Contact> advancedFilter(ContactFilter filter,
                                        Comparator<Contact> sorter) {

        return contacts.stream()
                .filter(filter.toPredicate())
                .sorted(sorter)
                .toList();
    }

    // ================= EXTRA =================
    public List<Contact> getAllContacts() {
        return List.copyOf(contacts);
    }
}