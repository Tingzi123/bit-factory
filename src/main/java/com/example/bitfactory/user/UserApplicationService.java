package com.example.bitfactory.user;

import com.example.bitfactory.user.command.CreateUserCommand;
import com.example.bitfactory.user.representation.UsersRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserApplicationService {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserApplicationService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public void addUser(CreateUserCommand command) {
        userRepository.save(UserAssembler.toUser(command));
    }

    public UsersRepresentation getAllUser() {
        return UserAssembler.toUsersRepresentation(userRepository.findAll());
    }

    public String login(String username, String password) {
        return userService.login(username, password);
    }

    public void logout(String authorization) {
        userService.logout(authorization);
    }
}
