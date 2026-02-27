package com.seveneleven.mycontactapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.seveneleven.mycontactapp.contact.model.*;
import com.seveneleven.mycontactapp.contact.service.ContactService;

/**
 * Entry point of the MyContactApp application.
 *
 * Demonstrates:
 * UC-04: Create Contact
 * UC-05: View Contact Details
 *
 * This is a simple console-based simulation.
 */
public class Main {

    public static void main(String[] args) {

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Service layer for managing contacts
        ContactService contactService = new ContactService();

        System.out.println("=== CREATE CONTACT (UC-04) ===");

        // Ask user for contact type
        System.out.print("Contact type (1-Person, 2-Organization): ");
        int type = Integer.parseInt(sc.nextLine());

        // Get contact name
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        /* =========================
           Collect Phone Numbers
           ========================= */
        List<PhoneNumber> phones = new ArrayList<>();
        System.out.print("How many phone numbers? ");
        int phoneCount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < phoneCount; i++) {
            System.out.print("Enter phone " + (i + 1) + ": ");
            phones.add(new PhoneNumber(sc.nextLine()));
        }

        /* =========================
           Collect Email Addresses
           ========================= */
        List<EmailAddress> emails = new ArrayList<>();
        System.out.print("How many email addresses? ");
        int emailCount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < emailCount; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            emails.add(new EmailAddress(sc.nextLine()));
        }

        // Create appropriate Contact type
        Contact contact;
        if (type == 1) {
            contact = new PersonContact(name, phones, emails);
        } else {
            contact = new OrganizationContact(name, phones, emails);
        }

        // Save contact using service layer
        contactService.addContact(contact);

        // Display confirmation
        System.out.println("\n✅ Contact created successfully!");
        System.out.println("Contact ID: " + contact.getId());
        System.out.println("Created At: " + contact.getCreatedAt());
        System.out.println("Type: " + contact.getType());

        /* =========================
           UC-05: View Contact Details
           ========================= */
        System.out.println("\n=== UC-05: VIEW CONTACT DETAILS ===");
        System.out.print("Enter Contact ID: ");
        String inputId = sc.nextLine();

        // Retrieve and display contact by UUID
        contactService.getContactById(UUID.fromString(inputId))
                .ifPresentOrElse(
                        foundContact -> {
                            System.out.println("\n--- CONTACT DETAILS ---");
                            System.out.println(foundContact); // calls overridden toString()
                        },
                        () -> System.out.println("❌ Contact not found.")
                );

        // Close scanner resource
        sc.close();
    }
}