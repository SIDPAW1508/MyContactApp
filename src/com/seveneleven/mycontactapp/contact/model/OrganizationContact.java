package com.seveneleven.mycontactapp.contact.model;

import java.util.List;

/**
 * Represents an organization/company contact.
 *
 * Used for storing business or institutional contacts.
 */
public class OrganizationContact extends Contact {

    // =====================================================
    // Constructor (UC-04 : Create Contact)
    // =====================================================
    public OrganizationContact(String name,
                               List<PhoneNumber> phoneNumbers,
                               List<EmailAddress> emailAddresses) {

        super(name, phoneNumbers, emailAddresses);
    }

    // =====================================================
    // Copy Constructor (UC-06 : Edit Contact)
    // =====================================================
    public OrganizationContact(OrganizationContact other) {

        super(
                other.getName(),
                other.getPhoneNumbers(),
                other.getEmailAddresses()
        );
    }

    // =====================================================
    // Contact Type
    // =====================================================
    @Override
    public String getType() {
        return "ORGANIZATION";
    }
}