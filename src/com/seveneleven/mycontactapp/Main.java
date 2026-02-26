package com.seveneleven.mycontactapp;

import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.factory.UserFactory.UserType;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.exception.ValidationException;

public class Main {

    public static void main(String[] args) {

        try {
            // Create FREE user
            User freeUser = new UserBuilder()
                    .email("freeuser@gmail.com")
                    .password("free123")
                    .name("Free User")
					.type(UserType.FREE)
                    .build();

            // Create PREMIUM user
            User premiumUser = new UserBuilder()
                    .type(UserType.PREMIUM)
                    .email("premiumuser@gmail.com")
                    .password("premium123")
                    .name("Premium User")
                    .build();

            // Print details
            printUserDetails(freeUser);
            printUserDetails(premiumUser);


        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private static void printUserDetails(User user) {
        System.out.println("\nUser Details:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());
    }
}