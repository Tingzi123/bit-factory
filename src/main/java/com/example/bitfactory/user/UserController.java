package com.example.bitfactory.user;

import com.example.bitfactory.user.command.CreateUserCommand;
import com.example.bitfactory.user.representation.UsersRepresentation;
import com.example.bitfactory.infrastructure.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public Resource<UsersRepresentation> getAllUser() {
        return Resource.of(userApplicationService.getAllUser());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public Resource<Void> addUser(@Valid @RequestBody CreateUserCommand command) {
        userApplicationService.addUser(command);
        return Resource.empty();
    }
}
