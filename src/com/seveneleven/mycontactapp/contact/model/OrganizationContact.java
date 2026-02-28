package com.seveneleven.mycontactapp.contact.model;

import java.util.List;
import java.util.Set;
import com.seveneleven.mycontactapp.contact.tag.Tag;

/**
 * Represents an organization/company contact.
 */
public class OrganizationContact extends Contact {

    public OrganizationContact(String name, List<PhoneNumber> phoneNumbers, List<EmailAddress> emailAddresses) {
        super(name, phoneNumbers, emailAddresses);
    }

    public OrganizationContact(OrganizationContact other) {
        super(other.getName(), other.getPhoneNumbers(), other.getEmailAddresses());
    }

    @Override
    public String getType() {
        return "ORGANIZATION";
    }

    @Override
    public void addTags(Set<Tag> tags) {
        addTags1(tags);
    }
}