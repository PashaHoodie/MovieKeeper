package com.example.moviekeeper.repository;

import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.Telephone;
import com.example.moviekeeper.entity.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    private final UserRepository userRepository;

    private final static List<User> users = new ArrayList<>();

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeAll
    void initUsers(){
        User user1 = User.builder()
                .id(1)
                .name("Pasha")
                .username("pasham@mail.ru")
                .password("Tinker")
                .telephone(Telephone.builder()
                        .id(1)
                        .number("111222333")
                        .build())
                .role(Role.USER)
                .build();

        User user2 = User.builder()
                .id(2)
                .name("Slava")
                .username("slava@mail.ru")
                .password("Slave")
                .telephone(Telephone.builder()
                        .id(2)
                        .number("777666999")
                        .build())
                .role(Role.USER)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        users.add(user1);
        users.add(user2);
    }

    @Test
    void findByUsername() {
        assertEquals(users.get(0), userRepository.findByUsername(users.get(0).getUsername()).get());
    }

    @Test
    void getUserByUsername() {
        assertEquals(users.get(0), userRepository.getUserByUsername(users.get(0).getUsername()));
    }

    @Test
    void getByRole() {
        assertEquals(2, userRepository.getByRole(Role.USER).size());
    }
}
