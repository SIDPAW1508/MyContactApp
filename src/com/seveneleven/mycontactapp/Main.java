package com.seveneleven.mycontactapp;

import java.util.Scanner;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.seveneleven.mycontactapp.auth.store.UserStore;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // -------- SIGN UP --------
        System.out.println("=== SIGN UP ===");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserStore.saveUser(email, password);
        System.out.println("Signup successful for: " + name);

        // -------- LOGIN --------
        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter email: ");
        String loginEmail = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        Authentication auth = new BasicAuthStrategy();

        auth.login(loginEmail, loginPassword)
                .ifPresentOrElse(
                        user -> System.out.println("✅ Valid credentials. Login successful."),
                        () -> System.out.println("❌ Invalid credentials.")
                );

        scanner.close();
    }
}