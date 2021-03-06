package com.example.bitfactory.api;

import com.example.bitfactory.user.UserRepository;
import com.example.bitfactory.user.model.Role;
import com.example.bitfactory.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldLogin() throws Exception {
        userRepository.save(new User("cctv", "pass", List.of(new Role("Admin", "admin"))));
        mockMvc.perform(get("/login")
                .param("username", "cctv")
                .param("password", "pass"))
                .andExpect(status().isOk());
    }
}
