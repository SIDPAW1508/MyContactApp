package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Contact {

    private final UUID id;
    private final LocalDateTime createdAt;
    private int contactCount;

    private String name;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;
    private final Set<String> tags = new HashSet<>();

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

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getContactCount() {
        return contactCount;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return List.copyOf(phoneNumbers);
    }

    public List<EmailAddress> getEmailAddresses() {
        return List.copyOf(emailAddresses);
    }

    public Set<String> getTags() {
        return Set.copyOf(tags);
    }

    // ================= BUSINESS METHODS =================

    public void incrementContactCount() {
        contactCount++;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumbers(List<PhoneNumber> numbers) {
        phoneNumbers.clear();
        phoneNumbers.addAll(numbers);
    }

    public void setEmailAddresses(List<EmailAddress> emails) {
        emailAddresses.clear();
        emailAddresses.addAll(emails);
    }

    public abstract String getType();
}