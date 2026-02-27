package com.seveneleven.mycontactapp.contact.model;
import java.util.List;
public class PersonContact extends Contact {
   public PersonContact(String name,
                        List<PhoneNumber> phoneNumbers,
                        List<EmailAddress> emailAddresses) {
       super(name, phoneNumbers, emailAddresses);
   }
   @Override
   public String getType() {
       return "PERSON";
   }
}