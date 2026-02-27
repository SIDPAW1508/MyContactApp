package com.seveneleven.mycontactapp.contact.model;
import java.util.List;
public class OrganizationContact extends Contact {
   public OrganizationContact(String name,
                              List<PhoneNumber> phoneNumbers,
                              List<EmailAddress> emailAddresses) {
       super(name, phoneNumbers, emailAddresses);
   }
   @Override
   public String getType() {
       return "ORGANIZATION";
   }
}