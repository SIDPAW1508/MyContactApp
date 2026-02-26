package com.seveneleven.mycontactapp;

import java.util.Scanner;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.store.UserStore;
import com.seveneleven.mycontactapp.exception.ValidationException;
import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.model.User;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = null;

        try {
            // -------- SIGN UP --------
            System.out.println("=== SIGN UP ===");

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            // Create user (UC-01)
            user = new UserBuilder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();

            // Save credentials for login (UC-02)
            UserStore.saveUser(email, password);

            System.out.println("Signup successful!\n");

            // -------- LOGIN --------
            System.out.println("=== LOGIN ===");

            System.out.print("Enter email: ");
            String loginEmail = sc.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();

            Authentication auth = new BasicAuthStrategy();

            boolean loggedIn = auth.login(loginEmail, loginPassword).isPresent();

            if (!loggedIn) {
                System.out.println("❌ Invalid credentials. Exiting...");
                return;
            }

            System.out.println("✅ Login successful!\n");

            // -------- UC-03: PROFILE MANAGEMENT --------
            System.out.println("=== PROFILE MANAGEMENT ===");

            // Change Name
            System.out.print("Enter new name: ");
            String newName = sc.nextLine();
            user.setName(newName);

            // Change Password
            System.out.print("Enter old password: ");
            String oldPassword = sc.nextLine();

            System.out.print("Enter new password: ");
            String newPassword = sc.nextLine();

            user.changePassword(oldPassword, newPassword);

            System.out.println("\n✅ Profile updated successfully!");
            System.out.println("Updated Name: " + user.getName());

        } catch (ValidationException e) {
            System.out.println("❌ " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}