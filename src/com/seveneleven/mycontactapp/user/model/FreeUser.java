package com.seveneleven.mycontactapp.user.model;

/**
 * Represents a free-tier user in the system.
 *
 * This class extends the base User class and defines
 * behavior specific to FREE users.
 */
public class FreeUser extends User {

    /**
     * Constructs a FreeUser instance.
     *
     * @param email          the user's normalized email address
     * @param hashedPassword the securely hashed password
     * @param name           the user's display name
     */
    public FreeUser(String email, String hashedPassword, String name) {
        super(email, hashedPassword, name);
    }

    /**
     * Returns the role of this user.
     *
     * @return the string representation of the FREE role
     */
    @Override
    public String getRole() { 
        return "FREE"; 
    }

}
