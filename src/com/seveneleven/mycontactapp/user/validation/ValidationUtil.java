package com.seveneleven.mycontactapp.user.validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import com.seveneleven.mycontactapp.exception.ValidationException;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    
    // Prevent instantiation - utility class
    private ValidationUtil() {}

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be empty.");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " cannot be empty.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long.");
        }
    }

    /**
     * Hashes a password using SHA-256.
     * Demonstrates: MessageDigest, byte array to hex conversion
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
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
