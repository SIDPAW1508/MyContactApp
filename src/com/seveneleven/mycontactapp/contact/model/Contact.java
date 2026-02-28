package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;
import java.util.*;
import com.seveneleven.mycontactapp.contact.tag.Tag;

/**
 * Abstract base class for all types of contacts (Person or Organization).
 * Handles common fields such as id, name, phone numbers, emails, tags, and creation time.
 */
public abstract class Contact {

    private final UUID id;
    private final LocalDateTime createdAt;
    private int contactCount;

    private String name;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for creating a contact with name, phone numbers, and email addresses.
     */
    protected Contact(String name,
                      List<PhoneNumber> phoneNumbers,
                      List<EmailAddress> emailAddresses) {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.contactCount = 0;

        this.name = name;
        this.phoneNumbers = new ArrayList<>(phoneNumbers);
        this.emailAddresses = new ArrayList<>(emailAddresses);
    }

    // ================= GETTERS =================
    public UUID getId() { return id; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public int getContactCount() { return contactCount; }

    public String getName() { return name; }

    public List<PhoneNumber> getPhoneNumbers() { return List.copyOf(phoneNumbers); }

    public List<EmailAddress> getEmailAddresses() { return List.copyOf(emailAddresses); }

    public Set<Tag> getTags() { return Set.copyOf(tags); }

    // ================= BUSINESS METHODS =================
    public void incrementContactCount() { contactCount++; }

    public void addTag(Tag tag) { tags.add(tag); }

    public void removeTag(Tag tag) { tags.remove(tag); }

    public void setName(String name) { this.name = name; }

    public void setPhoneNumbers(List<PhoneNumber> numbers) {
        phoneNumbers.clear();
        phoneNumbers.addAll(numbers);
    }

    public void setEmailAddresses(List<EmailAddress> emails) {
        emailAddresses.clear();
        emailAddresses.addAll(emails);
    }

    /**
     * Returns the type of contact: "PERSON" or "ORGANIZATION".
     */
    public abstract String getType();
}