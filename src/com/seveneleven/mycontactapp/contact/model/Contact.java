package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Abstract base class representing a Contact.
 *
 * This class defines common attributes and behavior shared by
 * all contact types (e.g., PersonContact, OrganizationContact).
 *
 * Responsibilities:
 * - Generate unique ID
 * - Track creation timestamp
 * - Store contact details (name, phones, emails)
 * - Provide formatted output via toString()
 */
public abstract class Contact {

    // Unique identifier for each contact
    private final UUID id;

    // Timestamp when the contact was created
    private final LocalDateTime createdAt;

    // Common contact attributes
    protected String name;
    protected List<PhoneNumber> phoneNumbers;
    protected List<EmailAddress> emailAddresses;

    /**
     * Constructor used by subclasses.
     *
     * Automatically:
     * - Generates a random UUID
     * - Sets the creation timestamp
     *
     * @param name           contact name
     * @param phoneNumbers   list of phone numbers
     * @param emailAddresses list of email addresses
     */
    protected Contact(String name,
                      List<PhoneNumber> phoneNumbers,
                      List<EmailAddress> emailAddresses) {

        this.id = UUID.randomUUID();         // Generate unique ID
        this.createdAt = LocalDateTime.now(); // Capture creation time
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
    }

    /**
     * Returns the unique contact ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the contact creation timestamp.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the contact name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of phone numbers.
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Returns the list of email addresses.
     */
    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    /**
     * Returns the contact type.
     *
     * Implemented by subclasses (e.g., PERSON, ORGANIZATION).
     */
    public abstract String getType();
    public void setName(String name) {

        if (name == null || name.isBlank()) {

            throw new IllegalArgumentException("Name cannot be empty");

        }

        this.name = name;

    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {

        this.phoneNumbers = new ArrayList<>(phoneNumbers); // defensive copy

    }

    public void setEmailAddresses(List<EmailAddress> emailAddresses) {

        this.emailAddresses = new ArrayList<>(emailAddresses); // defensive copy

    }
    protected Contact(Contact other) {
    	this.id = other.id;
    	   this.createdAt = other.createdAt;
    	   this.name = other.name;
    	   // deep copy
    	   this.phoneNumbers = new ArrayList<>(other.phoneNumbers);
    	   this.emailAddresses = new ArrayList<>(other.emailAddresses);
    	}

    /**
     * Returns a formatted string representation of the contact.
     *
     * Includes:
     * - ID
     * - Type
     * - Name
     * - Creation timestamp
     * - Phone numbers
     * - Email addresses
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Contact ID: ").append(id).append("\n");
        sb.append("Type: ").append(getType()).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Created At: ").append(createdAt).append("\n");

        sb.append("Phone Numbers:\n");
        phoneNumbers.forEach(p ->
                sb.append("  - ").append(p.getNumber()).append("\n")
        );

        sb.append("Email Addresses:\n");
        emailAddresses.forEach(e ->
                sb.append("  - ").append(e.getEmail()).append("\n")
        );

        return sb.toString();
    }
}