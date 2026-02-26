package com.seveneleven.mycontactapp.user.builder;

import com.seveneleven.mycontactapp.user.factory.UserFactory;
import com.seveneleven.mycontactapp.user.factory.UserFactory.UserType;
import com.seveneleven.mycontactapp.user.model.User;

public class UserBuilder {

    private String email;
    private String password;
    private String name;
    private UserType type = UserType.FREE;


    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder type(UserType type) {
        this.type = type;
        return this;
    }

    public User build() {
        User user = UserFactory.create(type, email, password, name);
        return user;
    }
}