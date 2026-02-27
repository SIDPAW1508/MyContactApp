package com.seveneleven.mycontactapp.user.factory;

import com.seveneleven.mycontactapp.user.model.FreeUser;
import com.seveneleven.mycontactapp.user.model.PremiumUser;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.validation.ValidationUtil;

/**
 * Factory class responsible for creating User objects.
 *
 * Implements the Factory design pattern to centralize:
 * - Input validation
 * - Password hashing
 * - User type selection
 *
 * This ensures consistent user creation logic across the application.
 */
public class UserFactory {

    /**
     * Defines the supported user types.
     */
    public enum UserType { FREE, PREMIUM }

    /**
     * Creates a User instance based on the specified type.
     *
     * Steps performed:
     * 1. Validates email, password, and name.
     * 2. Hashes the raw password.
     * 3. Normalizes the email (trim + lowercase).
     * 4. Instantiates the appropriate User subclass.
     *
     * @param type        the type of user (FREE or PREMIUM)
     * @param email       user's email address
     * @param rawPassword user's plain text password
     * @param name        user's display name
     * @return a fully constructed and validated User object
     * @throws IllegalArgumentException if validation fails
     */
    public static User create(UserType type, String email, String rawPassword, String name) {

        // Validate input fields before creating user
        ValidationUtil.validateEmail(email);
        ValidationUtil.validatePassword(rawPassword);
        ValidationUtil.validateNotBlank(name, "Display name");

        // Hash the password for secure storage
        String hashed = ValidationUtil.hashPassword(rawPassword);

        // Normalize email for consistency
        email = email.trim().toLowerCase();

        // Create the appropriate user type using Java switch expression
        return switch (type) {
            case PREMIUM -> new PremiumUser(email, hashed, name);
            default -> new FreeUser(email, hashed, name);
        };
    }
}
