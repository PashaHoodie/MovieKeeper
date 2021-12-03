package com.example.moviekeeper.service;

import com.example.moviekeeper.dto.user.ChangePasswordDTO;
import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.User;
import com.example.moviekeeper.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean saveUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    public Optional<User> findUserByUsername(String username){
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername;
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUserByRole(String role) {
        return userRepository.getByRole(Role.valueOf(role.toUpperCase(Locale.ENGLISH)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean updateUser(User oldUser, User newUser) {
        if (userRepository.existsById(oldUser.getId())) {
            oldUser.setName(newUser.getName());
            oldUser.setTelephone(newUser.getTelephone());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public boolean updateUsername(User user, String newUsername) {
        if (user.getUsername().equals(newUsername)) {
            return false;
        } else {
            user.setUsername(newUsername);
            userRepository.save(user);
            return true;
        }
    }
    public boolean changePassword(User user, ChangePasswordDTO passwordDTO) {
        String newPassword = passwordDTO.getNewPassword();
        String oldPassword = passwordDTO.getOldPassword();
        if (passwordEncoder.matches(oldPassword, user.getPassword()) && !newPassword.equals(oldPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUserById(long id, User user) {
        if (userRepository.existsById(id) && user.getId() == id) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

