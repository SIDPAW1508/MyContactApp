package com.seveneleven.mycontactapp.user.factory;

import com.seveneleven.mycontactapp.user.model.FreeUser;
import com.seveneleven.mycontactapp.user.model.PremiumUser;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.user.validation.ValidationUtil;

public class UserFactory {

    public enum UserType { FREE, PREMIUM }

    public static User create(UserType type, String email, String rawPassword, String name) {

        ValidationUtil.validateEmail(email);
        ValidationUtil.validatePassword(rawPassword);
        ValidationUtil.validateNotBlank(name, "Display name");

        String hashed = ValidationUtil.hashPassword(rawPassword);
        email = email.trim().toLowerCase();

        return switch (type) {
            case PREMIUM -> new PremiumUser(email, hashed, name);
            default -> new FreeUser(email, hashed, name);
        };
    }
}