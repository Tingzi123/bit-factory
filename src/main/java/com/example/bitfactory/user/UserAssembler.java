package com.example.bitfactory.user;

import com.example.bitfactory.user.command.CreateUserCommand;
import com.example.bitfactory.user.model.User;
import com.example.bitfactory.user.representation.UsersRepresentation;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserAssembler {
    public static User toUser(CreateUserCommand command) {
        return User.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .roles(command.getRoles())
                .build();
    }

    public static UsersRepresentation.UserVO toUserVO(User user) {
        return UsersRepresentation.UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public static UsersRepresentation toUsersRepresentation(List<User> users) {
        return UsersRepresentation.builder()
                .users(users.stream()
                        .map(UserAssembler::toUserVO)
                        .collect(toList()))
                .build();
    }
}
