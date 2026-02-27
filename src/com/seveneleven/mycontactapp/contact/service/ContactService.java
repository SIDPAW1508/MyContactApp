package com.seveneleven.mycontactapp.contact.service;
import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactapp.contact.model.Contact;
public class ContactService {
   private final List<Contact> contacts = new ArrayList<>();
   public void addContact(Contact contact) {
       contacts.add(contact);
   }
   public List<Contact> getAllContacts() {
       return contacts;
   }
}