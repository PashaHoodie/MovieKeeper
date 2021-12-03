package com.example.moviekeeper.controller;


import com.example.moviekeeper.dto.user.ChangePasswordDTO;
import com.example.moviekeeper.dto.user.UserNameDTO;
import com.example.moviekeeper.dto.user.UserNameTelDTO;
import com.example.moviekeeper.entity.user.User;
import com.example.moviekeeper.service.UserService;
import com.example.moviekeeper.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userService.saveUser(user)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        Optional<User> userByUsername = userService.findUserByUsername(username);
        if (userByUsername.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userByUsername.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/getAllUsersByRole/{role}")
    public ResponseEntity<List<User>> getAllUsersByRole( @PathVariable String role, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<User> allByRole = userService.getAllUserByRole(role);
        if (allByRole.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allByRole);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById( @PathVariable long id){
        Optional<User> userById = userService.getUserById(id);
        return userById.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/updUser")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserNameTelDTO userNameTelDTO, HttpSession httpSession){
        User allArgsUserDTO = ConverterOfDTO.UserNameTelDTO(userNameTelDTO);
        User user = (User) httpSession.getAttribute("user");
        if (userService.updateUser(user, allArgsUserDTO)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updUsername")
    public ResponseEntity<String> updateUsername(@Valid @RequestBody UserNameDTO userNameDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.updateUsername(user, userNameDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updPassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.changePassword(user, changePasswordDTO)) {
        return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.deleteUserById(userId, user)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    }

