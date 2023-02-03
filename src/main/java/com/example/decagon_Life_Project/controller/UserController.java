package com.example.decagon_Life_Project.controller;

import com.example.decagon_Life_Project.models.UserEntityDto;
import com.example.decagon_Life_Project.serviceImpl.UserServiceImpl;
import com.example.decagon_Life_Project.utils.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserServiceImpl userService;

    @SneakyThrows
    @PostMapping("/sign-up")
    public BaseResponse createUser(@RequestBody UserEntityDto userEntityDto) throws MessagingException, UnsupportedEncodingException {

          return  userService.signUp(userEntityDto);

    }

    @GetMapping("/confirm")
    public String confirmUser(@RequestParam (value = "token") String token){
        return userService.confirmUser(token);
    }

}
