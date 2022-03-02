
package com.example.bitfactory.user;


import com.example.bitfactory.DatabaseBaseTest;
import com.example.bitfactory.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest extends DatabaseBaseTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSimplePutAndGet() {
        Optional<User> userOptional = userRepository.findByUsername("cctv");
        assert userOptional.isEmpty();
        System.out.println("查询到的用户如下：");
        System.out.println(userOptional);
    }
}

