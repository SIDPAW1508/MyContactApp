package com.seveneleven.mycontactapp;
import java.util.*;
import java.util.stream.Collectors;
import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;
import com.seveneleven.mycontactapp.contact.tag.Tag;
public class Main {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       ContactService contactService = new ContactService();
       while (true) {
           System.out.println("\n=== MENU ===");
           System.out.println("1. Create Contact (UC-04)");
           System.out.println("2. View Contact Details (UC-05)");
           System.out.println("3. Edit Contact (UC-06)");
           System.out.println("4. Delete Contact (UC-07)");
           System.out.println("12. Apply Tags to Contact (UC-12)");
           System.out.println("0. Exit");
           System.out.print("Choose option: ");
           int choice;
           try {
               choice = Integer.parseInt(sc.nextLine());
           } catch (NumberFormatException e) {
               System.out.println("❌ Invalid input.");
               continue;
           }
           switch (choice) {
               // ================= UC-04 CREATE CONTACT =================
               case 1 -> {
                   System.out.print("Enter contact type (1-Person, 2-Organization): ");
                   int type = Integer.parseInt(sc.nextLine());
                   System.out.print("Enter name: ");
                   String name = sc.nextLine();
                   List<PhoneNumber> phones = new ArrayList<>();
                   System.out.print("How many phone numbers? ");
                   int phoneCount = Integer.parseInt(sc.nextLine());
                   for (int i = 0; i < phoneCount; i++) {
                       System.out.print("Enter phone: ");
                       phones.add(new PhoneNumber(sc.nextLine()));
                   }
                   List<EmailAddress> emails = new ArrayList<>();
                   System.out.print("How many emails? ");
                   int emailCount = Integer.parseInt(sc.nextLine());
                   for (int i = 0; i < emailCount; i++) {
                       System.out.print("Enter email: ");
                       emails.add(new EmailAddress(sc.nextLine()));
                   }
                   Contact contact = (type == 1)
                           ? new PersonContact(name, phones, emails)
                           : new OrganizationContact(name, phones, emails);
                   contactService.addContact(contact);
                   System.out.println(" Contact created. ID: " + contact.getId());
               }
               // ================= UC-05 VIEW CONTACT =================
               case 2 -> {
                   System.out.print("Enter contact name: ");
                   String name = sc.nextLine();
                   contactService.getContactByName(name)
                           .ifPresentOrElse(
                                   c -> {
                                       System.out.println("\n--- CONTACT DETAILS ---");
                                       System.out.println(c);
                                   },
                                   () -> System.out.println(" Contact not found.")
                           );
               }
               // ================= UC-06 EDIT CONTACT =================
               case 3 -> {
                   System.out.print("Enter contact name to edit: ");
                   String name = sc.nextLine();
                   Optional<Contact> opt = contactService.getContactByName(name);
                   if (opt.isEmpty()) {
                       System.out.println(" Contact not found.");
                       break;
                   }
                   Contact c = opt.get();
                   System.out.print("Enter new name: ");
                   c.setName(sc.nextLine());
                   System.out.println(" Contact updated.");
               }
               // ================= UC-07 DELETE CONTACT =================
               case 4 -> {
                   System.out.print("Enter contact name to delete: ");
                   String name = sc.nextLine();
                   System.out.print("Are you sure? (y/n): ");
                   if (sc.nextLine().equalsIgnoreCase("y")) {
                       boolean deleted = contactService.deleteContactByName(name);
                       System.out.println(deleted
                               ? " Contact deleted successfully."
                               : " Contact not found.");
                   }
               }
               // ================= UC-12 APPLY TAGS =================
               case 12 -> {
            	    System.out.print("Enter contact name: ");
            	    String name = sc.nextLine();

            	    System.out.print("Enter tags (comma separated): ");
            	    String[] input = sc.nextLine().split(",");

            	    Set<Tag> tags = Arrays.stream(input)
            	            .map(String::trim)
            	            .filter(s -> !s.isEmpty())
            	            .map(Tag::new)
            	            .collect(Collectors.toSet());

            	    // Fetch existing contact and apply tags
            	    contactService.getContactByName(name)
            	            .ifPresentOrElse(
            	                    contact -> {
            	                        contact.addTags(tags); // call the abstract method implemented in concrete class
            	                        System.out.println(" Tags applied successfully.");
            	                    },
            	                    () -> System.out.println("Contact not found.")
            	            );
            	}
               // ================= EXIT =================
               case 0 -> {
                   System.out.println(" Exiting application.");
                   sc.close();
                   return;
               }
               default -> System.out.println(" Invalid option.");
           }
       }
   }
}