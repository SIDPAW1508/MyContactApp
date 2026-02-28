package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.store.UserStore;
import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.search.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;
import com.seveneleven.mycontactapp.exception.ValidationException;
import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * =============================================================
 * MyContactApp - Console Based Contact Management Application
 * =============================================================
 *
 * Handles:
 *  UC-01 : User Signup
 *  UC-02 : User Login
 *  UC-03 : Profile Update
 *  UC-04 : Create Contact
 *  UC-05 : View Contact
 *  UC-06 : Edit Contact
 *  UC-07 : Delete Contact
 *  UC-08 : Bulk Operations
 *  UC-09 : Search Contacts (Strategy Pattern)
 *
 * This application uses:
 *  - Builder Pattern (User creation)
 *  - Strategy Pattern (Search)
 *  - Composite Pattern (Advanced Search)
 *  - Service Layer Architecture
 *
 * NOTE: All data is stored in memory.
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactService contactService = new ContactService();
        User loggedInUser;

        try {

            // =====================================================
            // UC-01 : USER SIGNUP
            // =====================================================
            System.out.println("=== USER SIGN UP ===");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            loggedInUser = new UserBuilder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();

            UserStore.saveUser(email, password);

            System.out.println("✅ Signup successful!\n");

            // =====================================================
            // UC-02 : LOGIN
            // =====================================================
            System.out.println("=== LOGIN ===");

            System.out.print("Email: ");
            String loginEmail = sc.nextLine();

            System.out.print("Password: ");
            String loginPassword = sc.nextLine();

            Authentication auth = new BasicAuthStrategy();

            if (auth.login(loginEmail, loginPassword).isEmpty()) {
                System.out.println("❌ Invalid credentials.");
                return;
            }

            System.out.println("✅ Login successful!");

            // =====================================================
            // MAIN MENU LOOP
            // =====================================================
            while (true) {

                System.out.println("\n========= MENU =========");
                System.out.println("1. Create Contact");
                System.out.println("2. View Contact");
                System.out.println("3. Edit Contact");
                System.out.println("4. Change Profile Name");
                System.out.println("5. Change Password");
                System.out.println("6. Delete Contact");
                System.out.println("7. Bulk Operations");
                System.out.println("8. Search Contacts");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    // ================= CREATE =================
                    case 1 -> createContact(sc, contactService);

                    // ================= VIEW =================
                    case 2 -> viewContact(sc, contactService);

                    // ================= EDIT =================
                    case 3 -> editContact(sc, contactService);

                    // ================= PROFILE =================
                    case 4 -> {
                        System.out.print("New Name: ");
                        loggedInUser.setName(sc.nextLine());
                        System.out.println("✅ Updated.");
                    }

                    case 5 -> {
                        System.out.print("Old Password: ");
                        String oldPass = sc.nextLine();
                        System.out.print("New Password: ");
                        String newPass = sc.nextLine();
                        loggedInUser.changePassword(oldPass, newPass);
                        System.out.println("✅ Password changed.");
                    }

                    // ================= DELETE =================
                    case 6 -> deleteContact(sc, contactService);

                    // ================= BULK =================
                    case 7 -> bulkOperations(sc, contactService);

                    // ================= SEARCH =================
                    case 8 -> searchContacts(sc, contactService);

                    case 0 -> {
                        System.out.println("Goodbye 👋");
                        return;
                    }

                    default -> System.out.println("Invalid option.");
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

    // =====================================================
    // CREATE CONTACT
    // =====================================================
    private static void createContact(Scanner sc, ContactService service) {

        System.out.print("1-Person | 2-Organization: ");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Contact Name: ");
        String name = sc.nextLine();

        List<PhoneNumber> phones = new ArrayList<>();
        System.out.print("Phone count: ");
        int pCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < pCount; i++) {
            phones.add(new PhoneNumber(sc.nextLine()));
        }

        List<EmailAddress> emails = new ArrayList<>();
        System.out.print("Email count: ");
        int eCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < eCount; i++) {
            emails.add(new EmailAddress(sc.nextLine()));
        }

        Contact contact = (type == 1)
                ? new PersonContact(name, phones, emails)
                : new OrganizationContact(name, phones, emails);

        service.addContact(contact);

        System.out.println("✅ Created. ID: " + contact.getId());
    }

    // =====================================================
    private static void viewContact(Scanner sc, ContactService service) {

        System.out.print("Enter Contact ID: ");
        UUID id = UUID.fromString(sc.nextLine());

        service.getContactById(id)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("❌ Not found.")
                );
    }

    // =====================================================
    private static void editContact(Scanner sc, ContactService service) {

        System.out.print("Enter contact name: ");
        String name = sc.nextLine();

        service.getContactByName(name).ifPresentOrElse(contact -> {

            System.out.print("New Name: ");
            contact.setName(sc.nextLine());

            System.out.println("✅ Updated.");

        }, () -> System.out.println("❌ Not found."));
    }

    // =====================================================
    private static void deleteContact(Scanner sc, ContactService service) {

        System.out.print("Enter ID: ");
        UUID id = UUID.fromString(sc.nextLine());

        if (service.deleteContactById(id))
            System.out.println("✅ Deleted.");
        else
            System.out.println("❌ Not found.");
    }

    // =====================================================
    private static void bulkOperations(Scanner sc, ContactService service) {

        System.out.println("1. Delete PERSON");
        System.out.println("2. Delete ORGANIZATION");

        int type = Integer.parseInt(sc.nextLine());

        int deleted = switch (type) {
            case 1 -> service.bulkDelete(c -> c.getType().equalsIgnoreCase("PERSON"));
            case 2 -> service.bulkDelete(c -> c.getType().equalsIgnoreCase("ORGANIZATION"));
            default -> 0;
        };

        System.out.println("Deleted: " + deleted);
    }

    // =====================================================
    private static void searchContacts(Scanner sc, ContactService service) {

        System.out.println("1. By Name");
        System.out.println("2. By Phone");
        System.out.println("3. By Email");

        int option = Integer.parseInt(sc.nextLine());

        SearchCriteria criteria = switch (option) {

            case 1 -> {
                System.out.print("Name: ");
                yield new NameSearchCriteria(sc.nextLine());
            }
            case 2 -> {
                System.out.print("Phone: ");
                yield new PhoneSearchCriteria(sc.nextLine());
            }
            case 3 -> {
                System.out.print("Email: ");
                yield new EmailSearchCriteria(sc.nextLine());
            }
            default -> null;
        };

        if (criteria != null) {
            service.search(criteria).forEach(System.out::println);
        }
    }
}