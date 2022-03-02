package com.example.bitfactory.user.command;

import com.example.bitfactory.user.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CreateUserCommand {
    private String username;
    private String password;
    private List<Role> roles;
}
