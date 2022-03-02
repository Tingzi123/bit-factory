package com.example.bitfactory.user.representation;

import com.example.bitfactory.user.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UsersRepresentation {
    private List<UserVO> users;

    @Builder
    @Getter
    public static class UserVO {
        private Long id;
        private String username;
        private List<Role> roles;
    }
}
