package com.seveneleven.mycontactapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;
public class Main {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       ContactService contactService = new ContactService();
       System.out.println("=== CREATE CONTACT (UC-04) ===");
       System.out.print("Contact type (1-Person, 2-Organization): ");
       int type = Integer.parseInt(sc.nextLine());
       System.out.print("Enter name: ");
       String name = sc.nextLine();
       // Phones
       List<PhoneNumber> phones = new ArrayList<>();
       System.out.print("How many phone numbers? ");
       int phoneCount = Integer.parseInt(sc.nextLine());
       for (int i = 0; i < phoneCount; i++) {
           System.out.print("Enter phone " + (i + 1) + ": ");
           phones.add(new PhoneNumber(sc.nextLine()));
       }
       // Emails
       List<EmailAddress> emails = new ArrayList<>();
       System.out.print("How many email addresses? ");
       int emailCount = Integer.parseInt(sc.nextLine());
       for (int i = 0; i < emailCount; i++) {
           System.out.print("Enter email " + (i + 1) + ": ");
           emails.add(new EmailAddress(sc.nextLine()));
       }
       Contact contact;
       if (type == 1) {
           contact = new PersonContact(name, phones, emails);
       } else {
           contact = new OrganizationContact(name, phones, emails);
       }
       contactService.addContact(contact);
       System.out.println("\n✅ Contact created successfully!");
       System.out.println("Contact ID: " + contact.getId());
       System.out.println("Created At: " + contact.getCreatedAt());
       System.out.println("Type: " + contact.getType());
       sc.close();
   }
}