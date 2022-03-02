package com.example.bitfactory.user;

import com.example.bitfactory.infrastructure.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserApplicationService userApplicationService;

    public LoginController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/login")
    public Resource<String> login(String username, String password) {
        return Resource.of(userApplicationService.login(username, password));
    }

    @GetMapping("/my/logout")
    public Resource<Void> logout(@RequestHeader String authorization) {
        userApplicationService.logout(authorization);
        return Resource.empty();
    }
}
