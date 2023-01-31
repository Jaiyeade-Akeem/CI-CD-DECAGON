package com.example.decagon_Life_Project.services;

import com.example.decagon_Life_Project.entities.UserEntity;
import com.example.decagon_Life_Project.models.UserEntityDto;
import com.example.decagon_Life_Project.utils.BaseResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface UserService {

    BaseResponse signUp(UserEntityDto userEntityDto) throws MessagingException, UnsupportedEncodingException, MalformedURLException;
    void sendVerificationEmail(String toEmail, String subject, String body) throws MessagingException, UnsupportedEncodingException;
    String confirmUser(String token);
}
