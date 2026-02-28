package com.seveneleven.mycontactapp.contact.model;

import java.util.List;

/**
 * Represents an individual person contact.
 */
public class PersonContact extends Contact {

    // Normal constructor (UC-04)
    public PersonContact(String name,
                         List<PhoneNumber> phoneNumbers,
                         List<EmailAddress> emailAddresses) {

        super(name, phoneNumbers, emailAddresses);
    }

    // Copy constructor (UC-06)
    public PersonContact(PersonContact other) {
        super(
                other.getName(),
                other.getPhoneNumbers(),
                other.getEmailAddresses()
        );
    }

    @Override
    public String getType() {
        return "PERSON";
    }
}