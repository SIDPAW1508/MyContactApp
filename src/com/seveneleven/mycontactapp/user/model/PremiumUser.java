package com.seveneleven.mycontactapp.user.model;



public class PremiumUser extends User {

    public PremiumUser(String email, String hashedPassword, String name) {
        super(email, hashedPassword, name);
    }

    @Override
    public String getRole() { return "PREMIUM"; }

    
}