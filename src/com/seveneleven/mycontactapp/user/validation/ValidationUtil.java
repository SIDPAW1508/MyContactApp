package com.seveneleven.mycontactapp.user.validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import com.seveneleven.mycontactapp.exception.ValidationException;

/**
 * Utility class for validating user input and handling password hashing.
 *
 * Responsibilities:
 * - Email validation
 * - Password validation
 * - Blank field validation
 * - Password hashing (SHA-256)
 *
 * This class cannot be instantiated.
 */
public class ValidationUtil {

    /**
     * Regular expression pattern for validating email format.
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private ValidationUtil() {}

    /**
     * Validates that the provided email is not null, not blank,
     * and matches the required email pattern.
     *
     * @param email the email to validate
     * @throws ValidationException if validation fails
     */
    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be empty.");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }

    /**
     * Checks whether an email is valid without throwing an exception.
     *
     * @param email the email to validate
     * @return true if email format is valid, otherwise false
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validates that a string field is not null or blank.
     *
     * @param value     the value to validate
     * @param fieldName the name of the field (used in error messages)
     * @throws ValidationException if the field is empty
     */
    public static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " cannot be empty.");
        }
    }

    /**
     * Validates that a password meets minimum security requirements.
     * Current rule: must be at least 6 characters long.
     *
     * @param password the password to validate
     * @throws ValidationException if password is invalid
     */
    public static void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long.");
        }
    }

    /**
     * Hashes a password using the SHA-256 algorithm.
     *
     * Steps:
     * 1. Obtain SHA-256 MessageDigest instance.
     * 2. Convert password to byte array.
     * 3. Generate hash.
     * 4. Convert byte array to hexadecimal string.
     *
     * NOTE: In production systems, consider using stronger password
     * hashing algorithms such as BCrypt or Argon2 instead of SHA-256.
     *
     * @param password the plain text password
     * @return hashed password in hexadecimal format
     * @throws RuntimeException if SHA-256 algorithm is unavailable
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing failed.", e);
        }
    }

}
