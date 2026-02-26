package com.seveneleven.mycontactapp.auth;

import java.util.Optional;

public interface Authentication {
    Optional<String> login(String email, String password);
}