package com.example.bitfactory.service;

import com.example.bitfactory.infrastructure.configuration.RedisDao;
import com.example.bitfactory.user.UserRepository;
import com.example.bitfactory.user.UserService;
import com.example.bitfactory.user.UserServiceImpl;
import com.example.bitfactory.user.model.Role;
import com.example.bitfactory.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    RedisDao redisDao = mock(RedisDao.class);

    UserService userService;

    public UserServiceTest() {
        this.userService = new UserServiceImpl(userRepository, redisDao);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void login() {
        userRepository.findByUsername("admin");
        User user = new User(1L, "cctv", "123", List.of(new Role("ADMIN", "admin")));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        String token = userService.login("cctv", "123");
        Assert.assertFalse(token.isBlank());
    }
}

