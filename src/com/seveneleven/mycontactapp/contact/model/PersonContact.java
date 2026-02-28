package com.seveneleven.mycontactapp.contact.model;

import java.util.List;
import java.util.Set;
import com.seveneleven.mycontactapp.contact.tag.Tag;

/**
 * Represents an individual person contact.
 */
public class PersonContact extends Contact {

    // Normal constructor
    public PersonContact(String name, List<PhoneNumber> phoneNumbers, List<EmailAddress> emailAddresses) {
        super(name, phoneNumbers, emailAddresses);
    }

    // Copy constructor
    public PersonContact(PersonContact other) {
        super(other.getName(), other.getPhoneNumbers(), other.getEmailAddresses());
    }

    @Override
    public String getType() {
        return "PERSON";
    }

    // Implement the abstract method
    @Override
    public void addTags(Set<Tag> tags) {
        addTags1(tags); // addTags1() is already implemented in Contact
    }
}