package com.seveneleven.mycontactapp.user.builder;

import com.seveneleven.mycontactapp.user.factory.UserFactory;
import com.seveneleven.mycontactapp.user.factory.UserFactory.UserType;
import com.seveneleven.mycontactapp.user.model.User;

/**
 * Builder class for creating User objects.
 * 
 * This class follows the Builder design pattern to simplify
 * the construction of User instances with optional parameters.
 */
public class UserBuilder {

    // User attributes
    private String email;
    private String password;
    private String name;

    // Default user type is FREE
    private UserType type = UserType.FREE;

    /**
     * Sets the email for the user.
     *
     * @param email user's email address
     * @return current UserBuilder instance (for method chaining)
     */
    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    /**
     * Sets the password for the user.
     *
     * @param password user's password
     * @return current UserBuilder instance (for method chaining)
     */
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    /**
     * Sets the name for the user.
     *
     * @param name user's full name
     * @return current UserBuilder instance (for method chaining)
     */
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the user type (e.g., FREE, PREMIUM).
     *
     * @param type the type of user
     * @return current UserBuilder instance (for method chaining)
     */
    public UserBuilder type(UserType type) {
        this.type = type;
        return this;
    }

    /**
     * Builds and returns a User instance.
     * 
     * Internally delegates object creation to UserFactory
     * based on the selected UserType.
     *
     * @return a fully constructed User object
     */
    public User build() {
        User user = UserFactory.create(type, email, password, name);
        return user;
    }
}
