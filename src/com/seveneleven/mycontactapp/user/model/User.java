package com.seveneleven.mycontactapp.user.model;

import com.seveneleven.mycontactapp.user.validation.ValidationUtil;

public abstract class User {
	private String name;
    private String email;
    private String hashedPassword;
    
    protected User(String email, String hashedPassword, String name) {
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.name = name;
}

    public abstract String getRole();
    public String getName() { return name;}
    public String getEmail() { return email; }
   
    public void setEmail(String email) {
        ValidationUtil.validateEmail(email);
        this.email = email.trim().toLowerCase();
    }
    public void setName(String name) {
    	this.name=name;
    }
    public void setPassword(String newPassword) {
        ValidationUtil.validatePassword(newPassword);
        this.hashedPassword = ValidationUtil.hashPassword(newPassword);
    }
 
}
