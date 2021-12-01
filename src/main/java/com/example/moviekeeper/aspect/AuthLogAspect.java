package com.example.moviekeeper.aspect;

import com.example.moviekeeper.dto.jwt.JwtRespDTO;
import com.example.moviekeeper.dto.user.UserAuthDTO;
import com.example.moviekeeper.dto.user.UserDTO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthLogAspect {

    private final Logger logger = LoggerFactory.getLogger(AuthLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.moviekeeper.controller.AuthController.authenticateUser(..)) && args(userAuthDTO, ..))")
    public void authenticateUser(UserAuthDTO userAuthDTO){}

    @AfterReturning(value = "authenticateUser(userAuthDTO)", argNames = "userAuthDTO, returningValue", returning = "returningValue")
    public void logOfAuthenticateUser(UserAuthDTO userAuthDTO, ResponseEntity<JwtRespDTO> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} logged in.", userAuthDTO.getUsername());
        }else{
            logger.info("User - {} has an authorization problem", userAuthDTO.getUsername());
        }

    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.AuthController.registerUser(..)) && args(userDTO, ..))")
    public void registerUser(UserDTO userDTO){}

    @AfterReturning(value = "registerUser(userDTO)", argNames = "userAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfRegisterUser(UserDTO userAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Register new user - {}.", userAllArgsDTO.getUsername());
        }else{
            logger.info("User - {} already exists", userAllArgsDTO.getUsername());
        }
    }

}

