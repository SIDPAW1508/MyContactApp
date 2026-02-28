package com.seveneleven.mycontactapp;

import java.util.*;
import java.util.UUID;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.store.UserStore;
import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;
import com.seveneleven.mycontactapp.exception.ValidationException;
import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Main class for MyContactApp.
 *
 * This class handles:
 *  - User Signup (UC-01)
 *  - User Login (UC-02)
 *  - Change Profile Name (UC-03)
 *  - Create Contact (UC-04)
 *  - View Contact (UC-05)
 *  - Edit Contact (UC-06)
 *  - Delete Contact (UC-07)
 *
 * The application runs as a console-based interactive program.
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactService contactService = new ContactService();
        User loggedInUser = null;

        try {

            // =====================================================
            // UC-01 : USER SIGN UP
            // =====================================================
            System.out.println("=== USER SIGN UP ===");

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            loggedInUser = new UserBuilder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();

            UserStore.saveUser(email, password);

            System.out.println("✅ Signup successful!\n");

            // =====================================================
            // UC-02 : USER LOGIN
            // =====================================================
            System.out.println("=== USER LOGIN ===");

            System.out.print("Enter email: ");
            String loginEmail = sc.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();

            Authentication auth = new BasicAuthStrategy();

            if (auth.login(loginEmail, loginPassword).isEmpty()) {
                System.out.println("❌ Invalid credentials. Exiting...");
                return;
            }

            System.out.println("✅ Login successful!\n");

            // =====================================================
            // MAIN MENU LOOP
            // =====================================================
            while (true) {

                System.out.println("\n=== MENU ===");
                System.out.println("1. Create Contact (UC-04)");
                System.out.println("2. View Contact Details (UC-05)");
                System.out.println("3. Edit Contact (UC-06)");
                System.out.println("4. Change Profile Name (UC-03)");
                System.out.println("5. Change Password (UC-03)");
                System.out.println("6. Delete Contact (UC-07)");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    // =====================================================
                    // UC-04 : CREATE CONTACT
                    // =====================================================
                    case 1 -> {

                        System.out.print("Contact type (1-Person, 2-Organization): ");
                        int type = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter contact name: ");
                        String contactName = sc.nextLine();

                        // Phone Numbers
                        List<PhoneNumber> phones = new ArrayList<>();
                        System.out.print("How many phone numbers? ");
                        int pCount = Integer.parseInt(sc.nextLine());

                        for (int i = 0; i < pCount; i++) {
                            System.out.print("Phone " + (i + 1) + ": ");
                            phones.add(new PhoneNumber(sc.nextLine()));
                        }

                        // Email Addresses
                        List<EmailAddress> emails = new ArrayList<>();
                        System.out.print("How many email addresses? ");
                        int eCount = Integer.parseInt(sc.nextLine());

                        for (int i = 0; i < eCount; i++) {
                            System.out.print("Email " + (i + 1) + ": ");
                            emails.add(new EmailAddress(sc.nextLine()));
                        }

                        Contact contact = (type == 1)
                                ? new PersonContact(contactName, phones, emails)
                                : new OrganizationContact(contactName, phones, emails);

                        contactService.addContact(contact);

                        System.out.println("✅ Contact created. ID: " + contact.getId());
                    }

                    // =====================================================
                    // UC-05 : VIEW CONTACT
                    // =====================================================
                    case 2 -> {

                        System.out.print("Enter Contact ID: ");
                        UUID id = UUID.fromString(sc.nextLine());

                        contactService.getContactById(id)
                                .ifPresentOrElse(
                                        c -> {
                                            System.out.println("\n--- CONTACT DETAILS ---");
                                            System.out.println(c);
                                        },
                                        () -> System.out.println("❌ Contact not found.")
                                );
                    }

                    // =====================================================
                    // UC-06 : EDIT CONTACT
                    // =====================================================
                    case 3 -> {

                        System.out.print("Enter contact name to edit: ");
                        String searchName = sc.nextLine();

                        contactService.getContactByName(searchName)
                                .ifPresentOrElse(original -> {

                                    Contact copy = (original instanceof PersonContact)
                                            ? new PersonContact((PersonContact) original)
                                            : new OrganizationContact((OrganizationContact) original);

                                    System.out.print("New name (blank to skip): ");
                                    String newName = sc.nextLine();
                                    if (!newName.isBlank()) {
                                        copy.setName(newName);
                                    }

                                    // Edit Phones
                                    System.out.print("Edit phone numbers? (y/n): ");
                                    if (sc.nextLine().equalsIgnoreCase("y")) {

                                        List<PhoneNumber> newPhones = new ArrayList<>();
                                        System.out.print("How many? ");
                                        int count = Integer.parseInt(sc.nextLine());

                                        for (int i = 0; i < count; i++) {
                                            System.out.print("Phone " + (i + 1) + ": ");
                                            newPhones.add(new PhoneNumber(sc.nextLine()));
                                        }

                                        copy.setPhoneNumbers(newPhones);
                                    }

                                    // Edit Emails
                                    System.out.print("Edit email addresses? (y/n): ");
                                    if (sc.nextLine().equalsIgnoreCase("y")) {

                                        List<EmailAddress> newEmails = new ArrayList<>();
                                        System.out.print("How many? ");
                                        int count = Integer.parseInt(sc.nextLine());

                                        for (int i = 0; i < count; i++) {
                                            System.out.print("Email " + (i + 1) + ": ");
                                            newEmails.add(new EmailAddress(sc.nextLine()));
                                        }

                                        copy.setEmailAddresses(newEmails);
                                    }

                                    contactService.replaceContact(original, copy);
                                    System.out.println("✅ Contact updated.");

                                }, () -> System.out.println("❌ Contact not found."));
                    }

                    // =====================================================
                    // UC-03 : CHANGE PROFILE NAME
                    // =====================================================
                    case 4 -> {
                        System.out.print("Enter new name: ");
                        loggedInUser.setName(sc.nextLine());
                        System.out.println("✅ Name updated.");
                    }

                    // =====================================================
                    // UC-03 : CHANGE PASSWORD
                    // =====================================================
                    case 5 -> {
                        System.out.print("Enter old password: ");
                        String oldPass = sc.nextLine();

                        System.out.print("Enter new password: ");
                        String newPass = sc.nextLine();

                        loggedInUser.changePassword(oldPass, newPass);
                        System.out.println("✅ Password changed successfully.");
                    }

                    // =====================================================
                    // UC-07 : DELETE CONTACT
                    // =====================================================
                    case 6 -> {

                        System.out.print("Enter Contact ID to delete: ");
                        UUID id = UUID.fromString(sc.nextLine());

                        contactService.getContactById(id)
                                .ifPresentOrElse(contact -> {

                                    System.out.println("\n--- CONTACT TO DELETE ---");
                                    System.out.println(contact);

                                    System.out.print("Are you sure? (y/n): ");
                                    String confirm = sc.nextLine();

                                    if (confirm.equalsIgnoreCase("y")) {

                                        boolean deleted = contactService.deleteContactById(id);

                                        if (deleted) {
                                            System.out.println("✅ Contact deleted successfully.");
                                        } else {
                                            System.out.println("❌ Deletion failed.");
                                        }

                                    } else {
                                        System.out.println("❎ Deletion cancelled.");
                                    }

                                }, () -> System.out.println("❌ Contact not found."));
                    }

                    // =====================================================
                    // EXIT APPLICATION
                    // =====================================================
                    case 0 -> {
                        System.out.println("Goodbye 👋");
                        return;
                    }

                    default -> System.out.println("❌ Invalid option.");
                }
            }

        } catch (ValidationException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}