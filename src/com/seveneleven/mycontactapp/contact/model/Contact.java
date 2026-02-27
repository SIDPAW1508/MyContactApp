package com.seveneleven.mycontactapp.contact.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public abstract class Contact {
   private final UUID id;
   private final LocalDateTime createdAt;
   protected String name;
   protected List<PhoneNumber> phoneNumbers;
   protected List<EmailAddress> emailAddresses;
   protected Contact(String name,
                     List<PhoneNumber> phoneNumbers,
                     List<EmailAddress> emailAddresses) {
this.id = UUID.randomUUID();
       this.createdAt = LocalDateTime.now();
       this.name = name;
       this.phoneNumbers = phoneNumbers;
       this.emailAddresses = emailAddresses;
   }
   public UUID getId() {
       return id;
   }
   public LocalDateTime getCreatedAt() {
       return createdAt;
   }
   public String getName() {
       return name;
   }
   public List<PhoneNumber> getPhoneNumbers() {
       return phoneNumbers;
   }
   public List<EmailAddress> getEmailAddresses() {
       return emailAddresses;
   }
   public abstract String getType();
}