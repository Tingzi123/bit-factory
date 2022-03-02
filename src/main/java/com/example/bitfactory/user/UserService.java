package com.example.bitfactory.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String login(String username, String password);

    void logout(String authorization);
}
