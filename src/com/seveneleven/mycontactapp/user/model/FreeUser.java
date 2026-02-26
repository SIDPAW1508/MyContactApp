package com.seveneleven.mycontactapp.user.model;

public class FreeUser extends User {

    public FreeUser(String email, String hashedPassword, String name) {
        super(email, hashedPassword, name);
    }

    @Override
    public String getRole() { return "FREE"; }

}