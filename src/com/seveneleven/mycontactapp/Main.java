package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.store.UserStore;
import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;
import com.seveneleven.mycontactapp.contact.tag.Tag;
import com.seveneleven.mycontactapp.contact.tag.TagService;
import com.seveneleven.mycontactapp.contact.search.*;
import com.seveneleven.mycontactapp.exception.ValidationException;
import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Main entry point for MyContactApp - a console-based contact management system.
 *
 * Features:
 *  - User Signup/Login
 *  - Profile Management
 *  - Create/Edit/Delete Contacts
 *  - Bulk Operations
 *  - Search Contacts (strategy pattern)
 *  - Tag Management
 */
public class Main {

    private static final TagService tagService = new TagService(); // Service for managing tags

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactService contactService = new ContactService();
        User loggedInUser;

        try {
            // ================= UC-01 : SIGNUP =================
            System.out.println("=== USER SIGN UP ===");
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Password: "); String password = sc.nextLine();

            loggedInUser = new UserBuilder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();

            UserStore.saveUser(email, password);
            System.out.println("✅ Signup successful!\n");

            // ================= UC-02 : LOGIN =================
            System.out.println("=== LOGIN ===");
            System.out.print("Email: "); String loginEmail = sc.nextLine();
            System.out.print("Password: "); String loginPassword = sc.nextLine();

            Authentication auth = new BasicAuthStrategy();
            if (auth.login(loginEmail, loginPassword).isEmpty()) {
                System.out.println("❌ Invalid credentials. Exiting...");
                return;
            }
            System.out.println("✅ Login successful!\n");

            // ================= MAIN MENU LOOP =================
            while (true) {
                printMenu();
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> createContact(sc, contactService);
                    case 2 -> viewContact(sc, contactService);
                    case 3 -> editContact(sc, contactService);
                    case 4 -> changeProfileName(sc, loggedInUser);
                    case 5 -> changePassword(sc, loggedInUser);
                    case 6 -> deleteContact(sc, contactService);
                    case 7 -> bulkOperations(sc, contactService);
                    case 8 -> searchContacts(sc, contactService);
                    case 9 -> tagContact(sc, contactService); // New UC: Tag a contact
                    case 0 -> {
                        System.out.println("Goodbye 👋");
                        return;
                    }
                    default -> System.out.println("❌ Invalid option.");
                }
            }

        } catch (ValidationException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // ================= MENU =================
    private static void printMenu() {
        System.out.println("\n========= MENU =========");
        System.out.println("1. Create Contact");
        System.out.println("2. View Contact");
        System.out.println("3. Edit Contact");
        System.out.println("4. Change Profile Name");
        System.out.println("5. Change Password");
        System.out.println("6. Delete Contact");
        System.out.println("7. Bulk Operations");
        System.out.println("8. Search Contacts");
        System.out.println("9. Tag a Contact");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    // ================= CREATE CONTACT =================
    private static void createContact(Scanner sc, ContactService service) {
        System.out.print("1-Person | 2-Organization: ");
        int type = Integer.parseInt(sc.nextLine());
        System.out.print("Contact Name: "); String name = sc.nextLine();

        List<PhoneNumber> phones = new ArrayList<>();
        System.out.print("Phone count: "); int pCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < pCount; i++) phones.add(new PhoneNumber(sc.nextLine()));

        List<EmailAddress> emails = new ArrayList<>();
        System.out.print("Email count: "); int eCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < eCount; i++) emails.add(new EmailAddress(sc.nextLine()));

        Contact contact = (type == 1)
                ? new PersonContact(name, phones, emails)
                : new OrganizationContact(name, phones, emails);

        service.addContact(contact);
        System.out.println("✅ Created. ID: " + contact.getId());
    }

    // ================= VIEW CONTACT =================
    private static void viewContact(Scanner sc, ContactService service) {
        System.out.print("Enter Contact ID: ");
        UUID id = UUID.fromString(sc.nextLine());

        service.getContactById(id)
                .ifPresentOrElse(
                        c -> {
                            c.incrementContactCount();
                            System.out.println(c);
                        },
                        () -> System.out.println("❌ Contact not found.")
                );
    }

    // ================= EDIT CONTACT =================
    private static void editContact(Scanner sc, ContactService service) {
        System.out.print("Enter contact name to edit: ");
        String name = sc.nextLine();

        service.getContactByName(name)
                .ifPresentOrElse(contact -> {
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    if (!newName.isBlank()) contact.setName(newName);
                    System.out.println("✅ Updated.");
                }, () -> System.out.println("❌ Contact not found."));
    }

    // ================= CHANGE PROFILE NAME =================
    private static void changeProfileName(Scanner sc, User user) {
        System.out.print("New Name: ");
        user.setName(sc.nextLine());
        System.out.println("✅ Name updated.");
    }

    // ================= CHANGE PASSWORD =================
    private static void changePassword(Scanner sc, User user) {
        System.out.print("Old Password: "); String oldPass = sc.nextLine();
        System.out.print("New Password: "); String newPass = sc.nextLine();
        user.changePassword(oldPass, newPass);
        System.out.println("✅ Password changed.");
    }

    // ================= DELETE CONTACT =================
    private static void deleteContact(Scanner sc, ContactService service) {
        System.out.print("Enter Contact ID: "); UUID id = UUID.fromString(sc.nextLine());
        if (service.deleteContactById(id)) System.out.println("✅ Deleted.");
        else System.out.println("❌ Contact not found.");
    }

    // ================= BULK OPERATIONS =================
    private static void bulkOperations(Scanner sc, ContactService service) {
        System.out.println("1. Delete PERSON");
        System.out.println("2. Delete ORGANIZATION");
        int type = Integer.parseInt(sc.nextLine());

        int deleted = switch (type) {
            case 1 -> service.bulkDelete(c -> c.getType().equalsIgnoreCase("PERSON"));
            case 2 -> service.bulkDelete(c -> c.getType().equalsIgnoreCase("ORGANIZATION"));
            default -> 0;
        };
        System.out.println("✅ Deleted " + deleted + " contacts.");
    }

    // ================= SEARCH CONTACTS =================
    private static void searchContacts(Scanner sc, ContactService service) {
        System.out.println("1. By Name | 2. By Phone | 3. By Email");
        int option = Integer.parseInt(sc.nextLine());

        SearchCriteria criteria = switch (option) {
            case 1 -> { System.out.print("Name: "); yield new NameSearchCriteria(sc.nextLine()); }
            case 2 -> { System.out.print("Phone: "); yield new PhoneSearchCriteria(sc.nextLine()); }
            case 3 -> { System.out.print("Email: "); yield new EmailSearchCriteria(sc.nextLine()); }
            default -> null;
        };

        if (criteria != null) {
            List<Contact> results = service.search(criteria);
            if (results.isEmpty()) System.out.println("❌ No contacts found.");
            else results.forEach(System.out::println);
        }
    }

    // ================= TAG CONTACT =================
    private static void tagContact(Scanner sc, ContactService service) {
        System.out.print("Enter tag name to create: ");
        String tagName = sc.nextLine().trim();

        Tag tag = tagService.createTag(tagName);
        if (tag == null) {
            System.out.println("❌ Failed to create tag.");
            return;
        }

        System.out.print("Enter contact name to tag: ");
        String contactName = sc.nextLine();

        service.getContactByName(contactName)
                .ifPresentOrElse(c -> {
                    c.addTag(tag);
                    System.out.println("✅ Tag '" + tag.getName() + "' added to contact '" + c.getName() + "'.");
                }, () -> System.out.println("❌ Contact not found."));
    }
}