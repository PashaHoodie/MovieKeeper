package com.example.moviekeeper.aspect;

import com.example.moviekeeper.dto.user.ChangePasswordDTO;
import com.example.moviekeeper.dto.user.UserDTO;
import com.example.moviekeeper.dto.user.UserNameDTO;
import com.example.moviekeeper.dto.user.UserNameTelDTO;
import com.example.moviekeeper.entity.user.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UserLogAspect {

    private final Logger logger = LoggerFactory.getLogger(MovieLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.createUser(..)) && args(userDTO, ..))")
    public void addUser(UserDTO userDTO) {}

    @AfterReturning(value = "addUser(userDTO)", argNames = "userDTO, returningValue", returning = "returningValue")
    public void lofOfAddUser(UserDTO userDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("New user - {} added.", userDTO.getUsername());
        }else{
            logger.info("Unfortunately, user - {} not added, double-check the entered data", userDTO.getName());
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.findByUsername(..)) && args(username, ..))")
    public void findByUsername(String username){}

    @AfterReturning(value = "findByUsername(username)", argNames = "username, returningValue", returning = "returningValue")
    public void logOfFindByUsername(String username, ResponseEntity<User> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} found", username);
        }else{
            logger.info("User - {} not found", username);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.getAllUsers(..))")
    public void getAllUsers(){}

    @AfterReturning(value = "getAllUsers()", argNames = "returningValue", returning = "returningValue")
    public void logOfGetAllUsers(ResponseEntity<List<User>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Request to get all users successfully completed.");
        }else{
            logger.info("Request to get all users failed.");
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.getAllUsersByRole(..))")
    public void getAllUsersByRole(){}

    @AfterReturning(value = "getAllUsersByRole()", argNames = "returningValue", returning = "returningValue")
    public void logOfGetAllUsersByRole(ResponseEntity<List<User>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Request to get all users by role successfully completed.");
        }else{
            logger.info("Request to get all users by role failed.");
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.getUserById(..)) && args(id, ..))")
    public void getUserById(long id){}

    @AfterReturning(value = "getUserById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetUserById(long id, ResponseEntity<User> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User with id = {} found", id);
        }else{
            logger.info("User with id = {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.updateUser(..)) && args(userNameTelDTO, ..))")
    public void updateUser(UserNameTelDTO userNameTelDTO){}

    @AfterReturning(value = "updateUser(userNameTelDTO)", argNames = "userNameTelDTO, returningValue", returning = "returningValue")
    public void logOfUpdateUser(UserNameTelDTO userNameTelDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} successfully updated", userNameTelDTO.getName());
        }else{
            logger.info("The update of User - {} failed!", userNameTelDTO.getName());
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.updateUsername(..)) && args(userNameDTO, ..))")
    public void updateUsername(UserNameDTO userNameDTO){}

    @AfterReturning(value = "updateUsername(userNameDTO)", argNames = "userNameDTO, returningValue", returning = "returningValue")
    public void logOfUpdateUsername(UserNameDTO userNameDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Username of user - {} successfully updated", userNameDTO.getUsername());
        }else{
            logger.info("The update of User - {} failed!", userNameDTO.getUsername());
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.updatePassword(..)) && args(changePasswordDTO, ..))")
    public void updatePassword(ChangePasswordDTO changePasswordDTO){}

    @AfterReturning(value = "updatePassword(changePasswordDTO)", argNames = "changePasswordDTO, returningValue", returning = "returningValue")
    public void logOfUpdatePassword(ChangePasswordDTO changePasswordDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Password successfully updated");
        }else{
            logger.info("The update of password failed!");
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.UserController.deleteUser(..)) && args(userId, ..))")
    public void deleteUser(long userId){}

    @AfterReturning(value = "deleteUser(userId)", argNames = "userId, returningValue", returning = "returningValue")
    public void logOfDeleteUser(long userId, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User with id = {} successfully deleted", userId);
        }else{
            logger.info("User with id = {} deletion failed!", userId);
        }
    }

}
