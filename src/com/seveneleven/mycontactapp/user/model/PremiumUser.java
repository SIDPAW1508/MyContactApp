package com.seveneleven.mycontactapp.user.model;

/**
 * Represents a premium-tier user in the system.
 *
 * This class extends the base User class and defines
 * behavior specific to PREMIUM users.
 * Premium users may have access to additional features
 * compared to FreeUser.
 */
public class PremiumUser extends User {

    /**
     * Constructs a PremiumUser instance.
     *
     * @param email          the user's normalized email address
     * @param hashedPassword the securely hashed password
     * @param name           the user's display name
     */
    public PremiumUser(String email, String hashedPassword, String name) {
        super(email, hashedPassword, name);
    }

    /**
     * Returns the role of this user.
     *
     * @return the string representation of the PREMIUM role
     */
    @Override
    public String getRole() { 
        return "PREMIUM"; 
    }

}
