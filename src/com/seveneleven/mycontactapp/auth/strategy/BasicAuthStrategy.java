package com.seveneleven.mycontactapp.auth.strategy;

import java.util.Optional;

import com.seveneleven.mycontactapp.auth.Authentication;
import com.seveneleven.mycontactapp.auth.store.UserStore;

public class BasicAuthStrategy implements Authentication {

    @Override
    public Optional<String> login(String email, String password) {
        if (UserStore.isValidUser(email, password)) {
            return Optional.of(email);
        }
        return Optional.empty();
    }
}