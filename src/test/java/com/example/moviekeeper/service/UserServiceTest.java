package com.example.moviekeeper.service;

import com.example.moviekeeper.aggregator.UserAggregatorTest;
import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.Telephone;
import com.example.moviekeeper.entity.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
public class UserServiceTest {

    private static final List<User> users = new ArrayList<>();
    private final UserService userService;

    @Autowired
    UserServiceTest(UserService userService) {
        this.userService = userService;
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
        userService.saveUser(user1);
        userService.saveUser(user2);
        users.add(user1);
        users.add(user2);
    }

    @ParameterizedTest
    @CsvSource({"1, Pasha, pasham@mail.ru, Tinker, 1, 111222333",
            "2, Slava, slava@mail.ru, Slave, 2, 777666999"
})

    @DisplayName("addUser")
    void addUser(@AggregateWith(UserAggregatorTest.class) User user) {
        userService.saveUser(user);
        assertEquals(2, userService.getAllUsers().size());//expected, actual
    }

    @ParameterizedTest
    @CsvSource({"1, Pasha, pasham@mail.ru, Tinker, 1, 111222333"})
    @DisplayName("getUserByUsername")
    void getUserByUsername(@AggregateWith(UserAggregatorTest.class) User user) {
        User userByUsername = userService.getUserByUsername(user.getUsername());
        assertEquals(user, userByUsername);
    }

    @ParameterizedTest
    @CsvSource({"1, Pasha, pasham@mail.ru, Tinker, 1, 111222333"})
    @DisplayName("updateUser")
    void updateUser(@AggregateWith(UserAggregatorTest.class) User user) {
        userService.updateUser(users.get(0), user);
        assertEquals(users.get(0).getUsername(), user.getUsername());
    }

    @ParameterizedTest
    @CsvSource({"1, Pasha, pasham@mail.ru, Tinker, 1, 111222333"})
    @DisplayName("updateUsername")
    void updateUsername(@AggregateWith(UserAggregatorTest.class) User user) {
        userService.updateUsername(users.get(0), user.getUsername());
        assertEquals(users.get(0).getUsername(), user.getUsername());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(1, users.get(0));
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void getAllUsers() {
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getAllByRole() {
        String role = "user";
        assertEquals(2, userService.getAllUserByRole(role).size());
    }

    @Test
    void getUserById() {
        assertEquals(users.get(0), userService.getUserById(users.get(0).getId()).get());
    }
}
